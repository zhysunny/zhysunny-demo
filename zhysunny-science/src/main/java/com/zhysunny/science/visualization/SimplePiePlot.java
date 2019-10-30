package com.zhysunny.science.visualization;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.colors.LinearGradient;
import de.erichseifert.gral.ui.InteractivePanel;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 甜圈图
 * @author 章云
 * @date 2019/10/26 19:08
 */
public class SimplePiePlot extends JFrame {

    private static final int SAMPLE_COUNT = 10;

    public SimplePiePlot() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        // 生成实例数据
        DataTable data = new DataTable(Integer.class);
        Random random = new Random();
        for (int i = 0; i < SAMPLE_COUNT; i++) {
            int value = random.nextInt(8) + 2;
            data.add((random.nextDouble() <= 0.15) ? -value : value);
        }
        // 新建饼图
        PiePlot plot = new PiePlot(data);
        // 设置图形
        plot.getTitle().setText(String.format("Donut plot of %d random data values", SAMPLE_COUNT));
        // 设置饼块相对大小
        plot.setRadius(0.9);
        // 显示图例
        plot.setLegendVisible(true);
        // 向图形区域添加边距
        plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));
        PiePlot.PieSliceRenderer pointRenderer = (PiePlot.PieSliceRenderer)plot.getPointRenderer(data);
        // 设置内区域相对大小
        pointRenderer.setInnerRadius(0.4);
        // 设置饼块间的间隔大小
        pointRenderer.setGap(0.2);
        // 设置颜色
        LinearGradient colors = new LinearGradient(Color.BLUE, Color.GREEN);
        pointRenderer.setColor(colors);
        // 显示标签
        pointRenderer.setValueVisible(true);
        pointRenderer.setValueColor(Color.WHITE);
        pointRenderer.setValueFont(Font.decode(null).deriveFont(Font.BOLD));
        // 把图形添加到Swing组件
        InteractivePanel panel = new InteractivePanel(plot);
        add(panel);
    }

    public static void main(String[] args) {
        SimplePiePlot plot = new SimplePiePlot();
        plot.setVisible(true);
    }

}
