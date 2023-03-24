package pl.lumido.oblechproject.gui.handCreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.hands.FourOfAKind;
import pl.lumido.oblechproject.gui.ErrorWindow;

public class FourOfAKindController {
    @FXML
    public ChoiceBox<String> fourHeightChoiceBox;
    public FourOfAKind fourOfAKind;


    public void handleButtonPressed(ActionEvent event) {
        String string = fourHeightChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "2s" -> fourOfAKind.cardIndex = 2;
            case "3s" -> fourOfAKind.cardIndex = 3;
            case "4s" -> fourOfAKind.cardIndex = 4;
            case "5s" -> fourOfAKind.cardIndex = 5;
            case "6s" -> fourOfAKind.cardIndex = 6;
            case "7s" -> fourOfAKind.cardIndex = 7;
            case "8s" -> fourOfAKind.cardIndex = 8;
            case "9s" -> fourOfAKind.cardIndex = 9;
            case "10s" -> fourOfAKind.cardIndex = 10;
            case "Jacks" -> fourOfAKind.cardIndex = 11;
            case "Queens" -> fourOfAKind.cardIndex = 12;
            case "Kings" -> fourOfAKind.cardIndex = 13;
            case "Aces" -> fourOfAKind.cardIndex = 14;
            default -> fourOfAKind.cardIndex = 1;
        }
        try {
            fourOfAKind.check();
        } catch (Exception exception) {
            new ErrorWindow(exception.getMessage()).show(new Stage());
            return;
        }
        Stage stage = (Stage) fourHeightChoiceBox.getScene().getWindow();
        stage.close();
    }
}
