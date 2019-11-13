package com.automotriz.Presentacion;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {

    private final JPanel parent;

    public Graph(JPanel parent) {
        this.parent = parent;
    }

    public void setGraphBarra() {
        XYDataset dataset = createDataset();
        JFreeChart ch = ChartFactory.createXYLineChart(
                ReadProperties.props.getProperty("graph.title"),
                "Ventas",
                "Dias de la semana", dataset, PlotOrientation.HORIZONTAL, true, true, false);

        ch = ChartFactory.createBarChart(ReadProperties.props.getProperty("graph.title"),
                "Dias de la semana", "Ventas", createDataSet(), PlotOrientation.VERTICAL, true, true, true);

        ChartPanel cp = new ChartPanel(ch);
        parent.add(cp);
        cp.setBounds(20, 40, 1150, 470);
        cp.setVisible(true);
    }

    private XYDataset createDataset() {
        XYSeries series = new XYSeries("2016");
        series.add(18, 567);
        series.add(20, 612);
        series.add(25, 800);
        series.add(30, 980);
        series.add(40, 1410);
        series.add(50, 2350);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private CategoryDataset createDataSet() {
        final String fiat = "FIAT";
        final String audi = "AUDI";
        final String ford = "FORD";
        final String speed = "Speed";
        final String millage = "Millage";
        final String userrating = "User Rating";
        final String safety = "safety";
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        dataset.addValue(1.0, "Lunes", "Lunes");
        dataset.addValue(5.0, "Martes", "Martes");
        dataset.addValue(6.0, "Miercoles", "Miercoles");
        dataset.addValue(10.0, "Jueves", "Jueves");
        dataset.addValue(4.0, "Viernes", "viernes");
        dataset.addValue(4.0, "Sabado", "Sabado");
        dataset.addValue(2.0, "Domingo", "Domingo");

        return dataset;
    }

}
