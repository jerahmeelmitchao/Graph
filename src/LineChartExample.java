import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LineChartExample extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // X-axis for years
        NumberAxis xAxis = new NumberAxis(1960, 2020, 10);
        xAxis.setLabel("Years");

        // Y-axis for number of schools
        NumberAxis yAxis = new NumberAxis(0, 350, 50);
        yAxis.setLabel("No. of Schools");

        // Creating the line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("School Growth Over Years");

        // Data series for the line chart
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Schools Data");
        
        
        series.getData().add(new XYChart.Data<>(1970, 15));
        series.getData().add(new XYChart.Data<>(1980, 30));
        series.getData().add(new XYChart.Data<>(1990, 60));
        series.getData().add(new XYChart.Data<>(2000, 120));
        series.getData().add(new XYChart.Data<>(2013, 240));
        series.getData().add(new XYChart.Data<>(2014, 300));

        // Add the data series to the line chart
        lineChart.getData().add(series);

        // Create the root pane and add the chart
        StackPane root = new StackPane();
        root.getChildren().add(lineChart);

        // Set up the scene and stage
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Line Chart Example");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
