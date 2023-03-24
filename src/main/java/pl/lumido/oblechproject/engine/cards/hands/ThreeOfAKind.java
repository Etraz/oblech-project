package pl.lumido.oblechproject.engine.cards.hands;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.gui.Admin;
import pl.lumido.oblechproject.gui.handCreation.ThreeOfAKindController;

import java.io.IOException;
import java.util.Objects;

public class ThreeOfAKind implements Hand {
    public int cardIndex;
    ThreeOfAKindController controller;

    @Override
    public boolean isItInReverse(CardSet cardSet) {
        return cardSet.getCardsOfGivenNumber(cardIndex).size()<2;
    }
    @Override
    public boolean isSmaller(Hand hand) {
        if(this.getHandIndex() != hand.getHandIndex()){
            return this.getHandIndex() < hand.getHandIndex();
        }
        ThreeOfAKind threeOfAKind = (ThreeOfAKind) hand;
        return this.cardIndex <= threeOfAKind.cardIndex;
    }

    public ThreeOfAKind(int cardIndex){
        this.cardIndex = cardIndex;
        check();
    }

    @Override
    public void check(){
        if(cardIndex < 2 || cardIndex > 14){
            throw new IllegalArgumentException("Wrong card index in Hand");
        }
    }

    @Override
    public int getHandIndex() {
        return 4;
    }

    public ThreeOfAKind(){
        cardIndex = 1;
    }

    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Three of ");
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
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("ThreeOfAKind.fxml")));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.threeOfAKind = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        controller.threeHeightChoiceBox.setItems(FXCollections.observableArrayList("2s","3s","4s","5s","6s","7s","8s","9s","10s","Jacks","Queens","Kings","Aces"));
        controller.threeHeightChoiceBox.setItems(FXCollections.observableArrayList("8s","9s","10s","Jacks","Queens","Kings","Aces"));
        assert root != null;
        Scene scene = new Scene(root,300,100);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public Hand nextHand(){
        Hand toReturn;
        if (cardIndex != 14){
            toReturn = new ThreeOfAKind(cardIndex + 1);
        }
        else {
            toReturn = new FullHouse(Admin.game.getLowestCard(), Admin.game.getLowestCard() + 1);
        }
        return toReturn;
    }
}
