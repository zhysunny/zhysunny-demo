package com.zhysunny.scala.learn

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object DataStructures {
  def main(args: Array[String]): Unit = {
    // 数据结构

    // 数组
    //数组一旦定义长度不能改变
    val a1 = Array[Int](1, 2, 3, 4, 5, 6, 7, 8, 9) //创建一个数组，给定数组元素值
    println(a1.toBuffer)
    val a2 = new Array[Int](9) //创建一个空数组，长度为9，元素为泛型的默认值
    println(a2.toBuffer)
    //数组长度可变的类ArrayBuffer
    val ab = new ArrayBuffer[Int]() //创建一个空数组
    //追加一个元素1
    ab += 1
    ab.append(1)
    ab -= 1 //删除第一次出现的元素
    //追加集合中所有的元素
    ab ++= Array(2, 3)
    ab.appendAll(Array(2, 3))
    ab.insert(1, 4, 5) //在索引1的位置添加元素4，5
    ab.remove(2, 2) //从索引2开始删除两个元素
    println(ab)


    // 字典
    //HashMap
    //在immutable下是不可变集合，内容不可变
    val m1 = scala.collection.immutable.HashMap[String, Int]("a" -> 1, "b" -> 2) //初始化两个元素
    println(m1)
    //在mutable下是可变集合，内容可变
    val m2 = new scala.collection.mutable.HashMap[String, Int]()
    m2.put("a", 1) //添加一个键值对
    m2 += ("b" -> 2) //添加一个键值对
    m2 += "c" -> 3 //添加一个键值对
    m2 -= "c" //删除一个键值对
    m2.remove("a")
    println(m2)


    // Set集合
    //Set   HashSet
    //在immutable下是不可变集合，内容不可变
    val s1 = scala.collection.immutable.HashSet[Int](1, 2, 3, 4)
    println(s1)
    //在mutable下是可变集合，内容可变
    val s2 = new scala.collection.mutable.HashSet[Int]()
    s2 += 5 //添加一个元素
    s2 -= 5 //删除一个元素
    s2.add(1)
    s2.remove(1)
    println(s2)


    // List集合
    //List不可变集合，内容不可变
    val l1 = List[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(l1)
    //可变List用ListBuffer
    val lb = ListBuffer(1, 2, 3)
    lb += 4
    lb -= 4
    lb ++= l1
    lb.append(3)
    lb.remove(3) //删除索引为3的一个元素
    println(lb)


    // 常用方法
    val arr = Array[Int](1, 10, 2, 23, 25, 20, 5, 9)
    println(arr.toBuffer)
    println("sum: " + arr.sum) //求和
    println("max: " + arr.max) //最大值
    println("min: " + arr.min) //最小值
    println("size: " + arr.size) //长度
    println("length: " + arr.length) //长度
    println("sorted: " + arr.sorted.toBuffer) //排序，升序
    println("reverse: " + arr.sorted.reverse.toBuffer) //降序
    println("sortWith升序: " + arr.sortWith(_ < _).toBuffer) //升序
    println("sortWith降序: " + arr.sortWith(_ > _).toBuffer) //降序
    println("元素按照字符串排序:" + arr.sortBy(_.toString).toBuffer) //元素按照字符串排序，本身数据不变
    println("sortBy降序:" + arr.sortBy(-_).toBuffer) //降序


    // 其他
    println(5 :: List(2, 3, 5))
    println(1 to 5)
    println(1 until 6)
    // 步长
    println(1 to 10 by 3)

  }
}
