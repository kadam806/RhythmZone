package com.rythm_zone.loginPage1;
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

public class mailforget extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
    
    
        public void start(Stage stage)throws Exception {
            // Heading
            Label title = new Label("Forgot Password?");
            title.setTextFill(Color.web("#cb3083ff"));
            title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
            //title.setMaxWidth(100);
            title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;-fx-background color:fx-background-color: #cb3083ff");
    
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
            sendCodeBtn.setStyle("-fx-background-color: #cb3083ff; -fx-text-fill: white; -fx-background-radius: 8;");
            sendCodeBtn.setOnAction(e -> {
                 String email = emailField.getText().trim();

                if (email.isEmpty()) {
                                 Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter your email address.", ButtonType.OK);
                                 alert.showAndWait();
                } else {
            // Simulate sending a code (you can integrate real email logic here)
             String code = generateVerificationCode(); // Simulate a 6-digit code
             System.out.println("Sending verification code to " + email + ": " + code);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "A verification code has been sent to your email!", ButtonType.OK);
            alert.showAndWait();

            // Optionally store or pass the code for verification in the next screen
         }
});
            
            VBox vbox = new VBox(15, title, info, emailField, sendCodeBtn);
            vbox .setStyle("-fx-background-color:rgb(255, 239, 242);");
          //  vbox.setStyle("-fx-background color:rgb(255, 239, 242);");
            Scene sc=new Scene(vbox,screenWidth, screenHeight);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(40));
            vbox.setPrefWidth(300);
            stage.setScene(sc);
            stage.show();
    
           
        }

        private String generateVerificationCode() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'generateVerificationCode'");
        }

        public Scene createAdminScene(Stage stage) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'createAdminScene'");
        }
    }




