package com.rythm_zone.loginPage1;

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

public class registerAdmin extends Application {
     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Registration - Rhythm Zone");

        // ===== Root Layout =====
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(
                Color.rgb(10, 25, 47), CornerRadii.EMPTY, Insets.EMPTY)));

        // ===== Title =====
        Label title = new Label("🎵 Register New Admin");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setTextFill(Color.WHITE);

        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(30, 0, 20, 0));
        root.setTop(titleBox);

        // ===== Registration Form =====
        VBox form = new VBox(15);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(30));
        form.setBackground(new Background(new BackgroundFill(
                Color.rgb(15, 32, 65), new CornerRadii(10), Insets.EMPTY)));
        form.setEffect(new DropShadow(8, Color.BLACK));

        TextField usernameField = createStyledTextField("Username", false);
        PasswordField passwordField = (PasswordField) createStyledTextField("Password", true);
        PasswordField confirmPasswordField = (PasswordField) createStyledTextField("Confirm Password", true);
        TextField emailField = createStyledTextField("Email", false);

        // Extra enforcement to make sure typing is black in password fields
        passwordField.setStyle(passwordField.getStyle() + "; -fx-text-fill: black;");
        confirmPasswordField.setStyle(confirmPasswordField.getStyle() + "; -fx-text-fill: black;");

        Button registerBtn = new Button("Register");
        registerBtn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        registerBtn.setStyle("-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-background-radius: 8;");
        registerBtn.setPrefWidth(280);
        registerBtn.setOnMouseEntered(e -> registerBtn.setStyle(
                "-fx-background-color: #0077B6; -fx-text-fill: white; -fx-background-radius: 8;"));
        registerBtn.setOnMouseExited(e -> registerBtn.setStyle(
                "-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-background-radius: 8;"));

        // When Register is clicked → Back to adminLogin
        registerBtn.setOnAction(e -> {
            try {
                new adminLogin().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Label loginLink = new Label("Already have an account? Login");
        loginLink.setTextFill(Color.LIGHTBLUE);
        loginLink.setOnMouseEntered(e -> loginLink.setTextFill(Color.CYAN));
        loginLink.setOnMouseExited(e -> loginLink.setTextFill(Color.LIGHTBLUE));
        loginLink.setOnMouseClicked(e -> {
            try {
                new adminLogin().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        form.getChildren().addAll(
                usernameField,
                passwordField,
                confirmPasswordField,
                emailField,
                registerBtn,
                loginLink
        );

        root.setCenter(form);

        // ===== Scene =====
        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ===== Utility Method: Create Styled TextField =====
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
