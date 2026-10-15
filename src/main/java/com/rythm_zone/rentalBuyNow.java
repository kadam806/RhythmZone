package com.rythm_zone;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class rentalBuyNow extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    Label locationLabel = new Label("Fetching location...");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Buy Now - Musical Instrument");

        // Back Button
        Button backButton = new Button("⬅ Back to Product Page");
        backButton.setAlignment(Pos.TOP_RIGHT);
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: #efeef1ff; -fx-text-fill: white; -fx-border-width: 2px;");
        backButton.setOnAction(e -> {
            rentalHarmo homePage = new rentalHarmo();
            try {
                homePage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        HBox hb=new HBox(backButton);

        // Title
        Label titleLabel = new Label("Harmonium");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setTextFill(Color.WHITE);

        // Image
        ImageView productImage = new ImageView(new Image("asset\\harmonium.jpeg"));
        productImage.setFitWidth(300);
        productImage.setFitHeight(200);
        productImage.setPreserveRatio(true);

        // Price
        Label priceLabel = new Label("Price: ₹1,999");
        priceLabel.setFont(Font.font("Arial", 18));
        priceLabel.setTextFill(Color.LIGHTGREEN);

        // Quantity
        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setTextFill(Color.WHITE);
        Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1);

        Label totalLabel = new Label("Total: ₹1,999");
        totalLabel.setFont(Font.font("Arial", 16));
        totalLabel.setTextFill(Color.WHITE);

        quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            int total = newVal * 12500;
            totalLabel.setText("Total: ₹" + total);
        });

        // Form Fields
        TextField addressField = new TextField();
        addressField.setPromptText("Enter Full Address");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email Address");

        TextField mobileField = new TextField();
        mobileField.setPromptText("Enter Mobile Number");

        Label addressLabel = new Label("Shipping Address:");
        Label emailLabel = new Label("Email:");
        Label mobileLabel = new Label("Mobile Number:");

        Label[] formLabels = { addressLabel, emailLabel, mobileLabel };
        for (Label lbl : formLabels) {
            lbl.setTextFill(Color.WHITE);
        }

        VBox formBox = new VBox(10,
                addressLabel, addressField,
                emailLabel, emailField,
                mobileLabel, mobileField
        );
        formBox.setPadding(new Insets(10));
        formBox.setMaxWidth(300);

        // Buy Now Button
        Button buyNowBtn = new Button("Buy Now");
        buyNowBtn.setStyle("-fx-background-color: #13103cff; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        buyNowBtn.setOnAction(e -> {
            if (addressField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                mobileField.getText().isEmpty()) {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Missing Details");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Please fill in all the required fields.");
                errorAlert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Order Placed");
                alert.setHeaderText(null);
                alert.setContentText("Thank you for your order!\nWe will deliver your Harmonium soon.\n" + locationLabel.getText());
                alert.showAndWait();
            }
        });

        // Location Label Styling
        locationLabel.setTextFill(Color.LIGHTGRAY);
        locationLabel.setFont(Font.font("Arial", 12));

        VBox mainBox = new VBox(10,
            hb, titleLabel, productImage, priceLabel,
            quantityLabel, quantitySpinner, totalLabel,
            formBox, locationLabel, buyNowBtn
        );
        mainBox.setPadding(new Insets(30));
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.setStyle("-fx-background-color: #0B101A;");

        Scene scene = new Scene(mainBox, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Fetch user location
        fetchLiveLocation();
    }

    private void fetchLiveLocation() {
        new Thread(() -> {
            try {
                URL url = new URL("http://ip-api.com/json");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                Scanner scanner = new Scanner(new InputStreamReader(conn.getInputStream()));
                StringBuilder jsonText = new StringBuilder();
                while (scanner.hasNext()) {
                    jsonText.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject obj = new JSONObject(jsonText.toString());
                String city = obj.getString("city");
                String region = obj.getString("regionName");
                String country = obj.getString("country");
                String zip = obj.getString("zip");

                String location = city + ", " + region + ", " + country + " - " + zip;

                javafx.application.Platform.runLater(() -> {
                    locationLabel.setText("Your Location: " + location);
                });

            } catch (Exception e) {
                javafx.application.Platform.runLater(() -> {
                    locationLabel.setText("Unable to fetch location.");
                });
                e.printStackTrace();
            }
        }).start();
    }
}



