package pl.lumido.oblechproject.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ErrorWindow {
    String message;
    public ErrorWindow(String message){
        this.message = message;
    }
    public void show(Stage stage){
        Parent root = null;
        try {
            // root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NameWindow.fxml")));
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("ErrorWindow.fxml")));
            root = fxmlLoader.load();
            ErrorWindowController controller = fxmlLoader.getController();
            controller.errorLabel.setText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,300,100);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }
}
