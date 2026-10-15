package com.rythm_zone.loginPage1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class about extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage stage) {
        VBox aboutBox = new VBox(30);
        aboutBox.setAlignment(Pos.TOP_LEFT);
        aboutBox.setPadding(new Insets(40));
        aboutBox.setStyle("-fx-background-color: #0B101A;");
        aboutBox.setAlignment(Pos.CENTER);

        // === Title ===
        Label title = new Label("★ About Our Institute ★");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 32));
        title.setTextFill(Color.web("#CB3083"));

        // === Profile Image and Name ===
        Image profileImage = new Image(getClass().getResourceAsStream("/asset\\Screenshot 2025-07-27 140709.png"));
        ImageView imageView = new ImageView(profileImage);
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
        Circle clip = new Circle(125, 125, 125);
        imageView.setClip(clip);

        Label profName = new Label("Prof. Shashi Sir");
        profName.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
        profName.setTextFill(Color.LIGHTYELLOW);
        profName.setAlignment(Pos.CENTER);

        VBox imageWithNameBox = new VBox(10, imageView, profName);
        imageWithNameBox.setAlignment(Pos.CENTER);

        Label sideText = new Label(
                "Thank you so much Shashi Sir for your invaluable teachings and constant support.\n\n" +
                        "The way you teach is truly unique and impactful — turning complex topics into crystal-clear concepts. " +
                        "Your guidance and structured roadmap played a major role in my confidence for Supre-X Project.\n\n" +
                        "Big thanks to Core2web for creating such a powerful learning environment. " +
                        "Your mentorship helped me grow technically and shaped my mindset for real-world challenges.\n\n" +
                        "Forever grateful. Your teachings will always stay with me!"
        );
        sideText.setWrapText(true);
        sideText.setTextFill(Color.LIGHTYELLOW);
        sideText.setFont(Font.font("Georgia", 15));
        sideText.setMaxWidth(600);

        HBox imageTextBox = new HBox(30, imageWithNameBox, sideText);
        imageTextBox.setAlignment(Pos.CENTER);

        // === Mentors Appreciation Section ===
        Label mentorTitle = new Label("Special Thanks for");
        mentorTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        mentorTitle.setTextFill(Color.web("#CB3083"));

        VBox mentorBox = new VBox(10);
        mentorBox.setAlignment(Pos.CENTER);

        Label sachinSir = new Label("Sachin Sir");
        sachinSir.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        sachinSir.setTextFill(Color.LIGHTYELLOW);
        Label sachinNote = new Label("Your insights and encouragement helped me face tough challenges during the Super-X project. Your presence gave us the strength to push beyond our limits.");
        sachinNote.setWrapText(true);
        sachinNote.setTextFill(Color.WHITE);
        sachinNote.setFont(Font.font("Georgia", 15));

        Label subodhSir = new Label("Subodh Sir");
        subodhSir.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        subodhSir.setTextFill(Color.LIGHTYELLOW);
        Label subodhNote = new Label("Thank you for always being approachable and ready to solve our technical roadblocks. Your mentorship meant a lot in shaping our journey.");
        subodhNote.setWrapText(true);
        subodhNote.setTextFill(Color.WHITE);
        subodhNote.setFont(Font.font("Georgia", 15));

        Label shivSir = new Label("Shiv Sir");
        shivSir.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        shivSir.setTextFill(Color.LIGHTYELLOW);
        Label shivNote = new Label("Your logical approach and motivation during hard times kept us moving. Your support had a deep impact on our performance in the Super-X project.");
        shivNote.setWrapText(true);
        shivNote.setTextFill(Color.WHITE);
        shivNote.setFont(Font.font("Georgia", 15));

        Label poonam = new Label("Mentor: Poonam Didi");
        poonam.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        poonam.setTextFill(Color.LIGHTYELLOW);
        Label poonam1 = new Label("With heartfelt gratitude, we recognize Poonam Didi as one of the best mentors. Her unwavering support, thoughtful guidance, and endless encouragement played the most critical role in the Super-X project. Thank you for being the cornerstone of our success.");
        poonam1.setWrapText(true);
        poonam1.setTextFill(Color.WHITE);
        poonam1.setFont(Font.font("Georgia", 15));

        // === Ashture Ankita Section ===
        Image ankitaImage = new Image("/asset\\ankita1.jpg");
        ImageView ankitaImageView = new ImageView(ankitaImage);
        ankitaImageView.setFitWidth(180);
        ankitaImageView.setFitHeight(180);
        ankitaImageView.setPreserveRatio(false);
        ankitaImageView.setSmooth(true);
        Circle ankitaClip = new Circle(90, 90, 90);
        ankitaImageView.setClip(ankitaClip);

        Label ankitaName = new Label("Ashture Ankita");
        ankitaName.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        ankitaName.setTextFill(Color.LIGHTYELLOW);

        Label ankitaNote = new Label("With deep respect and regards, we thank Ashture Ankita for her passionate contribution and sincere dedication throughout the Super-X Project journey.\nHer consistency, focus, and positive energy brought our team closer to success.");
        ankitaNote.setWrapText(true);
        ankitaNote.setTextFill(Color.WHITE);
        ankitaNote.setFont(Font.font("Georgia", 15));

        VBox ankitaBox = new VBox(10, ankitaImageView, ankitaName, ankitaNote);
        ankitaBox.setAlignment(Pos.CENTER);

        mentorBox.getChildren().addAll(mentorTitle, sachinSir, sachinNote, subodhSir, subodhNote, shivSir, shivNote, poonam, poonam1, ankitaBox);

        // === Intro, Mission, Offer, Why ===
        Label intro = new Label("Welcome to the School of Musical Instrument Crafts.\n\n" +
                "We specialize in teaching the traditional craftsmanship of musical instrument making — from violins and violas to guitars and woodwinds. " +
                "With decades of heritage, we combine classic techniques with modern precision to help students master the art of instrument construction and repair.");
        intro.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
        intro.setWrapText(true);
        intro.setTextFill(Color.WHITE);
        intro.setFont(Font.font("Georgia", 16));

        Label missionTitle = new Label("Our Mission");
        missionTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        missionTitle.setTextFill(Color.web("#CB3083"));

        Label mission = new Label("To preserve and pass on the time-honored skills of musical instrument making and to foster the next generation of world-class luthiers and repair technicians.");
        mission.setWrapText(true);
        mission.setTextFill(Color.WHITE);
        mission.setFont(Font.font("Georgia", 16));

        Label offerTitle = new Label("What We Offer");
        offerTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        offerTitle.setTextFill(Color.web("#CB3083"));

        Label offer = new Label("- Hands-on workshops guided by master craftspeople\n" +
                "- Historical and modern instrument design study\n" +
                "- Access to professional-grade tools and materials\n" +
                "- Collaborative learning environment");
        offer.setWrapText(true);
        offer.setTextFill(Color.WHITE);
        offer.setFont(Font.font("Georgia", 16));

        Label whyTitle = new Label("Why Choose Us");
        whyTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        whyTitle.setTextFill(Color.web("#CB3083"));

        Label why = new Label("- International reputation for excellence in craftsmanship\n" +
                "- Alumni working in renowned orchestras and workshops worldwide\n" +
                "- Beautiful campus and well-equipped workshops");
        why.setWrapText(true);
        why.setTextFill(Color.WHITE);
        why.setFont(Font.font("Georgia", 16));

        Button backButton = new Button("← Back to Home");
        backButton.setTextFill(Color.web("#0B101A"));
        backButton.setOnAction(e -> new home1().start(stage));

        aboutBox.getChildren().addAll(title, imageTextBox, mentorBox, intro, missionTitle, mission, offerTitle, offer, whyTitle, why, backButton);

        ScrollPane scrollPane = new ScrollPane(aboutBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #0B101A; -fx-border-color: transparent;");

        Scene scene = new Scene(scrollPane, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.setTitle("About - Musical Instrument Crafts");
        stage.show();
    }
}
