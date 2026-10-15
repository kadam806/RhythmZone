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

public class chatbotPage extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage stage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #0B101A;");

        Label title = new Label("Chat with AI");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.WHITE);

        TextArea chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        chatArea.setStyle("-fx-control-inner-background: #1e1e1e; -fx-text-fill: white;");

        TextField inputField = new TextField();
        inputField.setPromptText("Type your message...");
        inputField.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");

        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: #00b894; -fx-text-fill: white;");
        sendButton.setOnAction(e -> {
            String userInput = inputField.getText();
            if (!userInput.isEmpty()) {
                chatArea.appendText("You: " + userInput + "\n");
                String response = chatbot.getAIResponse(userInput); // calling your existing method
                chatArea.appendText("AI: " + response + "\n\n");
                inputField.clear();
            }
        });

        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        backButton.setOnAction(e -> {
            new home1().start(stage); // navigate back to home
        });

        HBox inputBox = new HBox(10, inputField, sendButton);
        inputBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(backButton, title, chatArea, inputBox);

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(scene);
    }
}
