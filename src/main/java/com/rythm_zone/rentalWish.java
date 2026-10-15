package com.rythm_zone;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class rentalWish extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Your Wishlist");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = screenBounds.getWidth();
        double height = screenBounds.getHeight();

        VBox layout = new VBox(30);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #0B101A;");

        // Title
        Label title = new Label("My Wishlist ❤️");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setTextFill(Color.WHITE);

        // Wishlist Card
        VBox card = createWishlistCard("SG MUSICAL Harmonium", "₹1,999", "/asset/harmonium.jpeg");

        // Back Button → Returns to rentalHarmo
        Button back = new Button("⬅ Back ");
        back.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        back.setStyle("-fx-background-color: #2d3436; -fx-text-fill: white;");
        back.setOnAction(e -> {
            rentalHarmo harmoPage = new rentalHarmo();
            try {
                harmoPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        layout.getChildren().addAll(title, card, back);

        Scene scene = new Scene(layout, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createWishlistCard(String name, String price, String imgPath) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(15));
        card.setSpacing(10);
        card.setStyle("-fx-background-color: #1C1F2A; -fx-background-radius: 10;");
        card.setEffect(new DropShadow(10, Color.BLACK));

        ImageView image = new ImageView(new Image(imgPath));
        image.setFitHeight(100);
        image.setFitWidth(100);
        image.setPreserveRatio(true);

        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.WHITE);

        Label priceLabel = new Label(price);
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        priceLabel.setTextFill(Color.LIGHTGREEN);

        card.getChildren().addAll(image, nameLabel, priceLabel);
        return card;
    }
}
