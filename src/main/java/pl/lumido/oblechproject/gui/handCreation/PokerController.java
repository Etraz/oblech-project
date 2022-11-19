package pl.lumido.oblechproject.gui.handCreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.COLOR;
import pl.lumido.oblechproject.engine.cards.hands.Poker;
import pl.lumido.oblechproject.engine.cards.hands.TwoPairs;
import pl.lumido.oblechproject.gui.ErrorWindow;

public class PokerController {
    @FXML
    public ChoiceBox<String> cardHeightChoiceBox, cardColorChoiceBox;
    public Poker poker;


    public void handleButtonPressed(ActionEvent event) {
        String string = cardHeightChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "2" -> poker.cardIndex = 2;
            case "3" -> poker.cardIndex = 3;
            case "4" -> poker.cardIndex = 4;
            case "5" -> poker.cardIndex = 5;
            case "6" -> poker.cardIndex = 6;
            case "7" -> poker.cardIndex = 7;
            case "8" -> poker.cardIndex = 8;
            case "9" -> poker.cardIndex = 9;
            case "10" -> poker.cardIndex = 10;
            case "Jack" -> poker.cardIndex = 11;
            case "Queen" -> poker.cardIndex = 12;
            case "King" -> poker.cardIndex = 13;
            case "Ace" -> poker.cardIndex = 14;
            default -> poker.cardIndex = 1;
        }
        string = cardColorChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "Spades" -> poker.color = COLOR.SPADES;
            case "Hearts" -> poker.color = COLOR.HEARTS;
            case "Diamonds" -> poker.color = COLOR.DIAMONDS;
            case "Clubs" -> poker.color = COLOR.CLUBS;
            default -> poker.color=null;
        }
        try {
            poker.check();
        } catch (Exception exception) {
            new ErrorWindow(exception.getMessage()).show(new Stage());
            return;
        }
        Stage stage = (Stage) cardHeightChoiceBox.getScene().getWindow();
        stage.close();
    }
}
