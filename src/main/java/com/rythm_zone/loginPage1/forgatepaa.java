package com.rythm_zone.loginPage1;
    import javafx.application.Application;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class forgatepaa extends Application {
     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        public void start(Stage stage)throws Exception {
            // Heading
            Label title = new Label("Forgot Password?");
            title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
    
            // Info text
            Label info = new Label("Don’t worry! It occurs. Please enter the email address linked with your account.");
            info.setWrapText(true);
            info.setStyle("-fx-text-fill: #555555;");
    
            // Email input
            TextField emailField = new TextField();
            emailField.setPromptText("Enter your email");
            emailField.setMaxWidth(Double.MAX_VALUE);
    
            // Send Code button
            Button sendCodeBtn = new Button("Send Code");
            sendCodeBtn.setPrefWidth(250);
            //sendCodeBtn.setTextFill(Color.BURLYWOOD);
            sendCodeBtn.setStyle("-fx-background-color: #5A4FCF; -fx-text-fill: white; -fx-background-radius: 8;");
            
            
            Label Title =new Label("Enter new password");
            Title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            
        
            PasswordField password = new PasswordField();
            password.setPromptText("New Password");  
            password.setPrefWidth(200);
            password.setPrefHeight(30);
            password.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #bbb;");

            // Confirm password field
            PasswordField confirmPassword = new PasswordField();
            confirmPassword.setPromptText("Confirm New Password"); 
            confirmPassword.setPrefWidth(300);
            confirmPassword.setPrefHeight(30);
            confirmPassword.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #bbb;");  

            Button sendCodeBtn1 = new Button("Set password ");
            sendCodeBtn.setPrefWidth(250);
            // Back to login
            Hyperlink loginLink = new Hyperlink("Remembered Password? Login");
        
            VBox vbox = new VBox(15, title, info, emailField, sendCodeBtn, loginLink,Title,password,confirmPassword,sendCodeBtn1);
            Scene sc=new Scene(vbox,screenWidth,screenHeight);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(40));
            vbox.setPrefWidth(350);
            stage.setScene(sc);
            stage.show();
    
           
        }
    }


