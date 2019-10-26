package com.zhysunny.science.data.visualization;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.areas.AreaRenderer;
import de.erichseifert.gral.plots.areas.DefaultAreaRenderer2D;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 面积图
 * @author 章云
 * @date 2019/10/26 19:44
 */
public class AreaPlot extends JFrame {

    private static final int SAMPLE_COUNT = 50;

    public AreaPlot() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        // 生成实例数据
        Random random = new Random();
        DataTable data = new DataTable(Double.class, Double.class, Double.class, Double.class);
        for (double x = 0.0; x < SAMPLE_COUNT; x++) {
            double y1 = random.nextGaussian();
            double y2 = random.nextGaussian();
            double y3 = random.nextGaussian();
            data.add(x, y1, y2, y3);
        }
        // 创建数据系列
        DataSeries data1 = new DataSeries("Series 1", data, 0, 1);
        DataSeries data2 = new DataSeries("Series 2", data, 0, 2);
        DataSeries data3 = new DataSeries("Series 3", data, 0, 3);
        // 新建XYPlot对象
        XYPlot plot = new XYPlot(data1, data2, data3);
        plot.setLegendVisible(true);
        plot.setInsets(new Insets2D.Double(20.0, 40.0, 20.0, 20.0));
        // 设置数据系列
        formatFilledArea(plot, data1, Color.BLUE);
        formatFilledArea(plot, data2, Color.GREEN);
        formatLineArea(plot, data3, Color.CYAN);
        // 把图形添加到Swing组件
        InteractivePanel panel = new InteractivePanel(plot);
        add(panel, BorderLayout.CENTER);
    }

    private static void formatFilledArea(XYPlot plot, DataSource data, Color color) {
        PointRenderer point = new DefaultPointRenderer2D();
        point.setColor(color);
        plot.setPointRenderers(data, point);
        LineRenderer line = new DefaultLineRenderer2D();
        line.setColor(color);
        line.setGap(3.0);
        line.setGapRounded(true);
        plot.setLineRenderers(data, line);
        AreaRenderer area = new DefaultAreaRenderer2D();
        area.setColor(color);
        plot.setAreaRenderers(data, area);
    }

    private static void formatLineArea(XYPlot plot, DataSource data, Color color) {
        PointRenderer point = new DefaultPointRenderer2D();
        point.setColor(color);
        plot.setPointRenderers(data, point);
        plot.setLineRenderers(data, null);
        AreaRenderer area = new DefaultAreaRenderer2D();
        area.setGap(3.0);
        area.setColor(color);
        plot.setAreaRenderers(data, area);
    }

    public static void main(String[] args) {
        AreaPlot plot = new AreaPlot();
        plot.setVisible(true);
    }

}
