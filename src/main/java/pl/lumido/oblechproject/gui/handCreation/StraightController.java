package pl.lumido.oblechproject.gui.handCreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.hands.OneCard;
import pl.lumido.oblechproject.engine.cards.hands.Straight;
import pl.lumido.oblechproject.gui.ErrorWindow;

public class StraightController {
    @FXML
    public ChoiceBox<String> startingCardHeightChoiceBox;
    public Straight straight;


    public void handleButtonPressed(ActionEvent event) {
        String string = startingCardHeightChoiceBox.getValue();
        if (string == null) {
            new ErrorWindow("You need to choose card height").show(new Stage());
            return;
        }
        switch (string) {
            case "2" -> straight.cardIndex = 2;
            case "3" -> straight.cardIndex = 3;
            case "4" -> straight.cardIndex = 4;
            case "5" -> straight.cardIndex = 5;
            case "6" -> straight.cardIndex = 6;
            case "7" -> straight.cardIndex = 7;
            case "8" -> straight.cardIndex = 8;
            case "9" -> straight.cardIndex = 9;
            case "10" -> straight.cardIndex = 10;
            default -> straight.cardIndex = 1;
        }
        try {
            straight.check();
        } catch (Exception exception) {
            new ErrorWindow(exception.getMessage()).show(new Stage());
            return;
        }
        Stage stage = (Stage) startingCardHeightChoiceBox.getScene().getWindow();
        stage.close();
    }
}
