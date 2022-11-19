package pl.lumido.oblechproject.engine.cards.hands;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.COLOR;
import pl.lumido.oblechproject.engine.cards.COLORFunctions;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.gui.handCreation.PokerController;

import java.io.IOException;
import java.util.Objects;

public class Poker implements Hand {
    public int cardIndex;
    public COLOR color;
    PokerController controller;

    @Override
    public boolean isItInReverse(CardSet cardSet) {
        return (COLORFunctions.doseReversSetContains((cardSet.getCardsOfGivenNumber(cardIndex)), color)
                && COLORFunctions.doseReversSetContains((cardSet.getCardsOfGivenNumber(cardIndex + 1)), color)
                && COLORFunctions.doseReversSetContains((cardSet.getCardsOfGivenNumber(cardIndex + 2)), color)
                && COLORFunctions.doseReversSetContains((cardSet.getCardsOfGivenNumber(cardIndex + 3)), color)
                && COLORFunctions.doseReversSetContains((cardSet.getCardsOfGivenNumber(cardIndex + 4)), color));
    }

    @Override
    public boolean isSmaller(Hand hand) {
        if (this.getHandIndex() != hand.getHandIndex()) {
            return this.getHandIndex() < hand.getHandIndex();
        }
        Poker poker = (Poker) hand;
        if (this.cardIndex != poker.cardIndex) {
            return this.cardIndex <= poker.cardIndex;
        }
        return COLORFunctions.isSmaller(this.color, poker.color);
    }

    @Override
    public void check() {
        if (cardIndex < 2 || cardIndex > 10 || color == null) {
            throw new IllegalArgumentException("Wrong card index in Hand");
        }
    }

    @Override
    public int getHandIndex() {
        return 7;
    }

    public Poker(int cardIndex, COLOR color) {
        this.cardIndex = cardIndex;
        this.color = color;
        check();
    }

    public Poker() {
        cardIndex = 1;
        color = COLOR.SPADES;
    }

    @Override
    public String toString() {
        return "Poker from " + cardIndex + " in " + color;
    }

    public void show(Stage stage) {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Poker.fxml")));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.poker = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        controller.cardHeightChoiceBox.setItems(FXCollections.observableArrayList("2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "10s"));
        controller.cardHeightChoiceBox.setItems(FXCollections.observableArrayList("8","9","10"));
        controller.cardColorChoiceBox.setItems(FXCollections.observableArrayList("Spades", "Hearts", "Diamonds", "Clubs"));

        assert root != null;
        Scene scene = new Scene(root, 300, 100);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
