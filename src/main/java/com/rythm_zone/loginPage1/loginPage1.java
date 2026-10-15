package com.rythm_zone.loginPage1;

import com.rythm_zone.controller.FirebaseSignUpAuthController;
import com.rythm_zone.controller.firebaseSignInAuthSignIn;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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

public class loginPage1 extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    
    firebaseSignInAuthSignIn controllerSignIn = new firebaseSignInAuthSignIn();

    @Override
    public void start(Stage stage) throws Exception {

        Label heading = new Label("Welcome!");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        heading.setTextFill(Color.web("#FFFFFF")); // white text

        VBox loginBox = new VBox(15);
        loginBox.setPadding(new Insets(20));
        loginBox.setStyle("-fx-background-color: transparent;");
        loginBox.setAlignment(Pos.CENTER);

        Label loginLabel = new Label("User Login");
        loginLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        loginLabel.setTextFill(Color.web("#FFFFFF"));

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(200);
        emailField.setStyle(
            "-fx-background-color: #1A1F24; " +
            "-fx-border-color: #0B101A; " +
            "-fx-text-fill: white; " +
            "-fx-prompt-text-fill: #AAAAAA;"
        );
         Label errorLabel = new Label();
errorLabel.setTextFill(Color.RED);
errorLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
errorLabel.setVisible(false); // Initially hidden


        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);
        passwordField.setStyle(
            "-fx-background-color: #1A1F24; " +
            "-fx-border-color: #0B101A; " +
            "-fx-text-fill: white; " +
            "-fx-prompt-text-fill: #AAAAAA;"
        );

        CheckBox rememberBox = new CheckBox("Remember me");
        rememberBox.setTextFill(Color.web("#DDDDDD"));

        Hyperlink forgotLink = new Hyperlink("Forgot password?");
        forgotLink.setTextFill(Color.LIGHTGRAY);
        forgotLink.setOnAction(e -> {
            adminlogin2 adminPage = new adminlogin2();
            Scene adminScene = adminPage.createAdminScene(stage);
            stage.setScene(adminScene);
        });

        HBox optionsBox = new HBox(10, rememberBox, forgotLink);
        optionsBox.setAlignment(Pos.CENTER);

        Button signInBtn = new Button("Sign In");
        signInBtn.setStyle(
            "-fx-background-color: #213355ff; " +
            "-fx-text-fill: white;"
        );
        signInBtn.setOnAction(e -> {
            String name = emailField.getText();
            String pass = passwordField.getText();
            boolean success = controllerSignIn.signInWithEmailAnsPassword(name, pass);
        
            if (success) {
        try {
            home1 homePage = new home1();
            homePage.start(stage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } else {
        errorLabel.setText("Login failed. Please try again.");
        errorLabel.setVisible(true);
    }
            System.out.println("Login logic here");
        });

        Label noAccount = new Label("Don't have an account?");
        noAccount.setTextFill(Color.LIGHTGRAY);

        Hyperlink signUpLink = new Hyperlink("Sign Up");
        signUpLink.setTextFill(Color.web("#FFFFFF"));
        signUpLink.setBorder(Border.EMPTY);
        signUpLink.setOnAction(e -> {
            registration regPage = new registration();
            try {
                regPage.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox signUpBox = new HBox(5, noAccount, signUpLink);
        signUpBox.setAlignment(Pos.CENTER);

        loginBox.getChildren().addAll(
        
            loginLabel,
            emailField,
            passwordField,
            optionsBox,
            signInBtn,
            errorLabel,
            signUpBox
        );

        Image bgImage = new Image("file:/mnt/data/bk.png");
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(900);
        bgImageView.setFitHeight(600);
        bgImageView.setPreserveRatio(false);

        StackPane root = new StackPane(bgImageView, loginBox);
        root.setStyle("-fx-background-color: #0B101A;");

        Scene scene = new Scene(root, screenWidth, screenHeight);

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}