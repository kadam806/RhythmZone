package com.rythm_zone;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;

import java.util.ArrayList;
import java.util.List;

import com.rythm_zone.loginPage1.OffersUI;

public class rentalHarmo extends Application {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    private final List<CartItem> cartItems = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SG MUSICAL Harmonium");

        // Back Button
        Button backButton = new Button("⬅ Back ");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        backButton.setOnAction(e -> {
            new OffersUI().start(primaryStage);
        });

        // Product Image
        ImageView productImage = new ImageView(new Image("/asset/harmonium.jpeg"));
        productImage.setFitHeight(350);
        productImage.setFitWidth(350);
        productImage.setPreserveRatio(true);
        productImage.setStyle("-fx-background-color: #1C1F2A; -fx-padding: 10; -fx-border-color: #2C3440; -fx-border-width: 2;");

        // Title
        Text title = new Text("SG MUSICAL Harmonium with 42-Keys...");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        title.setFill(Color.WHITE);

        // Price Info
        HBox priceBox = new HBox(10,
                createLabel("-33%", Color.RED),
                createLabel("₹1,999", Color.WHITE, true),
                createLabel("M.R.P.: ₹2,222", Color.GRAY, false, true));
        priceBox.setAlignment(Pos.CENTER_LEFT);

        // EMI Info
        Label emiInfo = createLabel("EMI starts at ₹723. No Cost EMI available.", Color.LIGHTGRAY);

        // Offers
        HBox offerInfo = new HBox(20,
                createOfferCard("Cashback", "Upto ₹49 cashback...", "1 offer"),
                createOfferCard("Bank Offer", "Upto ₹500 off on HDFC...", "10 offers"),
                createOfferCard("No Cost EMI", "Save ₹75...", "1 offer"));
        offerInfo.setAlignment(Pos.CENTER);
        offerInfo.setPadding(new Insets(10));

        // Delivery Info
        Label delivery = createLabel("FREE delivery on Saturday.", Color.LIGHTGRAY);
        Label location = createLabel("Delivering to Pune 411001", Color.LIGHTGRAY);
        Label stock = createLabel("In Stock", Color.LIMEGREEN, true);

        // Add to Cart Button
        Button addToCart = new Button("Add to Cart");
        addToCart.setPrefWidth(150);
        addToCart.setStyle("-fx-background-color: #00b894; -fx-text-fill: white;");
        addToCart.setOnAction(e -> {
            cartItems.add(new CartItem("SG MUSICAL Harmonium", 1999));
            showCartPopup();
        });

        // Buy Now Button
        Button buyNow = new Button("Buy Now");
        buyNow.setPrefWidth(150);
        buyNow.setStyle("-fx-background-color: #ffaa00; -fx-text-fill: white;");
        buyNow.setOnAction(e -> {
            rentalBuyNow buyNowPage = new rentalBuyNow();
            try {
                buyNowPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Wishlist Button → Opens rentalWish
        Button wishList = new Button("Add to Wish List");
        wishList.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        wishList.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        wishList.setOnAction(e -> {
            rentalWish wishPage = new rentalWish();
            try {
                wishPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox buttonBox1 = new VBox(delivery, location, stock,
                new HBox(10, addToCart),
                new HBox(10, buyNow),
                new HBox(10, wishList));
        buttonBox1.setSpacing(10);
        buttonBox1.setPadding(new Insets(10));
        buttonBox1.setAlignment(Pos.CENTER_LEFT);

        // Product Info Specs
        VBox infoBox = new VBox(10);
        infoBox.setPadding(new Insets(10));
        infoBox.getChildren().addAll(
                createLabel("Famous Flute Harmonium", Color.WHITE, true, false, 20),
                createSpecs(),
                createLabel("About this item:", Color.WHITE, true, false, 16),
                createFeaturesInfo()
        );

        // Bottom Info
        HBox bottomInfo = new HBox(50,
                createLabel("🛵 Free Delivery", Color.LIGHTGRAY),
                createLabel("🔁 10 Days Replacement", Color.LIGHTGRAY),
                createLabel("🛡 Warranty Policy", Color.LIGHTGRAY),
                createLabel("🔒 Secure Transaction", Color.LIGHTGRAY));
        bottomInfo.setAlignment(Pos.CENTER_LEFT);
        bottomInfo.setPadding(new Insets(20));

        VBox rightPane = new VBox(10, title, priceBox, emiInfo, offerInfo, infoBox, bottomInfo);
        rightPane.setPadding(new Insets(10));

        HBox root = new HBox(20, productImage, rightPane, buttonBox1);
        root.setPadding(new Insets(20));

        VBox vb1 = new VBox(backButton, root);
        vb1.setSpacing(10);
        vb1.setStyle("-fx-background-color: #0B101A;");

        ScrollPane scrollPane = new ScrollPane(vb1);
        scrollPane.setStyle("-fx-background: #0B101A;");
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showCartPopup() {
        Stage cartStage = new Stage();
        cartStage.initModality(Modality.APPLICATION_MODAL);
        cartStage.setTitle("Your Cart");

        VBox cartContent = new VBox(10);
        cartContent.setPadding(new Insets(15));
        cartContent.setStyle("-fx-background-color: #1C1F2A;");

        int total = 0;
        for (CartItem item : cartItems) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            Label name = createLabel(item.name, Color.WHITE);
            Label price = createLabel("₹" + item.price, Color.LIGHTGREEN);
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            row.getChildren().addAll(name, spacer, price);
            cartContent.getChildren().add(row);
            total += item.price;
        }

        Label totalLabel = createLabel("Total: ₹" + total, Color.CYAN, true);
        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        cartContent.getChildren().add(totalLabel);

        Scene cartScene = new Scene(cartContent, 400, 300);
        cartStage.setScene(cartScene);
        cartStage.show();
    }

    private GridPane createSpecs() {
        GridPane specs = new GridPane();
        specs.setHgap(10);
        specs.setVgap(5);
        specs.setPadding(new Insets(20));
        String[][] specData = {
                {"Colour:", "Natural"},
                {"Brand:", "Famous Flute"},
                {"Material:", "Wood"},
                {"Model Name:", "Famous Flute Harmonium"},
                {"Dimensions:", "61 x 30.5 x 30.5 cm"}
        };
        for (int i = 0; i < specData.length; i++) {
            specs.addRow(i, createLabel(specData[i][0], Color.LIGHTGRAY), createLabel(specData[i][1], Color.WHITE));
        }
        return specs;
    }

    private VBox createFeaturesInfo() {
        VBox features = new VBox(5);
        features.setPadding(new Insets(10));
        String[] lines = {
                "• Double fold bellow", "• Finish lacquer",
                "• Total 39 keys", "• Keyboard cover with lock, a440 pitch"
        };
        for (String text : lines) {
            features.getChildren().add(createLabel(text, Color.LIGHTGRAY));
        }
        return features;
    }

    private VBox createOfferCard(String title, String description, String offersCount) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(10));
        card.setAlignment(Pos.TOP_LEFT);
        card.setPrefSize(230, 120);
        card.setStyle("-fx-background-color: #1C1F2A; -fx-border-color: #2C3440; -fx-border-radius: 8; -fx-background-radius: 8;");
        card.getChildren().addAll(
                createLabel(title, Color.WHITE, true),
                createLabel(description, Color.LIGHTGRAY),
                createLabel(offersCount, Color.LIGHTBLUE)
        );
        return card;
    }

    private Label createLabel(String text, Color color) {
        return createLabel(text, color, false);
    }

    private Label createLabel(String text, Color color, boolean bold) {
        return createLabel(text, color, bold, false);
    }

    private Label createLabel(String text, Color color, boolean bold, boolean strikethrough) {
        return createLabel(text, color, bold, strikethrough, 14);
    }

    private Label createLabel(String text, Color color, boolean bold, boolean strikethrough, int fontSize) {
        Label label = new Label(text);
        label.setTextFill(color);
        label.setFont(Font.font("Arial", bold ? FontWeight.BOLD : FontWeight.NORMAL, fontSize));
        label.setStyle(strikethrough ? "-fx-strikethrough: true;" : "");
        return label;
    }

    static class CartItem {
        String name;
        int price;

        CartItem(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }
}
