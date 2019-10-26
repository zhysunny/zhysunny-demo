package com.zhysunny.science.data.visualization;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Location;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.ui.InteractivePanel;
import javax.swing.*;
import java.awt.*;

/**
 * 条形图
 * @author 章云
 * @date 2019/10/26 19:08
 */
public class SimpleBarPlot extends JFrame {

    public SimpleBarPlot() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        // 模拟数据
        DataTable data = new DataTable(Double.class, Integer.class, String.class);
        data.add(0.1, 1, "1月");
        data.add(0.2, 3, "2月");
        data.add(0.3, -2, "3月");
        data.add(0.4, 6, "4月");
        data.add(0.5, -4, "5月");
        data.add(0.6, 8, "6月");
        data.add(0.7, 9, "7月");
        data.add(0.8, 11, "8月");
        data.add(0.9, -11, "9月");
        data.add(1.0, 10, "10月");
        data.add(1.1, 7, "11月");
        data.add(1.2, 3, "12月");
        // 新建条形图
        BarPlot plot = new BarPlot(data);
        // 设置图形
        plot.setInsets(new Insets2D.Double(40.0, 40.0, 40.0, 40.0));
        plot.setBarWidth(0.075);
        // 设置长条
        BarPlot.BarRenderer pointRenderer = (BarPlot.BarRenderer)plot.getPointRenderers(data).get(0);
        pointRenderer.setColor(Color.BLUE);
        pointRenderer.setBorderColor(Color.GREEN);
        pointRenderer.setValueVisible(true);
        pointRenderer.setValueColumn(2);
        pointRenderer.setValueLocation(Location.CENTER);
        pointRenderer.setValueColor(Color.RED);
        pointRenderer.setValueFont(Font.decode(null).deriveFont(Font.BOLD));
        // 把图形添加到Swing组件
        InteractivePanel panel = new InteractivePanel(plot);
        panel.setPannable(false);
        panel.setZoomable(false);
        add(panel);
    }

    public static void main(String[] args) {
        SimpleBarPlot plot = new SimpleBarPlot();
        plot.setVisible(true);
    }

}
