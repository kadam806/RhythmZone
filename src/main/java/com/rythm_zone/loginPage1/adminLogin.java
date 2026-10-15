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

public class adminLogin extends Application {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Login - Rhythm Zone");

        // ===== Root Background =====
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(
                Color.rgb(10, 25, 47), CornerRadii.EMPTY, Insets.EMPTY)));

        // ===== Login Form Container =====
        VBox loginBox = new VBox(15);
        loginBox.setPadding(new Insets(30));
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setMaxWidth(350);
        loginBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(15, 32, 65, 0.95), new CornerRadii(15), Insets.EMPTY)));
        loginBox.setEffect(new DropShadow(20, Color.BLACK));

        // ===== Title =====
        Label title = new Label("Admin Login");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setTextFill(Color.WHITE);

        // ===== Username Field =====
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle(
                "-fx-prompt-text-fill: #cccccc; -fx-text-fill: black; -fx-background-radius: 8; -fx-background-color: white;");
        usernameField.setMaxWidth(250);

        // ===== Password Field (Masked) =====
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle(
                "-fx-prompt-text-fill: #cccccc; -fx-text-fill: black; -fx-background-radius: 8; -fx-background-color: white;");
        passwordField.setMaxWidth(250);

        // ===== Show Password Toggle =====
        CheckBox showPasswordCheck = new CheckBox("Show Password");
        showPasswordCheck.setTextFill(Color.LIGHTGRAY);

        // ===== Visible Password Field =====
        TextField visiblePasswordField = new TextField();
        visiblePasswordField.setManaged(false);
        visiblePasswordField.setVisible(false);
        visiblePasswordField.setMaxWidth(250);
        visiblePasswordField.setStyle(
                "-fx-prompt-text-fill: #cccccc; -fx-text-fill: black; -fx-background-radius: 8; -fx-background-color: white;");
        visiblePasswordField.setPromptText("Password");

        // Toggle between password fields
        showPasswordCheck.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                visiblePasswordField.setText(passwordField.getText());
                visiblePasswordField.setManaged(true);
                visiblePasswordField.setVisible(true);
                passwordField.setManaged(false);
                passwordField.setVisible(false);
            } else {
                passwordField.setText(visiblePasswordField.getText());
                passwordField.setManaged(true);
                passwordField.setVisible(true);
                visiblePasswordField.setManaged(false);
                visiblePasswordField.setVisible(false);
            }
        });

        // ===== Error Message =====
        Label errorMessage = new Label("");
        errorMessage.setTextFill(Color.RED);
        errorMessage.setFont(Font.font("Segoe UI", 14));

        // ===== Login Button =====
        Button loginBtn = new Button("Login");
        loginBtn.setStyle(
                "-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
        loginBtn.setPrefWidth(120);

        loginBtn.setOnMouseEntered(e -> loginBtn.setStyle(
                "-fx-background-color: #0096c7; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;"));
        loginBtn.setOnMouseExited(e -> loginBtn.setStyle(
                "-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;"));

        // ===== Login Action =====
        loginBtn.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = showPasswordCheck.isSelected()
                    ? visiblePasswordField.getText().trim()
                    : passwordField.getText().trim();

            if (username.equals("admin") && password.equals("123")) {
                errorMessage.setText("");
                fadeOutAndSwitch(primaryStage, new admin());
            } else {
                errorMessage.setText("Invalid username or password!");
                FadeTransition ft = new FadeTransition(Duration.seconds(0.3), errorMessage);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
            }
        });

        // ===== Forgot Password & Register Links =====
        HBox linkBox = new HBox(20);
        linkBox.setAlignment(Pos.CENTER);

        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setTextFill(Color.LIGHTBLUE);
        forgotPasswordLink.setBorder(Border.EMPTY);

        Hyperlink registerLink = new Hyperlink("Register");
        registerLink.setTextFill(Color.LIGHTBLUE);
        registerLink.setBorder(Border.EMPTY);

        // Forgot Password → open forgot UI
        forgotPasswordLink.setOnAction(e -> fadeOutAndSwitch(primaryStage, new forgot()));

        // Register → open registerAdmin UI
        registerLink.setOnAction(e -> fadeOutAndSwitch(primaryStage, new registerAdmin()));

        linkBox.getChildren().addAll(forgotPasswordLink, registerLink);

        // ===== Add Controls to Login Box =====
        loginBox.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                visiblePasswordField,
                showPasswordCheck,
                loginBtn,
                errorMessage,
                linkBox
        );

        root.setCenter(loginBox);

        // ===== Fade-in Animation =====
        FadeTransition ft = new FadeTransition(Duration.seconds(1.2), loginBox);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        Scene scene = new Scene(root,screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Helper method to fade out current stage and open new scene
     */
    private void fadeOutAndSwitch(Stage currentStage, Application nextApp) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.7), currentStage.getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> {
            try {
                nextApp.start(new Stage());
                currentStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        fadeOut.play();
    }
}
