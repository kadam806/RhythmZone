package com.rythm_zone.loginPage1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class userlogin extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage stage) {
        // Main container
        VBox mainBox = new VBox(15);
        mainBox.setPadding(new Insets(20));
        mainBox.setStyle("-fx-background-color: #F9F9F9;");

        // Banner area
        Label banner = new Label("Become An Insider To Avail Extra Rewards and Better Discounts!");
        banner.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        banner.setWrapText(true);
        banner.setTextFill(Color.DARKGOLDENROD);
        Button knowMore = new Button("Know More");
        knowMore.setStyle("-fx-background-color: #D4AF37; -fx-text-fill: white;");
        VBox bannerBox = new VBox(5, banner, knowMore);
        bannerBox.setStyle("-fx-background-color: #FFF8F0; -fx-padding: 15; -fx-background-radius: 10;");

        // Profile section
        Circle avatar = new Circle(30, Color.LIGHTSEAGREEN);
        Label avatarText = new Label("Y");
        avatarText.setTextFill(Color.WHITE);
        avatarText.setFont(Font.font(24));
        StackPane avatarPane = new StackPane(avatar, avatarText);

        Label adminLabel = new Label("Admin");
        adminLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        adminLabel.setTextFill(Color.WHITE);
        adminLabel.setStyle("-fx-background-color: #555; -fx-background-radius: 5; -fx-padding: 2 5 2 5;");
        VBox profileBox = new VBox(avatarPane, adminLabel, new Label("You"));
        profileBox.setAlignment(Pos.CENTER);

        Circle addCircle = new Circle(30, Color.LIGHTGRAY);
        Label plus = new Label("+");
        plus.setFont(Font.font(24));
        StackPane addPane = new StackPane(addCircle, plus);
        VBox addBox = new VBox(addPane, new Label("Add"));
        addBox.setAlignment(Pos.CENTER);

        HBox profileSection = new HBox(30, profileBox, addBox);
        profileSection.setAlignment(Pos.CENTER_LEFT);

        // Quick filters row
        HBox quickFilters = new HBox(10);
        String[] filters = {"Courses", "Size Details", "prizes", "Types"};
        for (String filter : filters) {
            Button btn = new Button(filter);
            btn.setStyle("-fx-background-color: white; -fx-border-color: lightgray; -fx-border-radius: 15; -fx-background-radius: 15;");
            quickFilters.getChildren().add(btn);
        }

        // Grid of options
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        String[] gridOptions = {
            "Courses", "Orders", "Help Center", "Coupons",
            "Ultimate Glam Clan", "Personal Loan",
            "Payments & Currencies", "Earn & Redeem",
            "Manage Account", "Challenges"
        };

        for (int i = 0; i < gridOptions.length; i++) {
            Button option = new Button(gridOptions[i]);
            option.setPrefWidth(200);
            option.setAlignment(Pos.CENTER_LEFT);
            option.setStyle("-fx-background-color: white; -fx-border-color: #DDD; -fx-border-radius: 10; -fx-background-radius: 10;");
            grid.add(option, i % 2, i / 2);
        }
        //logout

        Button logoutBtn = new Button("Log Out");
        logoutBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        logoutBtn.setTextFill(Color.RED);
        logoutBtn.setStyle("-fx-background-color: white; -fx-border-color: #b80e0eff; -fx-border-radius: 10; -fx-background-radius: 10;");
        logoutBtn.setPrefWidth(200);
        logoutBtn.setOnAction(e -> {
            System.out.println("User logged out");
                loginPage1 loginPage = new loginPage1();
        try {
             loginPage.start(stage); // Navigate to login page using the same stage
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        });

        HBox logoutBox = new HBox(logoutBtn);
        logoutBox.setAlignment(Pos.CENTER_LEFT);
        logoutBox.setPadding(new Insets(20, 0, 10, 0));

        mainBox.getChildren().addAll(bannerBox, profileSection, quickFilters, grid,logoutBox);
        mainBox.setStyle("-fx-background-color: rgb(255, 239, 242)");
        mainBox.setAlignment(Pos.CENTER);
    
        ScrollPane scrollPane = new ScrollPane(mainBox);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, screenWidth, screenHeight );
        stage.setScene(scene);
        stage.setTitle("Profile Page");
        stage.show();
    }
}

