package com.rythm_zone.loginPage1;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class report extends Application {
     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("📄 Reports - Music Store");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(
                Color.rgb(10, 25, 47), CornerRadii.EMPTY, Insets.EMPTY))); // Dark Blue

        // ===== Header =====
        Label title = new Label("📊 Business Reports");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        title.setTextFill(Color.WHITE);

        Button backBtn = new Button("⬅ Back to Admin");
        backBtn.setStyle("-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> {
            try {
                new admin().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox topBar = new HBox(15, title, backBtn);
        topBar.setAlignment(Pos.CENTER_LEFT);
        root.setTop(topBar);

        // ===== Charts Section =====
        VBox chartBox = new VBox(30);
        chartBox.setAlignment(Pos.CENTER);

        // Pie Chart - Business Distribution
        PieChart pieChart = new PieChart();
        pieChart.getData().add(new PieChart.Data("Selling", 65));
        pieChart.getData().add(new PieChart.Data("Rental", 25));
        pieChart.getData().add(new PieChart.Data("Courses", 10));
        pieChart.setTitle("Business Distribution");
        pieChart.setLegendVisible(true);
        pieChart.setLabelsVisible(true);
        pieChart.setPrefSize(500, 400);

        // Bar Chart - Monthly Sales
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Sales Count");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Monthly Sales Report");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2025");
        series.getData().add(new XYChart.Data<>("Jan", 40));
        series.getData().add(new XYChart.Data<>("Feb", 55));
        series.getData().add(new XYChart.Data<>("Mar", 35));
        series.getData().add(new XYChart.Data<>("Apr", 70));
        series.getData().add(new XYChart.Data<>("May", 50));

        barChart.getData().add(series);
        barChart.setCategoryGap(20);
        barChart.setBarGap(5);
        barChart.setPrefSize(600, 400);

        chartBox.getChildren().addAll(pieChart, barChart);

        root.setCenter(chartBox);

        Scene scene = new Scene(root, screenWidth,  screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

