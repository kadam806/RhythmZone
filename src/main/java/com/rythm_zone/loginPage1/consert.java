package com.rythm_zone.loginPage1;

import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class consert extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(
            new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0, Color.web("#0B101A")),
                new Stop(1, Color.web("#0B101A"))
            }),
            CornerRadii.EMPTY, Insets.EMPTY
        )));
        root.setPadding(new Insets(20));

        // === Top bar ===
        StackPane topBar = new StackPane();
        topBar.setPadding(new Insets(10));

        // Title at center
        Text title = new Text("🎼 Concert Showcase");
        title.setFont(Font.font("Brush Script MT", FontWeight.EXTRA_BOLD, 48));
        title.setFill(Color.CYAN);
        StackPane.setAlignment(title, Pos.CENTER);

        // Back button on left
        Button backButton = new Button("⬅ Back");
        backButton.setStyle(
            "-fx-background-color: #00b894; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-background-radius: 8px; " +
            "-fx-padding: 6 16;"
        );
        backButton.setOnAction(e -> {
            try {
                new home1().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        StackPane.setAlignment(backButton, Pos.CENTER_LEFT);
        StackPane.setMargin(backButton, new Insets(0, 0, 0, 10));

        topBar.getChildren().addAll(backButton, title);
        root.setTop(topBar);

        // === Slider area ===
        StackPane slider = new StackPane();
        slider.setMaxSize(800, 400);
        BorderPane.setAlignment(slider, Pos.CENTER);

        String[] images = {
            "/asset\\consert img1.jpg", "/asset\\consert1.jpg",
            "/asset\\consert2.jpg", "/asset\\consert3.jpg", "/asset\\consert4.jpg"
        };

        for (String imgPath : images) {
            ImageView ad = new ImageView(new Image(imgPath, true));
            ad.setPreserveRatio(true);
            ad.setSmooth(true);
            ad.setOpacity(0);
            ad.setFitWidth(1000);
            ad.setFitHeight(625);

            // Hover zoom effect
            ad.setOnMouseEntered(ev -> {
                ScaleTransition st = new ScaleTransition(Duration.millis(300), ad);
                st.setToX(1.03); st.setToY(1.03); st.play();
            });
            ad.setOnMouseExited(ev -> {
                ScaleTransition st = new ScaleTransition(Duration.millis(300), ad);
                st.setToX(1.0); st.setToY(1.0); st.play();
            });

            // Click to book
            ad.setOnMouseClicked((MouseEvent ev) -> {
                try {
                    new apply().start((Stage)((Node) ev.getSource()).getScene().getWindow());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            slider.getChildren().add(ad);
        }

        // Timeline for fading images
        Timeline timeline = new Timeline();
        ObservableList<Node> children = slider.getChildren();
        for (int i = 0; i < children.size(); i++) {
            final int idx = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(idx * 4),
                e -> fadeToFront(children, idx))
            );
        }
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        root.setCenter(slider);

        Scene scene = new Scene(root, screenWidth, screenHeight, Color.TRANSPARENT);
        primaryStage.setTitle("🎶 Concert Showcase");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fadeToFront(ObservableList<Node> ads, int index) {
        for (int i = 0; i < ads.size(); i++) {
            FadeTransition ft = new FadeTransition(Duration.seconds(1), ads.get(i));
            ft.setToValue(i == index ? 1.0 : 0.0);
            ft.play();
        }
    }
}
