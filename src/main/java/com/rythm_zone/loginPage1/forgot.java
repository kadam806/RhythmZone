package com.rythm_zone.loginPage1;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class forgot extends Application {
     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Forgot Password - Rhythm Zone");

        // ===== Root Layout =====
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(
                Color.rgb(10, 25, 47), CornerRadii.EMPTY, Insets.EMPTY)));

        // ===== Title =====
        Label title = new Label("🔑 Forgot Password");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setTextFill(Color.WHITE);

        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(30, 0, 20, 0));
        root.setTop(titleBox);

        // ===== Forgot Password Form =====
        VBox form = new VBox(15);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(30));
        form.setBackground(new Background(new BackgroundFill(
                Color.rgb(15, 32, 65), new CornerRadii(10), Insets.EMPTY)));
        form.setEffect(new DropShadow(8, Color.BLACK));

        TextField usernameField = createStyledTextField("Enter Username", false);
        TextField emailField = createStyledTextField("Enter Registered Email", false);

        // Security Question
        ComboBox<String> securityQuestion = new ComboBox<>();
        securityQuestion.getItems().addAll(
                "What is your pet's name?",
                "What is your mother's maiden name?",
                "What was your first school's name?"
        );
        securityQuestion.setPromptText("Select Security Question");
        securityQuestion.setMaxWidth(280);
        securityQuestion.setStyle("-fx-background-radius: 8;");

        TextField securityAnswer = createStyledTextField("Security Answer", false);

        PasswordField newPassword = (PasswordField) createStyledTextField("New Password", true);
        PasswordField confirmPassword = (PasswordField) createStyledTextField("Confirm New Password", true);

        // ===== Reset Password Button =====
        Button resetBtn = new Button("Reset Password");
        resetBtn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        resetBtn.setStyle("-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-background-radius: 8;");
        resetBtn.setPrefWidth(280);
        resetBtn.setOnMouseEntered(e -> resetBtn.setStyle(
                "-fx-background-color: #0077B6; -fx-text-fill: white; -fx-background-radius: 8;"));
        resetBtn.setOnMouseExited(e -> resetBtn.setStyle(
                "-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-background-radius: 8;"));

        Label messageLabel = new Label("");
        messageLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        messageLabel.setTextFill(Color.LIGHTGREEN);

        // Action for Reset Password
        resetBtn.setOnAction(e -> {
            if (newPassword.getText().equals(confirmPassword.getText()) && !newPassword.getText().isEmpty()) {
                messageLabel.setText("✅ Password reset successfully!");
                FadeTransition ft = new FadeTransition(Duration.seconds(1), messageLabel);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
            } else {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("❌ Passwords do not match!");
            }
        });

        // ===== Back to Login Link =====
        Label backToLogin = new Label("🔙 Back to Login");
        backToLogin.setTextFill(Color.LIGHTBLUE);
        backToLogin.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        backToLogin.setOnMouseEntered(e -> backToLogin.setTextFill(Color.CYAN));
        backToLogin.setOnMouseExited(e -> backToLogin.setTextFill(Color.LIGHTBLUE));
        backToLogin.setOnMouseClicked(e -> {
            try {
                new adminLogin().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        form.getChildren().addAll(
                usernameField,
                emailField,
                securityQuestion,
                securityAnswer,
                newPassword,
                confirmPassword,
                resetBtn,
                messageLabel,
                backToLogin
        );

        root.setCenter(form);

        // ===== Scene =====
        Scene scene = new Scene(root, screenWidth ,screenHeight );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ===== Utility Method =====
    private TextField createStyledTextField(String placeholder, boolean isPassword) {
        TextField field;
        if (isPassword) {
            field = new PasswordField();
            field.setStyle("-fx-prompt-text-fill: #cccccc; -fx-text-fill: black; -fx-background-radius: 8; -fx-background-color: white;");
        } else {
            field = new TextField();
            field.setStyle("-fx-prompt-text-fill: #cccccc; -fx-text-fill: black; -fx-background-radius: 8; -fx-background-color: white;");
        }
        field.setPromptText(placeholder);
        field.setMaxWidth(280);
        return field;
    }
}

