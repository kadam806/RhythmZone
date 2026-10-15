package com.rythm_zone.loginPage1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class setpass extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
    public void start(Stage myStage)throws Exception{
          Label Title =new Label("Enter new password");
          Title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            
           // Label title=new Label("Enter new pasword"); 
            PasswordField password = new PasswordField();
            password.setPromptText("New Password");  
            //password.setPrefWidth(200);
            //password.setPrefHeight(30);
            password.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #bbb;");
            password.setMaxWidth(200);
            

            // Confirm password field

            Label Title2 =new Label("confirm Pasword");
            Title2.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
            
            PasswordField confirmPassword = new PasswordField();
            confirmPassword.setPromptText("Confirm New Password"); 
           // confirmPassword.setPrefWidth(300);
           // confirmPassword.setPrefHeight(30);
            confirmPassword.setMaxWidth(200);
            
            confirmPassword.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #bbb;"); 
            TextField setBtn=new TextField("Set Password");
            Label label=new Label();
            label.setTextFill(Color.BLUE);
            label.setAlignment(Pos.CENTER);
            label.setMaxWidth(200);


            VBox vb=new VBox(10,Title,password,Title2,confirmPassword,label,setBtn);
            vb.setAlignment(Pos.CENTER);
            Scene sc=new Scene(vb,screenWidth ,screenHeight);
            myStage.setScene(sc);
            myStage.show();
    }    
}
