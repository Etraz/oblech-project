package pl.lumido.oblechproject.engine.cards.hands;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.gui.handCreation.FullHouseController;

import java.io.IOException;
import java.util.Objects;

public class FullHouse implements Hand {
    public int firstCardIndex;
    public int secondCardIndex;
    FullHouseController controller;

    @Override
    public boolean isItInReverse(CardSet cardSet) {
        return (cardSet.getCardsOfGivenNumber(firstCardIndex).size()<2 && cardSet.getCardsOfGivenNumber(secondCardIndex).size() < 3);
    }
    @Override
    public boolean isSmaller(Hand hand) {
        if(this.getHandIndex() != hand.getHandIndex()){
            return this.getHandIndex() < hand.getHandIndex();
        }
        FullHouse fullHouse = (FullHouse) hand;
        if(this.firstCardIndex != fullHouse.firstCardIndex){
            return this.firstCardIndex <= fullHouse.secondCardIndex;
        }
        return this.secondCardIndex <= fullHouse.secondCardIndex;

    }

    @Override
    public void check() {
        if(firstCardIndex < 2 || firstCardIndex > 14 || secondCardIndex < 2 || secondCardIndex > 14){
            throw new IllegalArgumentException("Wrong card index in Hand");
        }
        if(firstCardIndex == secondCardIndex){
            throw new IllegalArgumentException("OnePair and Three are the same");
        }
    }

    @Override
    public int getHandIndex(){
        return 5;
    }

    public FullHouse(int firstCardIndex, int secondCardIndex) {
        this.firstCardIndex = firstCardIndex;
        this.secondCardIndex = secondCardIndex;
        check();
    }

    public FullHouse(){
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
        toReturn.append(" on ");
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
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("FullHouse.fxml")));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.fullHouse = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        controller.threeHeightChoiceBox.setItems(FXCollections.observableArrayList("2s","3s","4s","5s","6s","7s","8s","9s","10s","Jacks","Queens","Kings","Aces"));
//        controller.pairHeightChoiceBox.setItems(FXCollections.observableArrayList("2s","3s","4s","5s","6s","7s","8s","9s","10s","Jacks","Queens","Kings","Aces"));
        controller.threeHeightChoiceBox.setItems(FXCollections.observableArrayList("8s","9s","10s","Jacks","Queens","Kings","Aces"));
        controller.pairHeightChoiceBox.setItems(FXCollections.observableArrayList("8s","9s","10s","Jacks","Queens","Kings","Aces"));
        assert root != null;
        Scene scene = new Scene(root,300,100);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
