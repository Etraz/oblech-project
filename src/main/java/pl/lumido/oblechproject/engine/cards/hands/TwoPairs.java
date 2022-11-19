package pl.lumido.oblechproject.engine.cards.hands;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.gui.handCreation.TwoPairController;

import java.io.IOException;
import java.util.Objects;

public class TwoPairs implements Hand {
    //firstCardIndex > secondCardIndex
    public int firstCardIndex;
    public int secondCardIndex;
    TwoPairController controller;

    @Override
    public boolean isItInReverse(CardSet cardSet) {
        return (cardSet.getCardsOfGivenNumber(firstCardIndex).size()<3 && cardSet.getCardsOfGivenNumber(secondCardIndex).size() < 3);
    }
    @Override
    public boolean isSmaller(Hand hand) {
        if(this.getHandIndex() != hand.getHandIndex()){
            return this.getHandIndex() < hand.getHandIndex();
        }
        TwoPairs twoPairs = (TwoPairs) hand;
        if(this.firstCardIndex != twoPairs.firstCardIndex){
            return this.firstCardIndex <= twoPairs.secondCardIndex;
        }
        return this.secondCardIndex <= twoPairs.secondCardIndex;

    }

    @Override
    public void check() {
        if(firstCardIndex < 2 || firstCardIndex > 14 || secondCardIndex < 2 || secondCardIndex > 14){
            throw new IllegalArgumentException("Wrong card index in Hand");
        }
        if(firstCardIndex == secondCardIndex){
            throw new IllegalArgumentException("Pairs are the same");
        }
        if(secondCardIndex > firstCardIndex){
            int foo = firstCardIndex;
            firstCardIndex = secondCardIndex;
            secondCardIndex = foo;
        }
    }

    @Override
    public int getHandIndex(){
        return 2;
    }

    public TwoPairs(int firstCardIndex, int secondCardIndex) {
        this.firstCardIndex = firstCardIndex;
        this.secondCardIndex = secondCardIndex;
        check();
    }

    public TwoPairs(){
        firstCardIndex = 1;
        secondCardIndex = 1;
    }

    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        if (firstCardIndex < 11) {
            toReturn.append(firstCardIndex).append("s");
        } else if (firstCardIndex == 11) {
            toReturn.append("Jacks");
        } else if (firstCardIndex == 12) {
            toReturn.append("Queens");
        } else if (firstCardIndex == 13) {
            toReturn.append("Kings");
        } else {
            toReturn.append("Aces");
        }
        toReturn.append(" and ");
        if (secondCardIndex < 11) {
            toReturn.append(secondCardIndex).append("s");
        } else if (secondCardIndex == 11) {
            toReturn.append("Jacks");
        } else if (secondCardIndex == 12) {
            toReturn.append("Queens");
        } else if (secondCardIndex == 13) {
            toReturn.append("Kings");
        } else {
            toReturn.append("Aces");
        }
        return toReturn.toString();
    }

    public void show(Stage stage){
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("TwoPairs.fxml")));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.twoPairs = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        controller.firstPairHeightChoiceBox.setItems(FXCollections.observableArrayList("2s","3s","4s","5s","6s","7s","8s","9s","10s","Jacks","Queens","Kings","Aces"));
//        controller.secondPairHeightChoiceBox.setItems(FXCollections.observableArrayList("2s","3s","4s","5s","6s","7s","8s","9s","10s","Jacks","Queens","Kings","Aces"));
        controller.firstPairHeightChoiceBox.setItems(FXCollections.observableArrayList("8s","9s","10s","Jacks","Queens","Kings","Aces"));
        controller.secondPairHeightChoiceBox.setItems(FXCollections.observableArrayList("8s","9s","10s","Jacks","Queens","Kings","Aces"));
        assert root != null;
        Scene scene = new Scene(root,300,100);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
