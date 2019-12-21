package com.zhysunny.science.visualization;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import javax.swing.*;
import java.awt.*;

/**
 * 复利
 * @author 章云
 * @date 2019/12/18 20:18
 */
public class CompoundInterest extends JFrame {

    /**
     * 利率
     */
    private static final double RATE = 0.12;
    /**
     * 时间
     */
    private static final int TIME = 30;
    /**
     * 本金
     */
    private static final double MONEY = 120000.00;

    public CompoundInterest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);

        DataTable data = new DataTable(Double.class, Double.class);
        for (double x = 0; x <= TIME; x += 1) {
            double y = Math.pow((1 + RATE), x);
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
        CompoundInterest plot = new CompoundInterest();
        plot.setVisible(true);
    }

}
