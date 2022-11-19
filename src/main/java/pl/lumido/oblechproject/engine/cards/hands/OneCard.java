package pl.lumido.oblechproject.engine.cards.hands;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.gui.handCreation.OneCardController;

import java.io.IOException;
import java.util.Objects;

public class OneCard implements Hand {
    public int cardIndex;
    OneCardController controller;

    @Override
    public int getHandIndex() {
        return 0;
    }

    @Override
    public boolean isItInReverse(CardSet cardSet) {
        return cardSet.getCardsOfGivenNumber(cardIndex).size() < 4;
    }

    @Override

    public boolean isSmaller(Hand hand) {
        if (this.getHandIndex() != hand.getHandIndex()) {
            return this.getHandIndex() < hand.getHandIndex();
        }
        OneCard oneCard = (OneCard) hand;
        return this.cardIndex <= oneCard.cardIndex;
    }

    @Override
    public void check() {
        if (cardIndex < 2 || cardIndex > 14) {
            throw new IllegalArgumentException("Wrong card index in Hand");
        }
    }

    public OneCard(int cardIndex) {
        this.cardIndex = cardIndex;
        check();
    }

    public OneCard() {
        this.cardIndex = 1;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("One ");
        if (cardIndex < 11) {
            toReturn.append(cardIndex);
        } else if (cardIndex == 11) {
            toReturn.append("Jack");
        } else if (cardIndex == 12) {
            toReturn.append("Queen");
        } else if (cardIndex == 13) {
            toReturn.append("King");
        } else {
            toReturn.append("Ace");
        }
        return toReturn.toString();
    }

    public void show(Stage stage) {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("OneCard.fxml")));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.oneCard = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        controller.cardHeightChoiceBox.setItems(FXCollections.observableArrayList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));
        controller.cardHeightChoiceBox.setItems(FXCollections.observableArrayList("8", "9", "10", "Jack", "Queen", "King", "Ace"));
        assert root != null;
        Scene scene = new Scene(root, 300, 100);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
