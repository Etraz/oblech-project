package pl.lumido.oblechproject.gui.handCreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.hands.ThreeOfAKind;
import pl.lumido.oblechproject.gui.ErrorWindow;

public class ThreeOfAKindController {
    @FXML
    public ChoiceBox<String> threeHeightChoiceBox;
    public ThreeOfAKind threeOfAKind;


    public void handleButtonPressed(ActionEvent event) {
        String string = threeHeightChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "2s" -> threeOfAKind.cardIndex = 2;
            case "3s" -> threeOfAKind.cardIndex = 3;
            case "4s" -> threeOfAKind.cardIndex = 4;
            case "5s" -> threeOfAKind.cardIndex = 5;
            case "6s" -> threeOfAKind.cardIndex = 6;
            case "7s" -> threeOfAKind.cardIndex = 7;
            case "8s" -> threeOfAKind.cardIndex = 8;
            case "9s" -> threeOfAKind.cardIndex = 9;
            case "10s" -> threeOfAKind.cardIndex = 10;
            case "Jacks" -> threeOfAKind.cardIndex = 11;
            case "Queens" -> threeOfAKind.cardIndex = 12;
            case "Kings" -> threeOfAKind.cardIndex = 13;
            case "Aces" -> threeOfAKind.cardIndex = 14;
            default -> threeOfAKind.cardIndex = 1;
        }
        try {
            threeOfAKind.check();
        } catch (Exception exception) {
            new ErrorWindow(exception.getMessage()).show(new Stage());
            return;
        }
        Stage stage = (Stage) threeHeightChoiceBox.getScene().getWindow();
        stage.close();
    }
}
