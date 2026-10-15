package com.rythm_zone.loginPage1;

import com.rythm_zone.rentalHarmo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OffersUI extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        // Back Button
        Button backButton = new Button("⬅ Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        backButton.setOnAction(e -> {
            try {
                new home1().start(primaryStage); // Navigate to home1
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox header = new HBox(backButton);
        header.setAlignment(Pos.TOP_LEFT);
        header.setPadding(new Insets(10));

        // Stylish Title
        Label title = new Label("🎼 Melody Market On Rent – Discover Your Sound 🎧");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(20, 0, 20, 0));
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);

        // Horizontal Auto-Scroll Banner
        ScrollPane banner = createAutoScrollingBanner();

        // Product Rows
        VBox productSection = new VBox(30,
                createProductRow("🎸 Guitar", "₹490", "/asset/guitar.jpeg",
                        "🥁 Drums", "₹700", "/asset/drums.jpeg",
                        "🪘 Tabla", "₹500", "/asset/tablas.jpg",
                        "🎶 Flute", "₹49", "/asset/flute.jpeg"),

                createProductRow("🎻 Violin", "₹499", "/asset/violins.jpeg",
                        "🪕 Ektara", "₹109", "/asset/ektara.jpeg",
                        "🎺 Shehnai", "₹167", "/asset/shehnai.jpeg",
                        "🪗 Harmonium", "₹1,999", "/asset/harmonium.jpeg")
        );
        productSection.setAlignment(Pos.CENTER);

        // Main Layout
        VBox layout = new VBox(20, header, banner, titleBox, productSection);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #0B101A;");

        ScrollPane root = new ScrollPane(layout);
        root.setFitToWidth(true);
        root.setStyle("-fx-background: transparent;");
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setTitle("Offers - Rythm Zone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Updated: No borders or shadows
    private ScrollPane createAutoScrollingBanner() {
        HBox imageRow = new HBox(20);
        imageRow.setPadding(new Insets(10));
        imageRow.setAlignment(Pos.CENTER_LEFT);

        String[] images = {
                "/asset/rental1.jpg",
                "/asset/rental2.png",
                "/asset/rental3.jpg",
                "/asset/rental4.png",
                "/asset/rental5.jpg"
        };

        for (String path : images) {
            ImageView img = new ImageView(new Image(getClass().getResourceAsStream(path)));
            img.setFitHeight(200);
            img.setFitWidth(350);
            img.setPreserveRatio(false);
            img.setStyle("-fx-background-color: transparent;"); // Removed drop shadow
            imageRow.getChildren().add(img);
        }

        ScrollPane scrollPane = new ScrollPane(imageRow);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(220);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: transparent;"
        );

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            double hVal = scrollPane.getHvalue();
            scrollPane.setHvalue(hVal + 0.001);
            if (hVal >= 1.0) scrollPane.setHvalue(0);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return scrollPane;
    }

    private HBox createProductRow(String name1, String price1, String img1,
                                  String name2, String price2, String img2,
                                  String name3, String price3, String img3,
                                  String name4, String price4, String img4) {
        VBox p1 = createProduct(name1, price1, img1);
        VBox p2 = createProduct(name2, price2, img2);
        VBox p3 = createProduct(name3, price3, img3);
        VBox p4 = createProduct(name4, price4, img4);

        HBox row = new HBox(40, p1, p2, p3, p4);
        row.setAlignment(Pos.CENTER);
        return row;
    }

    private VBox createProduct(String name, String price, String imagePath) {
        VBox box = new VBox();
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        img.setFitHeight(180);
        img.setPreserveRatio(true);

        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nameLabel.setTextFill(Color.WHITE);

        Label priceLabel = new Label(price);
        priceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        priceLabel.setTextFill(Color.LIGHTGRAY);

        box.getChildren().addAll(img, nameLabel, priceLabel);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #1E1E2E; -fx-background-radius: 10;");

        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #2C2C3E; -fx-background-radius: 10; -fx-cursor: hand;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: #1E1E2E; -fx-background-radius: 10;"));

        // Navigate to rentalHarmo when clicking Harmonium
        box.setOnMouseClicked(e -> {
            try {
                if (name.contains("Harmonium")) {
                    new rentalHarmo().start((Stage) box.getScene().getWindow());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return box;
    }
}
