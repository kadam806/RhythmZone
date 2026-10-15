package com.rythm_zone.loginPage1;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class selling extends Application {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth(), screenHeight = screenBounds.getHeight();

    List<VBox> productList = new ArrayList<>();
    VBox productGrid;

    @Override
    public void start(Stage primaryStage) {
        // Back button
        Button backButton = new Button("⬅ Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        backButton.setOnAction(e -> {
            try {
                new home1().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Search field
        TextField searchField = new TextField();
        searchField.setPromptText("Search instrument...");
        searchField.setStyle("-fx-background-radius: 8; -fx-padding: 6;");
        searchField.textProperty().addListener((obs, o, v) -> filterProducts(v, primaryStage));

        HBox header = new HBox(10, backButton, searchField);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(10));

        // Auto-scrolling ad banner
        StackPane adBanner = createAutoScrollingAdBanner();

        // Title
        javafx.scene.control.Label title = new javafx.scene.control.Label("🎶 Rythm Zone – Shop the Beat 🎧");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        title.setTextFill(Color.WHITE);
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10));

        // Product grid
        productGrid = new VBox(30,
                createProductRow("DRUMS", "20,700₹", "/asset/drums.jpeg",
                        "TABLA", "3,500₹", "/asset/tablas.jpg",
                        "GUITAR", "33,490₹", "/asset/guitar.jpeg",
                        "FLUTE", "349₹", "/asset/flute.jpeg"),
                createProductRow("VEENA", "4,390₹", "/asset/veena.jpeg",
                        "HARMONIUM", "11,999₹", "/asset/harmonium.jpeg",
                        "BASURI", "209₹", "/asset/basuri.jpeg",
                        "MARACAS", "451₹", "/asset/maracas.jpeg"),
                createProductRow("DHOLAK", "1,799₹", "/asset/dholak.jpeg",
                        "TANPURA", "2,000₹", "/asset/tanpura.jpeg",
                        "KANJIRA", "1,696₹", "/asset/kanjira.jpeg",
                        "EKTARA", "409₹", "/asset/ektara.jpeg"),
                createProductRow("SARANGI", "30,000₹", "/asset/sarangi.jpeg",
                        "SAROD", "49,500₹", "/asset/sarod.jpeg",
                        "SHEHNAI", "667₹", "/asset/shehnai.jpeg",
                        "VIOLIN", "3,499₹", "/asset/violins.jpeg")
        );
        productGrid.setAlignment(Pos.CENTER);

        // Layout
        VBox layout = new VBox(20, header, adBanner, titleBox, productGrid);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #0B101A;");

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #0B101A;");

        Scene scene = new Scene(scrollPane, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rythm Zone - Selling");
        primaryStage.show();
    }

    // Create a row of products
    private HBox createProductRow(
            String name1, String price1, String img1,
            String name2, String price2, String img2,
            String name3, String price3, String img3,
            String name4, String price4, String img4) {

        VBox p1 = createProduct(name1, price1, img1);
        VBox p2 = createProduct(name2, price2, img2);
        VBox p3 = createProduct(name3, price3, img3);
        VBox p4 = createProduct(name4, price4, img4);

        HBox row = new HBox(20, p1, p2, p3, p4);
        row.setAlignment(Pos.CENTER);
        return row;
    }

    // Create individual product card
    private VBox createProduct(String name, String price, String imgPath) {
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
        img.setFitWidth(180);
        img.setPreserveRatio(true);

        javafx.scene.control.Label nameL = new javafx.scene.control.Label(name);
        nameL.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nameL.setTextFill(Color.WHITE);

        javafx.scene.control.Label priceL = new javafx.scene.control.Label(price);
        priceL.setFont(Font.font("Arial", 14));
        priceL.setTextFill(Color.LIGHTGRAY);

        VBox box = new VBox(10, img, nameL, priceL);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #1E1E2E; -fx-background-radius: 10;");
        productList.add(box);

        box.setOnMouseClicked(e -> {
            try {
                new home().start(stageOf(box));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return box;
    }

    // Auto-scrolling ad banner
    private StackPane createAutoScrollingAdBanner() {
        ImageView imgView = new ImageView();
        imgView.setFitWidth(800);
        imgView.setFitHeight(200);
        imgView.setPreserveRatio(false);

        List<Image> ads = List.of(
                new Image(getClass().getResourceAsStream("/asset/ad1.jpg")),
                new Image(getClass().getResourceAsStream("/asset/ad2.jpg")),
                new Image(getClass().getResourceAsStream("/asset/ad3.jpg"))
        );

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), e -> {
            int index = (int) ((System.currentTimeMillis() / 3000) % ads.size());
            imgView.setImage(ads.get(index));
        }), new KeyFrame(Duration.seconds(3)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        StackPane banner = new StackPane(imgView);
        banner.setStyle("-fx-background-color: black;");
        return banner;
    }

    // Search filter
    private void filterProducts(String text, Stage stage) {
        boolean found = false;
        if (text == null || text.trim().isEmpty()) {
            productList.forEach(p -> p.setVisible(true));
            return;
        }
        String q = text.toLowerCase();
        for (VBox p : productList) {
            javafx.scene.control.Label name = (javafx.scene.control.Label) p.getChildren().get(1);
            boolean ok = name.getText().toLowerCase().contains(q);
            p.setVisible(ok);
            if (ok) found = true;
        }
        if (!found) fetchFromGoogle(text, stage);
    }

    // Fetch from Unsplash API
    private void fetchFromGoogle(String query, Stage stage) {
        String API_KEY = "e62Wby7kSL7txOlXjMoDmPQMQ7Z5TQYFsdMeOavtRjk";
        String url = String.format(
                "https://api.unsplash.com/photos/random?client_id=%s&query=%s", API_KEY, query);

        new Thread(() -> {
            try (InputStreamReader rd = new InputStreamReader(new URL(url).openStream())) {
                JsonObject json = JsonParser.parseReader(rd).getAsJsonObject();
                String imgUrl = json.get("urls").getAsJsonObject().get("regular").getAsString();

                javafx.application.Platform.runLater(() -> {
                    VBox extra = createDynamicProduct(query.toUpperCase(), "Price not available", imgUrl);
                    productGrid.getChildren().add(extra);
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    // Create a dynamic product from API
    private VBox createDynamicProduct(String name, String price, String imgURL) {
        ImageView img = new ImageView(new Image(imgURL, 180, 0, true, true));
        javafx.scene.control.Label nameL = new javafx.scene.control.Label(name);
        nameL.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nameL.setTextFill(Color.WHITE);

        javafx.scene.control.Label priceL = new javafx.scene.control.Label(price);
        priceL.setFont(Font.font("Arial", 14));
        priceL.setTextFill(Color.LIGHTGRAY);

        VBox box = new VBox(10, img, nameL, priceL);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #1E1E2E; -fx-background-radius:10;");
        box.setOnMouseClicked(e -> {
            try {
                new home().start(stageOf(box));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        productList.add(box);
        return box;
    }

    private Stage stageOf(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
