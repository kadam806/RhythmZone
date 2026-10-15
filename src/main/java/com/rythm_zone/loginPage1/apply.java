package com.rythm_zone.loginPage1;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class apply extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(8));
        root.setStyle("-fx-background-color: #0B101A;");

        // ===== Top section with Back Button & Centered Title =====
        StackPane topBar = new StackPane();

        Button backButton = new Button("⬅ Back");
        backButton.setStyle(
            "-fx-background-color: #00b894; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-background-radius: 8px; " +
            "-fx-padding: 6 16;"
        );
        backButton.setOnAction(e -> {
            try {
                new consert().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        StackPane.setAlignment(backButton, Pos.CENTER_LEFT);
        StackPane.setMargin(backButton, new Insets(0, 0, 0, 10));

        Text title = new Text("🎼 Concert Showcase");
        title.setFont(Font.font("Georgia", FontWeight.EXTRA_BOLD, 36));
        title.setFill(Color.web("#F5F8F8"));
        StackPane.setAlignment(title, Pos.CENTER);

        topBar.getChildren().addAll(backButton, title);
        topBar.setPadding(new Insets(10));

        root.setTop(topBar);

        // ===== Details section =====
        VBox detailsBox = new VBox(6,
            createLabel("Concert: Symphony Night"),
            createLabel("Venue: City Opera House"),
            createLabel("Date: August 15, 2025")
        );
        detailsBox.setAlignment(Pos.CENTER);
        detailsBox.setPadding(new Insets(5, 0, 5, 0));
        root.setCenter(detailsBox);

        // ===== Form =====
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        form.setHgap(8);
        form.setVgap(6);
        form.setPadding(new Insets(8));

        String fieldStyle =
            "-fx-background-color: #1A1F2B; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #00CED1; " +
            "-fx-border-radius: 5px;";

        TextField nameField = new TextField();
        nameField.setStyle(fieldStyle);

        TextField emailField = new TextField();
        emailField.setStyle(fieldStyle);

        addRow(form, 0, "Full Name:", nameField);
        addRow(form, 1, "Email:", emailField);

        ChoiceBox<String> roles = new ChoiceBox<>();
        roles.getItems().addAll("Performer", "Volunteer", "Audience");
        roles.setValue("Audience");
        roles.setStyle(fieldStyle + "-fx-control-inner-background: #c4c7cfff;");
        addRow(form, 2, "Interested as:", roles);

        // Message field
        Label msgLabel = new Label("Message (optional):");
        msgLabel.setTextFill(Color.web("#00FFFF"));
        msgLabel.setFont(Font.font(13));

        TextArea msg = new TextArea();
        msg.setPrefRowCount(3);
        msg.setStyle(
            "-fx-control-inner-background: #1A1F2B; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #00CED1; " +
            "-fx-border-radius: 5px;"
        );

        form.add(msgLabel, 0, 3);
        form.add(msg, 1, 3);

        // Submit button
        Button submit = new Button("Submit Application");
        submit.setStyle(
            "-fx-background-color:#00CED1; " +
            "-fx-text-fill:white; -fx-font-size:14px; " +
            "-fx-background-radius:5px;"
        );
        submit.setMaxWidth(Double.MAX_VALUE);
        submit.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Submission");
            alert.setHeaderText(null);
            alert.setContentText("Application submitted!");
            alert.initOwner(primaryStage);
            alert.showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) {
                    try {
                        new consert().start(primaryStage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        });

        // Button container with reduced spacing
        HBox btnBox = new HBox(submit);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(10);
        btnBox.setPadding(new Insets(5, 0, 0, 0)); // reduced top padding
        form.add(btnBox, 0, 4, 2, 1);

        root.setBottom(form);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setTitle("Concert Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label createLabel(String text) {
        Label lbl = new Label(text);
        lbl.setTextFill(Color.WHITE);
        lbl.setFont(Font.font("Georgia", FontWeight.SEMI_BOLD, 14));
        return lbl;
    }

    private void addRow(GridPane grid, int row, String labelText, Control input) {
        Label lbl = new Label(labelText);
        lbl.setTextFill(Color.web("#00FFFF"));
        lbl.setFont(Font.font(13));
        grid.add(lbl, 0, row);
        input.setMaxWidth(250);
        grid.add(input, 1, row);
    }
}
