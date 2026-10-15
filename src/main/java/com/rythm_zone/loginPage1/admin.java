package com.rythm_zone.loginPage1;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class admin extends Application {
     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    private Label logoutMessage;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rhythm Zone Admin Dashboard");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(
                Color.rgb(10, 25, 47), CornerRadii.EMPTY, Insets.EMPTY)));

        // ===== Top Navigation Bar =====
        HBox navBar = new HBox(15);
        navBar.setPadding(new Insets(10));
        navBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("🎵 Admin Dashboard - Rhythm Zone");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        title.setTextFill(Color.WHITE);

        // Spacer pushes buttons to right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button homeBtn = createNavButton("🏠 Home");
         homeBtn.setOnAction(e -> {
            try {
                new home1().start(primaryStage); // Navigate to home1 page
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button reportsBtn = createNavButton("📄 Reports");
        reportsBtn.setOnAction(e -> {
            try {
                new report().start(new Stage());
                ((Stage) reportsBtn.getScene().getWindow()).close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        logoutMessage = new Label("");
        logoutMessage.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        logoutMessage.setTextFill(Color.LIGHTGREEN);

        Button logoutBtn = createLogoutButton();

        navBar.getChildren().addAll(title, spacer, homeBtn, reportsBtn, logoutBtn, logoutMessage);
        root.setTop(navBar);

        // ===== Left Panel: Forms =====
        VBox leftPanel = new VBox(15);
        leftPanel.setPadding(new Insets(10));
        leftPanel.getChildren().addAll(
                createFormSection("📦 Rental Management", "Instrument Name", "Customer Name", "Rental Duration (days)", Color.BLACK),
                createFormSection("💰 Selling Management", "Instrument Name", "Buyer Name", "Price", Color.BLACK),
                createFormSection("🎓 Course Management", "Course Type (Online/Offline/Free)", "Student Name", "Duration", Color.BLACK)
        );

        // ===== Right Panel: Charts & Stats =====
        VBox rightPanel = new VBox(20);
        rightPanel.setPadding(new Insets(10, 10, 10, 30));
        rightPanel.setAlignment(Pos.TOP_CENTER);

        // ===== Pie Chart =====
        PieChart pieChart = new PieChart();
        PieChart.Data sellingData = new PieChart.Data("Selling", 65);
        PieChart.Data rentalData = new PieChart.Data("Rental", 25);
        PieChart.Data coursesData = new PieChart.Data("Courses", 10);

        pieChart.getData().addAll(sellingData, rentalData, coursesData);
        pieChart.setLegendVisible(true);
        pieChart.setLabelsVisible(true);
        pieChart.setTitle("Business Distribution");
        pieChart.setTitleSide(Side.TOP);
        pieChart.setPrefSize(700, 550);

        // White labels & title
        pieChart.setStyle("""
                -fx-pie-label-fill: white;
                -fx-chart-title-fill: white;
                -fx-legend-label-fill: white;
                """);

        animatePieChart(pieChart);

        // ===== Quick Stats =====
        Label statsTitle = new Label("📊 Quick Stats");
        statsTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        statsTitle.setTextFill(Color.WHITE);

        GridPane statsGrid = new GridPane();
        statsGrid.setHgap(20);
        statsGrid.setVgap(20);
        statsGrid.setPadding(new Insets(15));
        statsGrid.setAlignment(Pos.CENTER);
        statsGrid.setBackground(new Background(new BackgroundFill(Color.rgb(15, 32, 65), new CornerRadii(10), Insets.EMPTY)));
        statsGrid.setEffect(new DropShadow(5, Color.BLACK));

        statsGrid.add(createAnimatedStatCard("Total Rentals", 120), 0, 0);
        statsGrid.add(createAnimatedStatCard("Total Sales", 350), 1, 0);
        statsGrid.add(createAnimatedStatCard("Online Courses", 80), 0, 1);
        statsGrid.add(createAnimatedStatCard("Offline Courses", 45), 1, 1);

        rightPanel.getChildren().addAll(statsTitle, statsGrid, pieChart);

        // ===== Layout =====
        HBox centerContent = new HBox(30, leftPanel, rightPanel);
        root.setCenter(centerContent);

        Scene scene = new Scene(root, screenWidth,screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ===== Navigation Button =====
    private Button createNavButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-background-radius: 5;"));
        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white;"));
        return btn;
    }

    // ===== Logout Button =====
    private Button createLogoutButton() {
        Button logoutBtn = new Button("🚪 Logout");
        logoutBtn.setStyle("-fx-background-color: #d90429; -fx-text-fill: white; -fx-background-radius: 5;");

        logoutBtn.setOnAction(e -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Logout Confirmation");
            confirm.setHeaderText("Are you sure you want to logout?");
            confirm.setContentText("Click OK to logout, or Cancel to stay on the dashboard.");

            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    logoutMessage.setText("✅ Logout Successfully");
                }
            });
        });

        return logoutBtn;
    }

    // ===== Form Section =====
    private TitledPane createFormSection(String title, String field1, String field2, String field3, Color titleColor) {
        VBox formBox = new VBox(10);
        formBox.getChildren().addAll(
                createTextField(field1),
                createTextField(field2),
                createTextField(field3),
                createSubmitButton()
        );
        formBox.setPadding(new Insets(10));
        formBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(15, 32, 65), new CornerRadii(8), Insets.EMPTY)));
        formBox.setEffect(new DropShadow(5, Color.BLACK));

        TitledPane pane = new TitledPane(title, formBox);
        pane.setExpanded(false);
        pane.setTextFill(titleColor);
        pane.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));

        VBox.setMargin(pane, new Insets(5, 0, 5, 0));
        return pane;
    }

    private TextField createTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white;");
        return tf;
    }

    private Button createSubmitButton() {
        Button btn = new Button("Submit");
        btn.setStyle("-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-font-weight: bold;");
        btn.setPrefWidth(100);
        return btn;
    }

    // ===== Animated Stat Card =====
    private VBox createAnimatedStatCard(String title, int value) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(10));
        card.setAlignment(Pos.CENTER);
        card.setBackground(new Background(new BackgroundFill(
                Color.rgb(20, 40, 70), new CornerRadii(10), Insets.EMPTY)));
        card.setEffect(new DropShadow(5, Color.BLACK));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        titleLabel.setTextFill(Color.WHITE);

        Label valueLabel = new Label("0");
        valueLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        valueLabel.setTextFill(Color.LIGHTGREEN);

        Timeline timeline = new Timeline();
        final Integer[] count = {0};
        KeyFrame keyFrame = new KeyFrame(Duration.millis(20), event -> {
            if (count[0] < value) {
                count[0]++;
                valueLabel.setText(count[0].toString());
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(value);
        timeline.play();

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    // ===== Animate Pie Chart =====
    private void animatePieChart(PieChart chart) {
        for (PieChart.Data data : chart.getData()) {
            data.setPieValue(0);
        }
        Timeline timeline = new Timeline();
        double[] values = {65, 25, 10};
        int i = 0;
        for (PieChart.Data data : chart.getData()) {
            final int index = i;
            KeyFrame kf = new KeyFrame(Duration.seconds(1 + index * 0.5),
                    new KeyValue(data.pieValueProperty(), values[index]));
            timeline.getKeyFrames().add(kf);
            i++;
        }
        timeline.play();
    }
}
