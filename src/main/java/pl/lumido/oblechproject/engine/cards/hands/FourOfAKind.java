package pl.lumido.oblechproject.engine.cards.hands;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.COLOR;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.gui.Admin;
import pl.lumido.oblechproject.gui.handCreation.FourOfAKindController;

import java.io.IOException;
import java.util.Objects;

public class FourOfAKind implements Hand {
    public int cardIndex;
    FourOfAKindController controller;

    @Override
    public boolean isItInReverse(CardSet cardSet) {
        return cardSet.getCardsOfGivenNumber(cardIndex).isEmpty();
    }
    @Override
    public boolean isSmaller(Hand hand) {
        if(this.getHandIndex() != hand.getHandIndex()){
            return this.getHandIndex() < hand.getHandIndex();
        }
        FourOfAKind fourOfAKind = (FourOfAKind) hand;
        return this.cardIndex <= fourOfAKind.cardIndex;
    }

    @Override
    public void check() {
        if(cardIndex < 2 || cardIndex > 14){
            throw new IllegalArgumentException("Wrong card index in Hand");
        }
    }

    @Override
    public int getHandIndex(){
        return 6;
    }

    public FourOfAKind(int cardIndex){
        this.cardIndex = cardIndex;
        check();
    }

    public FourOfAKind(){
        cardIndex = 1;
    }

    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Four of ");
        if (cardIndex < 11) {
            toReturn.append(cardIndex).append("s");
        } else if (cardIndex == 11) {
            toReturn.append("Jacks");
        } else if (cardIndex == 12) {
            toReturn.append("Queens");
        } else if (cardIndex == 13) {
            toReturn.append("Kings");
        } else {
            toReturn.append("Aces");
        }
        return toReturn.toString();
    }

    public void show(Stage stage){
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("FourOfAKind.fxml")));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.fourOfAKind = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        controller.fourHeightChoiceBox.setItems(FXCollections.observableArrayList("2s","3s","4s","5s","6s","7s","8s","9s","10s","Jacks","Queens","Kings","Aces"));
        controller.fourHeightChoiceBox.setItems(FXCollections.observableArrayList("8s","9s","10s","Jacks","Queens","Kings","Aces"));
        assert root != null;
        Scene scene = new Scene(root,300,100);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public Hand nextHand(){
        Hand toReturn;
        if (cardIndex != 14){
            toReturn = new FourOfAKind(cardIndex + 1);
        }
        else {
            toReturn = new Poker(Admin.game.getLowestCard(), COLOR.CLUBS);
        }
        return toReturn;
    }
}
