package pl.lumido.oblechproject.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameWindowController {
    public NameWindow nameWindow;

    @FXML
    TextField nameField;

    public void handleButtonPressed(ActionEvent event){
        String currName  = nameField.getText();
        if (currName == null || currName.equals("")) return;
        nameWindow.name = currName;
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
