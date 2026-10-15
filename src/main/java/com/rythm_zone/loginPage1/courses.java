package com.rythm_zone.loginPage1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class courses extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Instrument Courses");

        // Background image
        Image backgroundImage = new Image("file:syntax-trio\\rythm\\src\\main\\resources\\asset\\img4.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(screenWidth);
        backgroundView.setFitHeight(screenHeight);
        backgroundView.setPreserveRatio(false);

        // Main layout
        VBox mainLayout = new VBox(30);
        mainLayout.setPadding(new Insets(40));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: #0B101A;");

        // Titles (clickable)
        Text txOnline = createTitle("🎧 Online Courses", "https://www.bmusician.com/blog/top-10-music-courses-online-with-certificates/#r");
        Text txOffline = createTitle("🎻 Offline Courses", "https://www.bmusician.com/about-us/");
        Text txFree = createTitle("📚 Free Tutorials", "https://www.bmusician.com/blog/top-10-music-courses-online-with-certificates/#r");

        // Course card layout
        HBox courseCards = new HBox(30);
        courseCards.setAlignment(Pos.CENTER);
        courseCards.getChildren().addAll(
                createCourseCard("Offline", "Lectures"),
                createCourseCard("Online", "Lectures"),
                createCourseCard("Free Tutorial", "Lectures")
        );

        // Back Button
        Button backButton = createStyledButton("← Back");
        backButton.setOnAction(e -> {
            new home1().start(primaryStage);
        });

        // Add all to layout
        mainLayout.getChildren().addAll(txOnline, txOffline, txFree, courseCards, backButton);

        StackPane root = new StackPane(backgroundView, mainLayout);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Creates clickable section titles
    private Text createTitle(String content, String url) {
        Text text = new Text(content);
        text.setFont(Font.font("Georgia", FontWeight.BOLD, 32));
        text.setFill(Color.WHITE);
        text.setOnMouseClicked(e -> openWebpage(url));
        text.setStyle("-fx-cursor: hand;");
        return text;
    }

    // Creates dark-themed course cards with click actions
    private VBox createCourseCard(String title, String subtitle) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(12));
        card.setAlignment(Pos.CENTER);
        card.setStyle(
            "-fx-background-color: #1e1e1e;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #2f3542;" +
            "-fx-border-width: 1;" +
            "-fx-cursor: hand;"
        );
        card.setPrefSize(150, 100);

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.setTextFill(Color.LIGHTGRAY);
        subtitleLabel.setFont(Font.font("Arial", 12));

        card.getChildren().addAll(titleLabel, subtitleLabel);

        // Trim title for matching
        String normalizedTitle = title.trim();
        if (normalizedTitle.equalsIgnoreCase("Online")) {
            card.setOnMouseClicked(e -> openWebpage(
                "https://www.bmusician.com/blog/top-10-music-courses-online-with-certificates/#r"
            ));
        } else if (normalizedTitle.equalsIgnoreCase("Free Tutorial")) {
            card.setOnMouseClicked(e -> openWebpage(
                "https://www.bmusician.com/blog/top-10-music-courses-online-with-certificates/#r"
            ));
        } else if (normalizedTitle.equalsIgnoreCase("Offline")) {
            card.setOnMouseClicked(e -> openWebpage(
                "https://www.bmusician.com/about-us/"
            ));
        }

        return card;
    }

    // Opens a webpage in default browser
    private void openWebpage(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Creates consistent styled buttons
    private Button createStyledButton(String text) {
        Button btn = new Button(text);
        btn.setFont(Font.font("Georgia", FontWeight.BOLD, 14));
        btn.setStyle(
            "-fx-background-color: #00b894;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 8 20 8 20;"
        );
        return btn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
