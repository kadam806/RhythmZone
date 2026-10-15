package com.rythm_zone.loginPage1;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class payment extends Application {
     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();


    @Override
    public void start(Stage primaryStage) {
         Button backButton = new Button("⬅ Back");
         backButton.setAlignment(Pos.TOP_RIGHT);
         backButton.setTranslateX(-620);
        backButton.setStyle(
    "-fx-border-color: #FFEFF2;" +
    "-fx-border-width: 4px;" +
    "-fx-background-color: white;"
);
        backButton.setOnAction(e -> {
            home sellingPage = new home();
            try {
                sellingPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Font labelFont = Font.font("System", 18);
        Font fieldFont = Font.font("System", 16);
        Font buttonFont = Font.font("System", FontWeight.BOLD, 20);

        ToggleGroup tg = new ToggleGroup();
        RadioButton rbCredit = new RadioButton("Credit Card");
        RadioButton rbDebit  = new RadioButton("Debit Card");
        RadioButton rbQR     = new RadioButton("QR Code");
        for (RadioButton rb : new RadioButton[]{rbCredit, rbDebit, rbQR}) {
            rb.setToggleGroup(tg);
            rb.setTextFill(Color.WHITE);
            rb.setFont(labelFont);
        }

        HBox opts = new HBox(20, rbCredit, rbDebit, rbQR);
        opts.setAlignment(Pos.CENTER);

        GridPane cardPane = new GridPane();
        cardPane.setHgap(12); cardPane.setVgap(12);
        cardPane.setVisible(false);
        cardPane.setAlignment(Pos.CENTER);

        TextField tfNumber = new TextField(); 
        TextField tfExpiry = new TextField(); 
        TextField tfCVV    = new TextField(); 
        for (TextField tf : new TextField[]{tfNumber, tfExpiry, tfCVV}) {
            tf.setFont(fieldFont);
            tf.setStyle("-fx-text-fill: white; "
                      + "-fx-prompt-text-fill: #cccccc; "
                      + "-fx-background-color: #2a3b52;");
        }
        tfNumber.setPromptText("1234 5678 9012 3456");
        tfExpiry.setPromptText("MM/YY");
        tfCVV.setPromptText("CVV");

        Label lblNum    = new Label("Number:");
        Label lblExpiry = new Label("Expiry:");
        Label lblCVV    = new Label("CVV:");
        for (Label lbl : new Label[]{lblNum, lblExpiry, lblCVV}) {
            lbl.setTextFill(Color.WHITE);
            lbl.setFont(labelFont);
        }
        cardPane.add(lblNum,    0, 0);
        cardPane.add(tfNumber,  1, 0);
        cardPane.add(lblExpiry, 0, 1);
        cardPane.add(tfExpiry,  1, 1);
        cardPane.add(lblCVV,    0, 2);
        cardPane.add(tfCVV,     1, 2);

        VBox qrPane = new VBox(5);
        qrPane.setAlignment(Pos.CENTER);
        qrPane.setVisible(false);
        Label qrLabel = new Label("Scan QR Code:");
        qrLabel.setTextFill(Color.WHITE);
        qrLabel.setFont(labelFont);
        ImageView qrView = new ImageView();
        qrView.setFitWidth(220);
        qrView.setFitHeight(220);
        qrPane.getChildren().addAll(qrLabel, qrView);

        Button btnPay = new Button("Pay Now");
        btnPay.setOnAction(e -> {
    Toggle sel = tg.getSelectedToggle();
    if (sel == null) {
        showAlert(Alert.AlertType.ERROR, "Error", "Please select a payment method.");
        return;
    }
    String method = ((RadioButton) sel).getText();
    if (method.contains("Card")) {
        if (tfNumber.getText().isBlank() ||
            tfExpiry.getText().isBlank() ||
            tfCVV.getText().isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Incomplete", "Please fill all card details.");
            return;
        }
        if (method.equals("Debit Card")) {
            new successPay(method).showAndWait();
            return;
        }
    }
    // fallback or other methods...
});

        btnPay.setFont(buttonFont);
        btnPay.setStyle("-fx-background-color: #0f68a8; -fx-text-fill: white;");

        Label header = new Label("Choose Payment Method");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font("System", FontWeight.BOLD, 24));

        VBox root = new VBox(25, backButton, header, opts, cardPane, qrPane, btnPay);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #1a2938;");

        tg.selectedToggleProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                String txt = ((RadioButton)newV).getText();
                boolean isCard = txt.contains("Card");
                cardPane.setVisible(isCard);
                qrPane.setVisible(txt.contains("QR Code"));
                if (txt.contains("QR Code")) {
                    qrView.setImage(new Image(getClass().getResourceAsStream("/asset\\WhatsApp Image 2025-07-27 at 12.58.15.jpeg")));
                }
            }
        });

        btnPay.setOnAction(e -> {
            Toggle sel = tg.getSelectedToggle();
            if (sel == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a payment method.");
                return;
            }
            String method = ((RadioButton) sel).getText();
            if (method.contains("Card") && (
                    tfNumber.getText().isBlank() ||
                    tfExpiry.getText().isBlank() ||
                    tfCVV.getText().isBlank())) {
                showAlert(Alert.AlertType.WARNING, "Incomplete", "Please fill all card details.");
                return;
            }
            new successPay(method).showAndWait();
        });

        Scene scene = new Scene(root, screenWidth , screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Payment");
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
