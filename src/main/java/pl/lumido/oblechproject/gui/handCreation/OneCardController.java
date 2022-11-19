package pl.lumido.oblechproject.gui.handCreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.hands.Hand;
import pl.lumido.oblechproject.engine.cards.hands.OneCard;
import pl.lumido.oblechproject.gui.ErrorWindow;

public class OneCardController {
    @FXML
    public ChoiceBox<String> cardHeightChoiceBox;
    public OneCard oneCard;


    public void handleButtonPressed(ActionEvent event) {
        String string = cardHeightChoiceBox.getValue();
        if (string == null){
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "2" -> oneCard.cardIndex = 2;
            case "3" -> oneCard.cardIndex = 3;
            case "4" -> oneCard.cardIndex = 4;
            case "5" -> oneCard.cardIndex = 5;
            case "6" -> oneCard.cardIndex = 6;
            case "7" -> oneCard.cardIndex = 7;
            case "8" -> oneCard.cardIndex = 8;
            case "9" -> oneCard.cardIndex = 9;
            case "10" -> oneCard.cardIndex = 10;
            case "Jack" -> oneCard.cardIndex = 11;
            case "Queen" -> oneCard.cardIndex = 12;
            case "King" -> oneCard.cardIndex = 13;
            case "Ace" -> oneCard.cardIndex = 14;
            default -> oneCard.cardIndex = 1;
        }
        try {
            oneCard.check();
        } catch (Exception exception) {
            new ErrorWindow(exception.getMessage()).show(new Stage());
            return;
        }
        Stage stage = (Stage) cardHeightChoiceBox.getScene().getWindow();
        stage.close();
    }
}
