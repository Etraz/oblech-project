package pl.lumido.oblechproject.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.hands.*;

public class PlayerController {
    public Player player;
    public Button pokerButton;
    public Button oneCardButton;
    public Button fourOfAKindButton;
    public Button fullHouseButton;
    public Button threeOfAKindButton;
    public Button straightButton;
    public Button twoPairsButton;
    public Button pairButton;
    public Button checkButton;

    @FXML
    public HBox handBox;

    @FXML
    public VBox history, otherPlayers;

    public void createOneCard(ActionEvent event){
        synchronized (player) {
            player.lockButtons();
            OneCard oneCard = new OneCard();
            oneCard.show(new Stage());
            try {
                oneCard.check();
            }
            catch (Exception exception){
                player.unlockButtons();
                return;
            }
            player.setNextHand(oneCard);
            player.unlockButtons();
            player.notifyAll();
        }
    }

    public void createPair(ActionEvent event){
        synchronized (player) {
            player.lockButtons();
            OnePair onePair = new OnePair();
            onePair.show(new Stage());
            try {
                onePair.check();
            } catch (Exception exception) {
                player.unlockButtons();
                return;
            }
            player.setNextHand(onePair);
            player.unlockButtons();
            player.notifyAll();
        }
    }

    public void createTwoPairs(ActionEvent event){
        synchronized (player) {
            player.lockButtons();
            TwoPairs twoPairs = new TwoPairs();
            twoPairs.show(new Stage());
            try {
                twoPairs.check();
            } catch (Exception exception) {
                player.unlockButtons();
                return;
            }
            player.setNextHand(twoPairs);
            player.unlockButtons();
            player.notifyAll();
        }
    }

    public void createStraight(ActionEvent event){
        synchronized (player) {
            player.lockButtons();
            Straight straight = new Straight();
            straight.show(new Stage());
            try {
                straight.check();
            }
            catch (Exception exception){
                player.unlockButtons();
                return;
            }
            player.setNextHand(straight);
            player.unlockButtons();
            player.notifyAll();
        }
    }

    public void createThreeOfKind(ActionEvent event){
        player.lockButtons();
        synchronized (player) {
            ThreeOfAKind threeOfAKind = new ThreeOfAKind();
            threeOfAKind.show(new Stage());
            try {
                threeOfAKind.check();
            }
            catch (Exception exception){
                player.unlockButtons();
                return;
            }
            player.setNextHand(threeOfAKind);
            player.notifyAll();
        }
        player.unlockButtons();
    }

    public void createFullHouse(ActionEvent event){
        synchronized (player){
            player.lockButtons();
            FullHouse fullHouse = new FullHouse();
            fullHouse.show(new Stage());
            try {
                fullHouse.check();
            }catch (Exception exception){
                player.unlockButtons();
                return;
            }
            player.setNextHand(fullHouse);
            player.unlockButtons();
            player.notifyAll();
        }
    }

    public void createFourOfKind(ActionEvent event){
        synchronized (player) {
            player.lockButtons();
            FourOfAKind fourOfAKind = new FourOfAKind();
            fourOfAKind.show(new Stage());
            try {
                fourOfAKind.check();
            }
            catch (Exception exception){
                player.unlockButtons();
                return;
            }
            player.setNextHand(fourOfAKind);
            player.unlockButtons();
            player.notifyAll();
        }
    }

    public void createPoker(ActionEvent event){
        synchronized (player) {
            player.lockButtons();
            Poker poker = new Poker();
            poker.show(new Stage());
            try {
                poker.check();
            }
            catch (Exception exception){
                player.unlockButtons();
                return;
            }
            player.setNextHand(poker);
            player.unlockButtons();
            player.notifyAll();
        }
    }

    public void check(ActionEvent event){
        synchronized (player) {
            player.setNextHand(null);
            player.notifyAll();
        }
    }
}
