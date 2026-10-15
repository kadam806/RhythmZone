package com.rythm_zone.loginPage1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class successPay extends Dialog<Void> {
   Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
   

    public successPay(String method) {
        setTitle("✅ Payment Successful");
        setHeaderText(null);

        Label emoji = new Label("✅");
        emoji.setFont(Font.font(60));

        Label msg = new Label("Debit Card payment completed!");
        msg.setFont(Font.font("System", 20));
        msg.setStyle("-fx-text-fill: white;");

        HBox content = new HBox(20, emoji, msg);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setPadding(new Insets(20));

        DialogPane pane = getDialogPane();
        pane.setContent(content);
        pane.getButtonTypes().setAll(ButtonType.OK);

        // Style the dialog pane
        pane.setStyle("-fx-background-color: #2a3b52;");

        // Style OK button
        Button ok = (Button) pane.lookupButton(ButtonType.OK);
        ok.setFont(Font.font("System", 18));
        ok.setStyle(
          "-fx-background-color: linear-gradient(to bottom right, #4CAF50, #2E7D32);"
        + "-fx-text-fill: white; -fx-padding: 8 20;"
        + "-fx-background-radius: 6;"
        );

        setResizable(false);
    }
}
