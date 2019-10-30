package com.zhysunny.science.visualization;

import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.BoxPlot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.DataUtils;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 箱线图
 * @author 章云
 * @date 2019/10/26 19:08
 */
public class SimpleBoxPlot extends JFrame {

    private static final int SAMPLE_COUNT = 50;

    public SimpleBoxPlot() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setPreferredSize(new Dimension(400, 600));
        // 生成实例数据
        DataTable data = new DataTable(Integer.class, Integer.class, Integer.class);
        Random random = new Random();
        for (int i = 0; i < SAMPLE_COUNT; i++) {
            int x = (int)Math.round(5.0 * random.nextGaussian());
            int y = (int)Math.round(5.0 * random.nextGaussian());
            int z = (int)Math.round(5.0 * random.nextGaussian());
            data.add(x, y, z);
        }
        // 新建箱线图
        DataSource boxData = BoxPlot.createBoxData(data);
        BoxPlot plot = new BoxPlot(boxData);
        // 设置图形
        plot.setInsets(new Insets2D.Double(20.0, 50.0, 40.0, 20.0));
        // 设置坐标轴格式
        plot.getAxisRenderer(BoxPlot.AXIS_X)
        .setCustomTicks(DataUtils.map(new Double[]{ 1.0, 2.0, 3.0 }, new String[]{ "Column 1", "Column 2", "Column 3" }));
        // 设置箱盒
        BoxPlot.BoxWhiskerRenderer pointRenderer = (BoxPlot.BoxWhiskerRenderer)plot.getPointRenderers(boxData).get(0);
        pointRenderer.setBoxBorderColor(Color.BLUE);
        pointRenderer.setWhiskerColor(Color.GREEN);
        pointRenderer.setCenterBarColor(Color.RED);
        plot.getNavigator().setDirection(XYPlot.XYNavigationDirection.VERTICAL);
        // 把图形添加到Swing组件
        InteractivePanel panel = new InteractivePanel(plot);
        panel.setPannable(false);
        panel.setZoomable(false);
        add(panel);
    }

    public static void main(String[] args) {
        SimpleBoxPlot plot = new SimpleBoxPlot();
        plot.setVisible(true);
    }

}
