package com.zhysunny.science.visualization;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import javax.swing.*;
import java.awt.*;

/**
 * 2D正弦图
 * @author 章云
 * @date 2019/10/26 14:50
 */
public class SineGraph extends JFrame {

    public SineGraph() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);

        DataTable data = new DataTable(Double.class, Double.class);
        for (double x = 0; x < 2*Math.PI; x += 0.01) {
            // y = 5*sin(x)
            double y = 5.0 * Math.sin(x);
            data.add(x, y);
        }
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getLineRenderers(data).get(0).setColor(color);
    }

    public static void main(String[] args) {
        SineGraph plot = new SineGraph();
        plot.setVisible(true);
    }

}
