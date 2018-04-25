package GUI;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

public class CustomBarChart extends AnchorPane
{
    private XYChart.Series data = new XYChart.Series();
    private XYChart.Data d1 = new XYChart.Data("2.0", 0);
    private XYChart.Data d2 = new XYChart.Data("3.0", 0);
    private XYChart.Data d3 = new XYChart.Data("3.5", 0);
    private XYChart.Data d4 = new XYChart.Data("4.0", 0);
    private XYChart.Data d5 = new XYChart.Data("4.5", 0);
    private XYChart.Data d6 = new XYChart.Data("5.0", 0);

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

    public CustomBarChart()
    {
        bc.setTitle("Distribution of Marks");
        xAxis.setLabel("Mark");
        yAxis.setLabel("Value");

        yAxis.setTickUnit(1);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(9);
        yAxis.setTickLabelFormatter(new CustomStringConverter());

        data.setName("Marks");
        this.getChildren().add(bc);
    }

    public void uptadeData(int m2_0, int m3_0, int m3_5, int m4_0, int m4_5, int m5_0)
    {
        bc.getData().removeAll(data);
        data.getData().removeAll(d1, d2, d3, d4, d5, d6);
        d1 = new XYChart.Data("2.0", m2_0);
        d2 = new XYChart.Data("3.0", m3_0);
        d3 = new XYChart.Data("3.5", m3_5);
        d4 = new XYChart.Data("4.0", m4_0);
        d5 = new XYChart.Data("4.5", m4_5);
        d6 = new XYChart.Data("5.0", m5_0);
        data.getData().add(d1);
        data.getData().add(d2);
        data.getData().add(d3);
        data.getData().add(d4);
        data.getData().add(d5);
        data.getData().add(d6);

        bc.getData().addAll(data);
    }
}
