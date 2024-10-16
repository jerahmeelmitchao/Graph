import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LineChartExampleDatabase extends Application {

    @Override
    public void start(Stage stage) throws Exception {
      
        int minYear = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;
        int minSchools = Integer.MAX_VALUE;
        int maxSchools = Integer.MIN_VALUE;

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Schools Data");


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_growth", "root", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT year, number_of_schools FROM school_data")) {

            while (resultSet.next()) {
                int year = resultSet.getInt("year");
                int numberOfSchools = resultSet.getInt("number_of_schools");

                series.getData().add(new XYChart.Data<>(year, numberOfSchools));

              
                if (year < minYear) minYear = year;
                if (year > maxYear) maxYear = year;
                if (numberOfSchools < minSchools) minSchools = numberOfSchools;
                if (numberOfSchools > maxSchools) maxSchools = numberOfSchools;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        NumberAxis xAxis = new NumberAxis(minYear, maxYear, (maxYear - minYear) / 10.0); // Adjust tick unit
        xAxis.setLabel("Years");

        NumberAxis yAxis = new NumberAxis(minSchools, maxSchools, (maxSchools - minSchools) / 10.0); // Adjust tick unit
        yAxis.setLabel("No. of Schools");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("School Growth Over Years");


        lineChart.getData().add(series);


        StackPane root = new StackPane();
        root.getChildren().add(lineChart);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Line Chart from Database");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
