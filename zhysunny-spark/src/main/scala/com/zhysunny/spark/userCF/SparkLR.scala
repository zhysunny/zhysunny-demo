package com.zhysunny.spark.userCF

import org.apache.spark.ml.classification.{BinaryLogisticRegressionSummary, LogisticRegression}
import org.apache.spark.ml.feature.RFormula
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object SparkLR {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Spark LR")
      .enableHiveSupport()
      .getOrCreate()
    val orders = spark.sql("select * from badou.orders")
    val priors = spark.sql("select * from badou.order_products_prior")
    val trains = spark.sql("select * from badou.order_products_train")

    // 去重后用户数206209
    val prior_user = orders.filter("eval_set='prior'").select("user_id").distinct()
    // 去重后用户数131209
    val train_user = orders.filter("eval_set='train'").select("user_id").distinct()
    // 去重后用户数75000
    val test_user = orders.filter("eval_set='test'").select("user_id").distinct()
    // 两两取交集
    // 131209
    val prior_train = prior_user.intersect(train_user)
    // 75000
    val prior_test = prior_user.intersect(test_user)
    // 0
    val train_test = test_user.intersect(train_user)

    val orders_prior_user = orders.join(priors, "order_id")
    val orders_train_user = orders.join(trains, "order_id")

    // 13307953 总样本数
    val orders_prior_user_unique = orders_prior_user.select("user_id", "product_id").distinct()
    // 1384617 正样本数，负样本数为13307953-828823=12479130
    //    正样本设置label=1
    val orders_train_user_unique = orders_train_user.select("user_id", "product_id").distinct()
      .withColumn("label", lit(1))
    // 交集828823
    orders_prior_user_unique.intersect(orders_train_user_unique).count()
    // 正样本为1，其余为负样本为0
    val train_data = orders_prior_user_unique.join(orders_train_user_unique, Seq("user_id", "product_id"), "outer")
      .na.fill(0)
    // 特征
    val (priors_feature, user_feature) = Feature.getFeature(priors, orders)
    // 训练数据
    val train = train_data.join(priors_feature, "product_id").join(user_feature, "user_id")

    //    特征处理通过rformula:离散化特征one-hot，连续特征不处理，
    //    最后将分别处理的特征向量拼成最后的特征
    val rformula = new RFormula()
      .setFormula("label ~ period + order_cnt + user_product_cnt" +
        " + product_distinct_cnt + product_avg + product_cnt + reorder_sum + reordered_rate")
      .setFeaturesCol("features")
      .setLabelCol("label")

    //    对数据进行rformula处理生成新的数据：features是特征向量，label是标签
    val df = rformula.fit(train).transform(train).select("features", "label")
      .cache()

    //  算法没有收敛，迭代停止是因为达到了最大迭代次数
    // LogisticRegression training finished but the result is not converged because: max iterations reached
    //    lr模型的定义
    val lr = new LogisticRegression().setMaxIter(10).setRegParam(0)

    //    划分训练集和测试集
    val Array(trainingData, testData) = df.randomSplit(Array(0.7, 0.3))
    //    模型训练
    val lrModel = lr.fit(trainingData)
    // 打印系数（weight：W）和截距b
    print(s"Coefficients: ${lrModel.coefficients} intercept: ${lrModel.intercept}")

    val trainingSummary = lrModel.summary
    val objectHistory = trainingSummary.objectiveHistory
    //   打印loss
    objectHistory.foreach(loss => println(loss))

    val binarySummary = trainingSummary.asInstanceOf[BinaryLogisticRegressionSummary]

    val roc = binarySummary.roc
    //   TPR,FPR
    roc.show()
    //    AUC
    //    0.72-0.8  上线标准
    println(binarySummary.areaUnderROC)
    //    预测testData
    val test = lrModel.transform(testData)
  }
}
