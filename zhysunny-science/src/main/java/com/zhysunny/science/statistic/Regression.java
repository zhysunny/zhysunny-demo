package com.zhysunny.science.statistic;

import org.apache.commons.math3.stat.regression.GLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.util.Arrays;

/**
 * 简单回归
 * 普通最小二乘法
 * 广义最小二乘法
 * @author 章云
 * @date 2019/10/23 15:59
 */
public class Regression {

    public static void main(String[] args) {
        System.out.println("简单回归");
        simple(DataSet.REGRESSION);
        System.out.println("普通最小二乘法");
        ols(DataSet.X, DataSet.Y);
        System.out.println("广义最小二乘法");
        gls(DataSet.X, DataSet.Y, DataSet.OMEGA);
    }

    /**
     * 简单回归
     * @param data
     */
    public static void simple(double[][] data) {
        SimpleRegression regression = new SimpleRegression();
        regression.addData(data);
        // y = ax + b
        // b  截距
        System.out.println(regression.getIntercept());
        // a  斜率
        System.out.println(regression.getSlope());
        // 斜率的标准误差
        System.out.println(regression.getSlopeStdErr());
    }

    /**
     * 普通最小二乘法
     * @param x
     * @param y
     */
    public static void ols(double[][] x, double[] y) {
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.newSampleData(y, x);
        double[] beta = regression.estimateRegressionParameters();
        System.out.println(Arrays.toString(beta));
        double[] residuals = regression.estimateResiduals();
        System.out.println(Arrays.toString(residuals));
        double[][] parametersVariance = regression.estimateRegressionParametersVariance();
        System.out.println(Arrays.toString(parametersVariance));
        double rSquared = regression.calculateRSquared();
        System.out.println(rSquared);
        double sigma = regression.estimateRegressionStandardError();
        System.out.println(sigma);
    }

    /**
     * 广义最小二乘法
     * @param x
     * @param y
     * @param omega
     */
    public static void gls(double[][] x, double[] y, double[][] omega) {
        GLSMultipleLinearRegression regression = new GLSMultipleLinearRegression();
        regression.newSampleData(y, x, omega);
        double[] beta = regression.estimateRegressionParameters();
        System.out.println(Arrays.toString(beta));
        double[] residuals = regression.estimateResiduals();
        System.out.println(Arrays.toString(residuals));
        double[][] parametersVariance = regression.estimateRegressionParametersVariance();
        System.out.println(Arrays.toString(parametersVariance));
        double regressandVariance = regression.estimateRegressandVariance();
        System.out.println(regressandVariance);
        double sigma = regression.estimateRegressionStandardError();
        System.out.println(sigma);
    }

}
