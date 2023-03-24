package pl.lumido.oblechproject.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorWindowController {

    public Label errorLabel;

    public void close(ActionEvent event) {
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        stage.close();
    }
}
