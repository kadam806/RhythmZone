package com.rythm_zone.loginPage1;

import com.rythm_zone.controller.FirebaseSignUpAuthController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class registration extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
    FirebaseSignUpAuthController controller = new FirebaseSignUpAuthController();

    @Override
    public void start(Stage stage) throws Exception {
        Label heading = new Label("Welcome Again!");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        heading.setTextFill(Color.web("#FFFFFF"));

        String fieldStyle = "-fx-background-color: #1A1F24;" +
                            "-fx-text-fill: white;" +
                            "-fx-prompt-text-fill: #AAAAAA;" +
                            "-fx-border-color: #444;" +
                            "-fx-background-radius: 8;" +
                            "-fx-border-radius: 8;";

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setPrefSize(200, 30);
        username.setStyle(fieldStyle);
        Label errorLabel = new Label();
errorLabel.setTextFill(Color.RED);
errorLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
errorLabel.setVisible(false); // Initially hidden


        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setPrefSize(200, 30);
        emailField.setStyle(fieldStyle);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setPrefSize(200, 30);
        password.setStyle(fieldStyle);

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");
        confirmPassword.setPrefSize(200, 30);
        confirmPassword.setStyle(fieldStyle);

        Button registerBtn = new Button("Agree and Register");
        registerBtn.setPrefWidth(200);
        registerBtn.setStyle("-fx-background-color: #0B101A; -fx-text-fill: white; -fx-background-radius: 8;");
        VBox buttonBox = new VBox(registerBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setStyle(
            "-fx-background-color: #2e455cff; " +
            "-fx-border-color: #444; " +
            "-fx-border-radius: 10; " +
            "-fx-background-radius: 10;"
        );

        registerBtn.setOnAction(e -> {
            String email = emailField.getText();
            String pass = password.getText();
            boolean success = controller.signUpWithEmailAnsPassword(email, pass);
            System.out.println("Registration logic here");
           if (success) {
        try {
            home1 homePage = new home1();
            homePage.start(stage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } else {
        errorLabel.setText("Signup failed. Please try again.");
        errorLabel.setVisible(true);
    }
        });

        Hyperlink backToLogin = new Hyperlink("Back to Login");
        backToLogin.setTextFill(Color.LIGHTGRAY);
        backToLogin.setOnAction(e -> {
            loginPage1 loginPage = new loginPage1();
            try {
                loginPage.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Label orLoginWith = new Label("Or log in with");
        orLoginWith.setTextFill(Color.GRAY);

        VBox vb = new VBox(15,
                heading,
                username,
                emailField,
                password,
                confirmPassword,
                registerBtn,
                errorLabel,
                orLoginWith,
                backToLogin
        );
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(300));
        vb.setStyle("-fx-background-color: #0B101A; -fx-background-radius: 12;");

        StackPane root = new StackPane(vb);
        root.setStyle("-fx-background-color: #0B101A;");

        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setTitle("Registration Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}