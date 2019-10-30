package com.zhysunny.science.visualization;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.ui.InteractivePanel;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 散点图
 * @author 章云
 * @date 2019/10/26 19:44
 */
public class ScatterPlot extends JFrame {

    private static final int SAMPLE_COUNT = 100000;

    public ScatterPlot() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        // 生成实例数据
        Random random = new Random();
        DataTable data = new DataTable(Double.class, Double.class);
        for (int i = 0; i < SAMPLE_COUNT; i++) {
            data.add(2.0 * random.nextGaussian(), 2.0 * random.nextGaussian());
        }
        // 新建XYPlot对象
        XYPlot plot = new XYPlot(data);
        // 设置图形
        plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));
        plot.getTitle().setText(String.format("Scatter Plot with %d data points", data.getRowCount()));
        // 设置数据点
        plot.getPointRenderers(data).get(0).setColor(Color.BLUE);
        // 把图形添加到Swing组件
        InteractivePanel panel = new InteractivePanel(plot);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ScatterPlot plot = new ScatterPlot();
        plot.setVisible(true);
    }

}
