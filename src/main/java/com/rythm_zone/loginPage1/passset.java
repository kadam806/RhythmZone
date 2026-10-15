
package com.rythm_zone.loginPage1;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class passset extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();


    @Override
    public void start(Stage stage) {
         Label heading = new Label("Reset password!");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        heading.setTextFill(Color.web("#cb3083ff"));

        // Labels
        Label newPasswordLabel = new Label("New Password:");
        newPasswordLabel.setStyle("-fx-border color: Dark pink");
        Label confirmPasswordLabel = new Label("Confirm Password:");

        // Password Fields
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Enter new password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Re-enter password");

        // Result Label
        Label resultLabel = new Label();
        resultLabel.setTextFill(Color.RED);

        // Reset Button
        Button resetBtn = new Button("Reset Password");
        resetBtn.setTextFill(Color.PINK);
        resetBtn.setOnAction(e -> {
            String newPass = newPasswordField.getText();
            String confirmPass = confirmPasswordField.getText();

            if (newPass.isEmpty() || confirmPass.isEmpty()) {
                resultLabel.setText("Please fill in both fields.");
            } else if (!newPass.equals(confirmPass)) {
                resultLabel.setText("Passwords do not match.");
            } else {
                resultLabel.setTextFill(Color.GREEN);
                resultLabel.setText("Password reset successfully!");
                // Add your logic here to actually reset/save the password
                newPasswordField.clear();
                confirmPasswordField.clear();
            }
        });

        // Layout
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(300));
        root.setStyle("-fx-background-color: rgb(255, 239, 242); -fx-background-radius: 12;");

        root.getChildren().addAll(heading,
            newPasswordLabel, newPasswordField,
            confirmPasswordLabel, confirmPasswordField,
            resetBtn, resultLabel
        );

        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.setTitle("Reset Password");
        stage.show();
    }

    public Scene createResetScene(Stage stage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createResetScene'");
    }
}

