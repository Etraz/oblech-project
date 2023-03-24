package pl.lumido.oblechproject.gui.handCreation;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.hands.FullHouse;
import pl.lumido.oblechproject.gui.ErrorWindow;

public class FullHouseController {
    @FXML
    public ChoiceBox<String> threeHeightChoiceBox, pairHeightChoiceBox;
    public FullHouse fullHouse;

    public void handleButtonPressed(ActionEvent event) {
        String string = threeHeightChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "2s" -> fullHouse.firstCardIndex = 2;
            case "3s" -> fullHouse.firstCardIndex = 3;
            case "4s" -> fullHouse.firstCardIndex = 4;
            case "5s" -> fullHouse.firstCardIndex = 5;
            case "6s" -> fullHouse.firstCardIndex = 6;
            case "7s" -> fullHouse.firstCardIndex = 7;
            case "8s" -> fullHouse.firstCardIndex = 8;
            case "9s" -> fullHouse.firstCardIndex = 9;
            case "10s" -> fullHouse.firstCardIndex = 10;
            case "Jacks" -> fullHouse.firstCardIndex = 11;
            case "Queens" -> fullHouse.firstCardIndex = 12;
            case "Kings" -> fullHouse.firstCardIndex = 13;
            case "Aces" -> fullHouse.firstCardIndex = 14;
            default -> fullHouse.firstCardIndex = 1;
        }
        string = pairHeightChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }

        switch (string) {
            case "2s" -> fullHouse.secondCardIndex = 2;
            case "3s" -> fullHouse.secondCardIndex = 3;
            case "4s" -> fullHouse.secondCardIndex = 4;
            case "5s" -> fullHouse.secondCardIndex = 5;
            case "6s" -> fullHouse.secondCardIndex = 6;
            case "7s" -> fullHouse.secondCardIndex = 7;
            case "8s" -> fullHouse.secondCardIndex = 8;
            case "9s" -> fullHouse.secondCardIndex = 9;
            case "10s" -> fullHouse.secondCardIndex = 10;
            case "Jacks" -> fullHouse.secondCardIndex = 11;
            case "Queens" -> fullHouse.secondCardIndex = 12;
            case "Kings" -> fullHouse.secondCardIndex = 13;
            case "Aces" -> fullHouse.secondCardIndex = 14;
            default -> fullHouse.secondCardIndex = 1;
        }
        try {
            fullHouse.check();
        } catch (Exception exception) {
            new ErrorWindow(exception.getMessage()).show(new Stage());
            return;
        }
        Stage stage = (Stage) threeHeightChoiceBox.getScene().getWindow();
        stage.close();
    }
}
