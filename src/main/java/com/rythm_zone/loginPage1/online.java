package com.rythm_zone.loginPage1;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class online {
    private VBox createCourseCard(String title, String subtitle) {
    VBox card = new VBox(10);
    card.setPadding(new Insets(12));
    card.setAlignment(Pos.CENTER);
    card.setStyle(
        "-fx-background-color: #1e1e1e;" +
        "-fx-background-radius: 12;" +
        "-fx-border-color: #2f3542;" +
        "-fx-border-width: 1;" +
        "-fx-cursor: hand;"
    );
    card.setPrefSize(150, 100);

    Label titleLabel = new Label(title);
    titleLabel.setTextFill(Color.WHITE);
    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    Label subtitleLabel = new Label(subtitle);
    subtitleLabel.setTextFill(Color.LIGHTGRAY);
    subtitleLabel.setFont(Font.font("Arial", 12));

    card.getChildren().addAll(titleLabel, subtitleLabel);

    // 🔹 Make "Online" course clickable to open the URL
    if (title.equalsIgnoreCase("Online")) {
        card.setOnMouseClicked(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(
                    new java.net.URI("https://www.guitarseekh.com/membership?gad_source=2&gad_campaignid=22822221240&gclid=CjwKCAjwy7HEBhBJEiwA5hQNorN7LxzpwMXGvtDprEel_MgIbkwht_D_O-65kbchUAMrsexfcropNhoC5vAQAvD_BwE#nav_bar")
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    return card;
}

}
