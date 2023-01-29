package pl.lumido.oblechproject.gui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.engine.cards.hands.Hand;

import java.io.IOException;
import java.util.Objects;

public abstract class Player {
    protected int numberOfCardsWanted = 1;
    protected CardSet cardSet = new CardSet();
    protected boolean isRegister = false, isPlaying = false;
    protected String name;

    public abstract Hand play(Hand currentHand);

    public int getNumberOfCardsWanted() {
        return numberOfCardsWanted;
    }

    public void setNumberOfCardsWanted(int numberOfCardsWanted) {
        this.numberOfCardsWanted = numberOfCardsWanted;
    }

    public CardSet getCardSet() {
        return cardSet;
    }

    public void setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        if (!isRegister() && playing) {
            throw new IllegalStateException("Players cannot play without registration");
        }
        isPlaying = playing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void close(){};

    public void show(Stage stage){};

    public void dealt(){};

    public void addToHistory(String string){};

    public void addOtherPlayers(String string){};

    public void clearCardSet() {
        cardSet.clear();
    }

}
