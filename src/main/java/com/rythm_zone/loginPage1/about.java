package com.rythm_zone.loginPage1;

import java.net.URL;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class about extends Application {

    private static final String BG = "#0B1020";
    private static final String CARD = "#151D31";
    private static final String PINK = "#FF4F9A";
    private static final String PURPLE = "#9B7BFF";
    private static final String WHITE = "#FFFFFF";
    private static final String MUTED = "#B5BED3";

    @Override
    public void start(Stage stage) {

        Rectangle2D bounds =
                Screen.getPrimary().getVisualBounds();

        // Main container
        VBox root = new VBox(35);
        root.setPadding(new Insets(35, 45, 35, 45));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: " + BG + ";");

        // ================= HEADER =================

        HBox header = new HBox();

        Label logo = new Label("♫ RhythmZone");
        logo.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));
        logo.setTextFill(Color.web(PINK));

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        Label aboutLabel = new Label("ABOUT US");
        aboutLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        aboutLabel.setTextFill(Color.web(WHITE));
        aboutLabel.setStyle(
                "-fx-border-color: " + PINK + ";" +
                "-fx-border-radius: 20;" +
                "-fx-padding: 9 18;" +
                "-fx-background-radius: 20;"
        );

        header.getChildren().addAll(
                logo, headerSpacer, aboutLabel
        );

        // ================= HERO SECTION =================

        VBox hero = new VBox(15);
        hero.setAlignment(Pos.CENTER);
        hero.setPadding(new Insets(30, 15, 20, 15));

        Label badge = new Label("✦  OUR STORY  ✦");
        badge.setTextFill(Color.web(PINK));
        badge.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        Label heroTitle = new Label("More Than Just Music");
        heroTitle.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 38));
        heroTitle.setTextFill(Color.WHITE);
        heroTitle.setWrapText(true);
        heroTitle.setAlignment(Pos.CENTER);

        Label heroSubtitle = new Label(
                "A passion for music, creativity and technology."
        );

        heroSubtitle.setFont(Font.font("Arial", 16));
        heroSubtitle.setTextFill(Color.web(MUTED));
        heroSubtitle.setWrapText(true);
        heroSubtitle.setAlignment(Pos.CENTER);

        hero.getChildren().addAll(
                badge, heroTitle, heroSubtitle
        );

        // ================= ABOUT PROJECT =================

        VBox projectCard = createCard();

        Label projectTitle = createHeading(
                "🎵  About RhythmZone", 25, PINK
        );

        Label projectDescription = createBody(
                "RhythmZone is a desktop-based music player " +
                "application developed using Java and JavaFX.\n\n" +
                "The project focuses on providing a simple and " +
                "user-friendly music experience through a modern " +
                "graphical interface. Firebase is used for the " +
                "application's configured backend features."
        );

        projectCard.getChildren().addAll(
                projectTitle, projectDescription
        );

        // ================= FOUNDER / DEVELOPER PROFILE =================

        HBox profileCard = new HBox(35);
        profileCard.setAlignment(Pos.CENTER);
        profileCard.setPadding(new Insets(30));
        profileCard.setMaxWidth(1000);

        profileCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 24;" +
                "-fx-border-color: #303B56;" +
                "-fx-border-radius: 24;"
        );

        // Vedika's profile image
        ImageView vedikaImage = createCircularImage(
                "/asset/vedika-rhythmzone.jpeg", 190
        );

        // Image glow border
        StackPane imageFrame = new StackPane();
        imageFrame.setPrefSize(210, 210);
        imageFrame.setMinSize(210, 210);
        imageFrame.setMaxSize(210, 210);

        Circle outerCircle = new Circle(102);
        outerCircle.setFill(Color.TRANSPARENT);
        outerCircle.setStroke(Color.web(PINK));
        outerCircle.setStrokeWidth(3);

        imageFrame.getChildren().addAll(
                outerCircle, vedikaImage
        );

        // Profile details
        VBox profileDetails = new VBox(14);
        profileDetails.setAlignment(Pos.CENTER_LEFT);

        Label profileBadge = new Label("✦  MEET THE DEVELOPER");

        profileBadge.setFont(
                Font.font("Arial", FontWeight.BOLD, 12)
        );

        profileBadge.setTextFill(Color.web(PINK));

        Label vedikaName = new Label("Vedika Kadam");

        vedikaName.setFont(
                Font.font("Arial", FontWeight.EXTRA_BOLD, 30)
        );

        vedikaName.setTextFill(Color.WHITE);

        Label role = new Label("Java Developer | Project Contributor");

        role.setFont(Font.font("Arial", 15));
        role.setTextFill(Color.web(PURPLE));
        role.setWrapText(true);

        Label vedikaDescription = createBody(
                "Passionate about technology, software development " +
                "and creating meaningful applications.\n\n" +
                "Through RhythmZone, I explored desktop application " +
                "development using Java, JavaFX, Maven and Firebase."
        );

        Button contactButton = new Button("Keep Creating  ✦");

        contactButton.setFont(
                Font.font("Arial", FontWeight.BOLD, 13)
        );

        contactButton.setTextFill(Color.WHITE);

        contactButton.setStyle(
                "-fx-background-color: linear-gradient(to right, " +
                PINK + ", " + PURPLE + ");" +
                "-fx-background-radius: 22;" +
                "-fx-padding: 12 22;" +
                "-fx-cursor: hand;"
        );

        profileDetails.getChildren().addAll(
                profileBadge,
                vedikaName,
                role,
                vedikaDescription,
                contactButton
        );

        HBox.setHgrow(profileDetails, Priority.ALWAYS);

        profileCard.getChildren().addAll(
                imageFrame, profileDetails
        );

        // ================= MENTORS SECTION =================

        VBox mentorsSection = new VBox(22);
        mentorsSection.setAlignment(Pos.CENTER);

        Label mentorHeading = createHeading(
                "People Who Inspired Us", 29, PINK
        );

        Label mentorSubtitle = createBody(
                "A heartfelt thank you to the mentors who guided us."
        );

        mentorSubtitle.setAlignment(Pos.CENTER);

        HBox mentorCards = new HBox(18);
        mentorCards.setAlignment(Pos.CENTER);

        VBox sachinCard = createMentorCard(
                "✦",
                "Sachin Sir",
                "Your insights and encouragement helped us " +
                "face challenges and push beyond our limits."
        );

        VBox subodhCard = createMentorCard(
                "✧",
                "Subodh Sir",
                "Thank you for your guidance, approachability " +
                "and help in solving technical challenges."
        );

        VBox shivCard = createMentorCard(
                "♫",
                "Shiv Sir",
                "Your logical approach and motivation helped " +
                "us keep moving forward."
        );

        VBox poonamCard = createMentorCard(
                "♡",
                "Poonam Khedkar",
                "Thank you for your thoughtful guidance, " +
                "constant support and encouragement."
        );

        mentorCards.getChildren().addAll(
                sachinCard,
                subodhCard,
                shivCard,
                poonamCard
        );

        mentorsSection.getChildren().addAll(
                mentorHeading,
                mentorSubtitle,
                mentorCards
        );

        // ================= MISSION SECTION =================

        HBox missionCards = new HBox(20);
        missionCards.setAlignment(Pos.CENTER);

        VBox missionCard = createInfoCard(
                "🎯",
                "Our Mission",
                "To build a user-friendly music application " +
                "that combines creativity and technology."
        );

        VBox technologyCard = createInfoCard(
                "💻",
                "Our Technology",
                "Java 17, JavaFX, Maven and Firebase, " +
                "according to the implemented features."
        );

        VBox visionCard = createInfoCard(
                "✨",
                "Our Vision",
                "To continue learning, improving and creating " +
                "better software experiences."
        );

        missionCards.getChildren().addAll(
                missionCard,
                technologyCard,
                visionCard
        );

        // ================= THANK YOU SECTION =================

        VBox thankYouCard = new VBox(15);
        thankYouCard.setAlignment(Pos.CENTER);
        thankYouCard.setPadding(new Insets(35));

        thankYouCard.setStyle(
                "-fx-background-color: linear-gradient(to right, " +
                "#231936, #17243C);" +
                "-fx-background-radius: 24;" +
                "-fx-border-color: #40305E;" +
                "-fx-border-radius: 24;"
        );

        Label thankYouTitle = createHeading(
                "Thank You for Being Here 💖", 27, PINK
        );

        Label thankYouDescription = createBody(
                "Every project is a step forward in the journey " +
                "of learning and innovation.\n" +
                "Keep exploring. Keep creating. Keep growing."
        );

        thankYouDescription.setAlignment(Pos.CENTER);

        Button backButton = new Button("←  Back to Home");

        backButton.setFont(
                Font.font("Arial", FontWeight.BOLD, 14)
        );

        backButton.setTextFill(Color.WHITE);

        backButton.setStyle(
                "-fx-background-color: " + PINK + ";" +
                "-fx-background-radius: 22;" +
                "-fx-padding: 13 26;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnMouseEntered(e ->
                backButton.setOpacity(0.8)
        );

        backButton.setOnMouseExited(e ->
                backButton.setOpacity(1.0)
        );

        backButton.setOnAction(e ->
                new home1().start(stage)
        );

        thankYouCard.getChildren().addAll(
                thankYouTitle,
                thankYouDescription,
                backButton
        );

        // ================= FOOTER =================

        Label footer = new Label(
                "Made with ♡ by Vedika Kadam  |  RhythmZone"
        );

        footer.setFont(Font.font("Arial", 12));
        footer.setTextFill(Color.web(MUTED));

        // ================= ADD ALL SECTIONS =================

        root.getChildren().addAll(
                header,
                hero,
                projectCard,
                profileCard,
                mentorsSection,
                missionCards,
                thankYouCard,
                footer
        );

        // ScrollPane
        ScrollPane scrollPane = new ScrollPane(root);

        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: transparent;"
        );

        // Fade-in animation
        root.setOpacity(0);

        FadeTransition fade = new FadeTransition(
                Duration.millis(900), root
        );

        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        Scene scene = new Scene(
                scrollPane,
                bounds.getWidth(),
                bounds.getHeight()
        );

        stage.setTitle("About Us | RhythmZone");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    // ================= HELPER METHODS =================

    private ImageView createCircularImage(
            String path, double size
    ) {

        ImageView imageView = new ImageView();

        URL imageUrl = getClass().getResource(path);

        if (imageUrl == null) {

            System.err.println(
                    "Image not found: " + path
            );

            // Show a placeholder if the photo is missing.
            return imageView;
        }

        Image image = new Image(
                imageUrl.toExternalForm(),
                false
        );

        imageView.setImage(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);

        Circle clip = new Circle(
                size / 2,
                size / 2,
                size / 2
        );

        imageView.setClip(clip);

        return imageView;
    }

    private VBox createCard() {

        VBox card = new VBox(18);

        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(28));
        card.setMaxWidth(1000);

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 22;" +
                "-fx-border-color: #303B56;" +
                "-fx-border-radius: 22;"
        );

        addHoverEffect(card);

        return card;
    }

    private Label createHeading(
            String text, double size, String color
    ) {

        Label label = new Label(text);

        label.setFont(
                Font.font("Arial", FontWeight.EXTRA_BOLD, size)
        );

        label.setTextFill(Color.web(color));
        label.setWrapText(true);

        return label;
    }

    private Label createBody(String text) {

        Label label = new Label(text);

        label.setFont(Font.font("Arial", 15));
        label.setTextFill(Color.web(MUTED));
        label.setWrapText(true);
        label.setMaxWidth(850);

        return label;
    }

    private VBox createMentorCard(
            String icon,
            String name,
            String description
    ) {

        VBox card = createCard();

        card.setPrefWidth(220);
        card.setMinWidth(180);
        card.setMaxWidth(260);
        card.setMinHeight(220);

        card.setAlignment(Pos.TOP_LEFT);
        card.setPadding(new Insets(22));

        Label iconLabel = new Label(icon);

        iconLabel.setFont(Font.font("Arial", 30));
        iconLabel.setTextFill(Color.web(PINK));

        Label nameLabel = createHeading(
                name, 17, WHITE
        );

        Label descriptionLabel = createBody(
                description
        );

        descriptionLabel.setFont(Font.font("Arial", 13));

        card.getChildren().addAll(
                iconLabel,
                nameLabel,
                descriptionLabel
        );

        return card;
    }

    private VBox createInfoCard(
            String icon,
            String title,
            String description
    ) {

        VBox card = createCard();

        card.setAlignment(Pos.TOP_LEFT);
        card.setPrefWidth(300);
        card.setMinWidth(180);
        card.setMaxWidth(350);
        card.setMinHeight(190);

        Label iconLabel = new Label(icon);

        iconLabel.setFont(Font.font("Arial", 28));

        Label titleLabel = createHeading(
                title, 19, PINK
        );

        Label descriptionLabel = createBody(
                description
        );

        descriptionLabel.setFont(Font.font("Arial", 13));

        card.getChildren().addAll(
                iconLabel,
                titleLabel,
                descriptionLabel
        );

        addHoverEffect(card);

        return card;
    }

    private void addHoverEffect(VBox card) {

        card.setOnMouseEntered(e -> {

            ScaleTransition scale = new ScaleTransition(
                    Duration.millis(160), card
            );

            scale.setToX(1.025);
            scale.setToY(1.025);
            scale.play();
        });

        card.setOnMouseExited(e -> {

            ScaleTransition scale = new ScaleTransition(
                    Duration.millis(160), card
            );

            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.play();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}