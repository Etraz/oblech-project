package pl.lumido.oblechproject.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.jar.Attributes;

public class NameWindow {
    String name;

    public String ask(Stage stage){
        Parent root = null;
        try {
            // root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NameWindow.fxml")));
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("NameWindow.fxml")));
            root = fxmlLoader.load();
            NameWindowController controller = fxmlLoader.getController();
            controller.nameWindow = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,300,100);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        return name;
    }
}
