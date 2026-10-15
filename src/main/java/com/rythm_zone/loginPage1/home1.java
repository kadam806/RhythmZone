package com.rythm_zone.loginPage1;

import javafx.application.Application;
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

public class home1 extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage stage) {
        // === Background Container ===
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #0B101A;");

        // === Center Card ===
        VBox card = new VBox(20);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: transparent; -fx-padding: 40;");

        // === Navigation Buttons ===
        Button coursesBtn = createNavButton("COURSES");
        coursesBtn.setOnAction(e -> {
            courses coursesPage = new courses();
            coursesPage.start(stage);
        });

        Button consertBtn = createNavButton("CONCERT");
        consertBtn.setOnAction(e -> {
            consert consertPage = new consert();
            try {
                consertPage.start(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button sellingBtn = createNavButton("SELLING");
        sellingBtn.setOnAction(e -> {
            selling aboutObj1 = new selling();
            aboutObj1.start(stage);
        });

        Button rentalBtn = createNavButton("RENTAL");
        rentalBtn.setOnAction(e -> {
            OffersUI aboutObj2 = new OffersUI();
            aboutObj2.start(stage);
        });

        Button repairingBtn = createNavButton("REPAIRING");
        repairingBtn.setOnAction(e -> {
            repairing aboutObj3 = new repairing();
            aboutObj3.start(stage);
        });

        Button newsBtn = createNavButton("RYTHMBOT");
        newsBtn.setOnAction(e -> {
            chatbotPage chat = new chatbotPage();
            chat.start(stage);
        });

        Button aboutBtn = createNavButton("ABOUT US");
        aboutBtn.setOnAction(e -> {
            about aboutObj = new about();
            aboutObj.start(stage);
        });

        // === Stylish Settings Button ===
        Button settingsBtn = new Button("⚙");
        settingsBtn.setStyle(
            "-fx-background-color: #1E293B;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 18;" +
            "-fx-background-radius: 50%;" +
            "-fx-padding: 6;" +
            "-fx-cursor: hand;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 5, 0, 0, 2);"
        );

        settingsBtn.setOnMouseEntered(e -> settingsBtn.setStyle(
            "-fx-background-color: #2563EB;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 18;" +
            "-fx-background-radius: 50%;" +
            "-fx-padding: 6;" +
            "-fx-cursor: hand;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 8, 0, 0, 4);"
        ));

        settingsBtn.setOnMouseExited(e -> settingsBtn.setStyle(
            "-fx-background-color: #1E293B;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 18;" +
            "-fx-background-radius: 50%;" +
            "-fx-padding: 6;" +
            "-fx-cursor: hand;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 5, 0, 0, 2);"
        ));

        // === Advanced Context Menu for Settings ===
        ContextMenu settingsMenu = new ContextMenu();
        settingsMenu.setStyle(
            "-fx-background-color: #1F2937;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 5;"
        );

        MenuItem profileItem = new MenuItem("👤 Profile");
        profileItem.setStyle(menuItemStyle());
        profileItem.setOnAction(e -> System.out.println("Profile clicked"));

        MenuItem historyItem = new MenuItem("📜 History");
        historyItem.setStyle(menuItemStyle());
        historyItem.setOnAction(e -> System.out.println("History clicked"));

        MenuItem logoutItem = new MenuItem("🚪 Logout");
        logoutItem.setStyle(menuItemStyle());
                logoutItem.setOnAction(e -> {
            try {
                loginPage1 login = new loginPage1();
                login.start(stage); // Return to login page
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        

        MenuItem otherItem = new MenuItem("✨ Other Features");
        otherItem.setStyle(menuItemStyle());
        otherItem.setOnAction(e -> System.out.println("Other Features clicked"));

        settingsMenu.getItems().addAll(profileItem, historyItem, logoutItem, otherItem);

        

        // Show context menu when clicking settings button
        settingsBtn.setOnAction(e -> {
            settingsMenu.show(settingsBtn,
                settingsBtn.localToScreen(settingsBtn.getBoundsInLocal()).getMinX(),
                settingsBtn.localToScreen(settingsBtn.getBoundsInLocal()).getMaxY()
            );
        });

        // === Top Menu with Settings Button at the End ===
        HBox topMenu = new HBox(20, coursesBtn, consertBtn, sellingBtn,
                rentalBtn, repairingBtn, newsBtn, aboutBtn, settingsBtn);
        topMenu.setAlignment(Pos.CENTER);

        // === Title ===
        Label title = new Label("Wel-Come To The Rythm zone");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 32));
        title.setTextFill(Color.BEIGE);

        // === Image Background ===
        ImageView imageView = new ImageView(new Image("/asset/bg41.jpg"));
        imageView.setFitWidth(1000);
        imageView.setFitHeight(800);
        imageView.setOpacity(0.5);
        imageView.setPreserveRatio(true);

        StackPane imagePane = new StackPane(imageView, title);
        imagePane.setAlignment(Pos.CENTER);

        // === Card Content ===
        VBox content = new VBox(20, topMenu, imagePane);
        content.setAlignment(Pos.CENTER);

        card.getChildren().add(content);
        root.getChildren().add(card);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.setTitle("Musical Instrument Crafts");
        stage.show();
    }

    // === Reusable Button Creator ===
    private Button createNavButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent;");
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        return button;
    }

    // === Menu Item Style Helper ===
    private String menuItemStyle() {
        return "-fx-text-fill: white;" +
               "-fx-padding: 8 15;" +
               "-fx-font-size: 14;" +
               "-fx-background-radius: 5;" +
               "-fx-cursor: hand;";
    }
}
