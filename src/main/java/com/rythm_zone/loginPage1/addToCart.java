package com.rythm_zone.loginPage1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.*;

public class addToCart extends Application {
     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
    private final List<CartItem> cartItems = new ArrayList<>();
    private final Label cartLabel = new Label("Cart: 0 items • Total: ₹0.00");
    private final VBox cartBox = new VBox(5);

    @Override
    public void start(Stage stage) {
        stage.setTitle("Musical Instruments Store");

        // Sample instruments
        List<Instrument> instruments = List.of(
            new Instrument("Acoustic Guitar", 4500)
            
        );

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));
        int cols = 3, row = 0, col = 0;

        for (Instrument inst : instruments) {
            VBox card = createInstrumentCard(inst);
            grid.add(card, col, row);
            if (++col == cols) { col = 0; row++; }
        }

        VBox leftPane = new VBox(10, new ScrollPane(grid));
        leftPane.setPrefWidth(600);

        VBox rightPane = new VBox(10, cartLabel, new Separator(), new ScrollPane(cartBox));
        rightPane.setPadding(new Insets(10));
        rightPane.setPrefWidth(300);

        HBox root = new HBox(leftPane, rightPane);
        stage.setScene(new Scene(root, screenWidth, screenHeight));
        stage.show();
    }

    private VBox createInstrumentCard(Instrument inst) {
        ImageView image = new ImageView(new Image("https://via.placeholder.com/150"));
        image.setFitWidth(150);
        image.setFitHeight(150);

        Label name = new Label(inst.name);
        Label price = new Label("₹" + inst.price);
        Button addBtn = new Button("Add to Cart");
        
            addBtn.setOnAction(e -> {
    cartItems.add(new CartItem(inst.name, inst.price));
    updateCart();
});

        VBox box = new VBox(5, image, name, price, addBtn);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
        return box;
    }

    private void updateCart() {
        cartBox.getChildren().clear();
        int total = 0;
        for (CartItem ci : cartItems) {
            cartBox.getChildren().add(new Label(ci.name + " — ₹" + ci.price));
            total += ci.price;
        }
        cartLabel.setText("Cart: " + cartItems.size() + " items • Total: ₹" + total);
    }

    private record Instrument(String name, int price) {}
    private record CartItem(String name, int price) {}
}