package pl.lumido.oblechproject.engine.cards.hands;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.gui.Admin;
import pl.lumido.oblechproject.gui.handCreation.StraightController;

import java.io.IOException;
import java.util.Objects;

public class Straight implements Hand {
    public int cardIndex;
    StraightController controller;

    @Override
    public boolean isItInReverse(CardSet cardSet) {
        return (cardSet.getCardsOfGivenNumber(cardIndex).size()<4
        && cardSet.getCardsOfGivenNumber(cardIndex+1).size()<4
        && cardSet.getCardsOfGivenNumber(cardIndex+2).size()<4
        && cardSet.getCardsOfGivenNumber(cardIndex+3).size()<4
        && cardSet.getCardsOfGivenNumber(cardIndex+4).size()<4);
    }
    @Override
    public boolean isSmaller(Hand hand) {
        if(this.getHandIndex() != hand.getHandIndex()){
            return this.getHandIndex() < hand.getHandIndex();
        }
        Straight straight = (Straight) hand;
        return this.cardIndex <= straight.cardIndex;
    }

    @Override
    public void check() {
        if(cardIndex < 2 || cardIndex > 10){
            throw new IllegalArgumentException("Wrong card index in Hand");
        }
    }

    @Override
    public int getHandIndex(){
        return 3;
    }

    public Straight(int cardIndex){
        this.cardIndex = cardIndex;
        check();
    }

    public Straight(){
        cardIndex = 1;
    }

    @Override
    public String toString(){
        return "Straight from " + cardIndex;
    }

    public void show(Stage stage){
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Straight.fxml")));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.straight = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        controller.startingCardHeightChoiceBox.setItems(FXCollections.observableArrayList("2","3","4","5","6","7","8","9","10"));
        controller.startingCardHeightChoiceBox.setItems(FXCollections.observableArrayList("8","9","10"));
        assert root != null;
        Scene scene = new Scene(root,300,100);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public Hand nextHand(){
        Hand toReturn;
        if (cardIndex != 10){
            toReturn = new Straight(cardIndex + 1);
        }
        else {
            toReturn = new ThreeOfAKind(Admin.game.getLowestCard());
        }
        return toReturn;
    }
}
