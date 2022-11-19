package pl.lumido.oblechproject.gui.handCreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.hands.TwoPairs;
import pl.lumido.oblechproject.gui.ErrorWindow;

public class TwoPairController {
    @FXML
    public ChoiceBox<String> firstPairHeightChoiceBox, secondPairHeightChoiceBox;
    public TwoPairs twoPairs;


    public void handleButtonPressed(ActionEvent event) {
        String string = firstPairHeightChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "2s" -> twoPairs.firstCardIndex = 2;
            case "3s" -> twoPairs.firstCardIndex = 3;
            case "4s" -> twoPairs.firstCardIndex = 4;
            case "5s" -> twoPairs.firstCardIndex = 5;
            case "6s" -> twoPairs.firstCardIndex = 6;
            case "7s" -> twoPairs.firstCardIndex = 7;
            case "8s" -> twoPairs.firstCardIndex = 8;
            case "9s" -> twoPairs.firstCardIndex = 9;
            case "10s" -> twoPairs.firstCardIndex = 10;
            case "Jacks" -> twoPairs.firstCardIndex = 11;
            case "Queens" -> twoPairs.firstCardIndex = 12;
            case "Kings" -> twoPairs.firstCardIndex = 13;
            case "Aces" -> twoPairs.firstCardIndex = 14;
            default -> twoPairs.firstCardIndex = 1;
        }
        string = secondPairHeightChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "2s" -> twoPairs.secondCardIndex = 2;
            case "3s" -> twoPairs.secondCardIndex = 3;
            case "4s" -> twoPairs.secondCardIndex = 4;
            case "5s" -> twoPairs.secondCardIndex = 5;
            case "6s" -> twoPairs.secondCardIndex = 6;
            case "7s" -> twoPairs.secondCardIndex = 7;
            case "8s" -> twoPairs.secondCardIndex = 8;
            case "9s" -> twoPairs.secondCardIndex = 9;
            case "10s" -> twoPairs.secondCardIndex = 10;
            case "Jacks" -> twoPairs.secondCardIndex = 11;
            case "Queens" -> twoPairs.secondCardIndex = 12;
            case "Kings" -> twoPairs.secondCardIndex = 13;
            case "Aces" -> twoPairs.secondCardIndex = 14;
            default -> twoPairs.secondCardIndex = 1;
        }
        try {
            twoPairs.check();
        } catch (Exception exception) {
            new ErrorWindow(exception.getMessage()).show(new Stage());
            return;
        }
        Stage stage = (Stage) firstPairHeightChoiceBox.getScene().getWindow();
        stage.close();
    }
}
