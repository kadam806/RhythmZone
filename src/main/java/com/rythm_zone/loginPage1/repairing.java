package com.rythm_zone.loginPage1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class repairing extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Instruments Repair Service");

        // Back Button
        Button backButton = new Button("⬅ Back ");
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: #f8f4f6ff; -fx-text-fill: white; -fx-border-width: 2px;");
        backButton.setOnAction(e -> {
            home1 homePage = new home1();
            try {
                homePage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Header
        Text header = new Text("Musical Instrument Repair Center");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        header.setFill(Color.WHITE);

        // Form Labels and Fields
        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();

        Label instrumentLabel = new Label("Instrument Type:");
        ComboBox<String> instrumentBox = new ComboBox<>();
        instrumentBox.getItems().addAll("Guitar", "Violin", "Flute", "Piano", "Tabla", "Harmonium", "Other");

        Label issueLabel = new Label("Describe the Issue:");
        TextArea issueField = new TextArea();
        issueField.setPrefRowCount(4);

        Label contactLabel = new Label("Contact Number:");
        TextField contactField = new TextField();

        Label dateLabel = new Label("Preferred Date:");
        DatePicker datePicker = new DatePicker();

        Button submitButton = new Button("Submit Repair Request");
        submitButton.setStyle("-fx-background-color: #151251ff; -fx-text-fill: white; -fx-font-weight: bold;");
        submitButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Repair Request Submitted");
            alert.setHeaderText("Thank you, " + nameField.getText() + "!");
            alert.setContentText("We will contact you soon regarding your " +
                    instrumentBox.getValue() + ".");
            alert.showAndWait();

            // Optionally, go back to homepage
            home1 homePage = new home1();
            try {
                homePage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Apply white text to all labels
        Label[] labels = { nameLabel, instrumentLabel, issueLabel, contactLabel, dateLabel };
        for (Label label : labels) {
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font("System", FontWeight.NORMAL, 14));
        }

        // Layout for form
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setVgap(15);
        formGrid.setHgap(10);
        formGrid.setPadding(new Insets(20));

        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameField, 1, 0);

        formGrid.add(instrumentLabel, 0, 1);
        formGrid.add(instrumentBox, 1, 1);

        formGrid.add(issueLabel, 0, 2);
        formGrid.add(issueField, 1, 2);

        formGrid.add(contactLabel, 0, 3);
        formGrid.add(contactField, 1, 3);

        formGrid.add(dateLabel, 0, 4);
        formGrid.add(datePicker, 1, 4);

        formGrid.add(submitButton, 1, 5);

        // Main Layout
        VBox mainLayout = new VBox(30, backButton, header, formGrid);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: #0B101A;");
        mainLayout.setPadding(new Insets(30));

        Scene scene = new Scene(mainLayout, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
