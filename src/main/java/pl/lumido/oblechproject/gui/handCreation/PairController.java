package pl.lumido.oblechproject.gui.handCreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.hands.OnePair;
import pl.lumido.oblechproject.gui.ErrorWindow;

public class PairController {
    @FXML
    public ChoiceBox<String> pairHeightChoiceBox;
    public OnePair onePair;


    public void handleButtonPressed(ActionEvent event) {
        String string = pairHeightChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "2s" -> onePair.cardIndex = 2;
            case "3s" -> onePair.cardIndex = 3;
            case "4s" -> onePair.cardIndex = 4;
            case "5s" -> onePair.cardIndex = 5;
            case "6s" -> onePair.cardIndex = 6;
            case "7s" -> onePair.cardIndex = 7;
            case "8s" -> onePair.cardIndex = 8;
            case "9s" -> onePair.cardIndex = 9;
            case "10s" -> onePair.cardIndex = 10;
            case "Jacks" -> onePair.cardIndex = 11;
            case "Queens" -> onePair.cardIndex = 12;
            case "Kings" -> onePair.cardIndex = 13;
            case "Aces" -> onePair.cardIndex = 14;
            default -> onePair.cardIndex = 1;
        }
        try {
            onePair.check();
        } catch (Exception exception) {
            new ErrorWindow(exception.getMessage()).show(new Stage());
            return;
        }
        Stage stage = (Stage) pairHeightChoiceBox.getScene().getWindow();
        stage.close();
    }
}
