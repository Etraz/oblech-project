package pl.lumido.oblechproject.gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.engine.cards.hands.*;

import java.io.IOException;
import java.util.Objects;

public class HumanPlayer extends Player{
    private Hand nextHand;
    private HumanPlayerController controller;

    //returns null when checks
    public synchronized Hand play(Hand currentHand) {
        unlockButtons();
        nextHand = new EmptyHand();
        do {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (nextHand != null && nextHand.isSmaller(currentHand)) {
                ErrorWindow errorWindow = new ErrorWindow("New hand need to be greater");
                Platform.runLater(() -> errorWindow.show(new Stage()));
            }
        } while (nextHand != null && nextHand.isSmaller(currentHand));
        lockButtons();
        return nextHand;
    }

    @Override
    public void setPlaying(boolean playing) {
        if (!isRegister() && playing) {
            throw new IllegalStateException("Players cannot play without registration");
        }
        isPlaying = playing;
        if (!playing) {
            Platform.runLater(() -> controller.handBox.getChildren().clear());
        }
    }

    public synchronized void setNextHand(Hand nextHand) {
        this.nextHand = nextHand;
        notifyAll();
    }

    @Override
    public void close() {
        Platform.runLater(() -> ((Stage) controller.handBox.getScene().getWindow()).close());

    }

    @Override
    public void show(Stage stage) {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("HumanPlayer.fxml")));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.player = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root, 600, 400);
        stage.setMinHeight(450);
        stage.setMinWidth(650);
        stage.setScene(scene);
        stage.setTitle(this.getName() + " window");
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
    }


    public void askForName() {
        NameWindow nameWindow = new NameWindow();
        name = nameWindow.ask(new Stage());
    }

    @Override
    public void dealt() {
        Platform.runLater(() -> cardSet.showInHBox(controller.handBox));
    }

    @Override
    public void addToHistory(String string) {
        Platform.runLater(() -> {
            Label label = new Label(string);
            label.setFont(new Font("Arial", 16));
            controller.history.getChildren().add(label);
        });
    }

    @Override
    public void addOtherPlayers(String string) {
        Platform.runLater(() -> {
            Label label = new Label(string);
            label.setFont(new Font("Arial", 16));
            controller.otherPlayers.getChildren().add(label);
        });
    }

    @Override
    public void clearCardSet() {
        cardSet.clear();
        Platform.runLater(() -> {
            controller.otherPlayers.getChildren().clear();
            controller.history.getChildren().clear();
            controller.handBox.getChildren().clear();
        });
    }

    public void unlockButtons() {
        controller.checkButton.setDisable(false);
        controller.oneCardButton.setDisable(false);
        controller.pairButton.setDisable(false);
        controller.twoPairsButton.setDisable(false);
        controller.straightButton.setDisable(false);
        controller.threeOfAKindButton.setDisable(false);
        controller.fullHouseButton.setDisable(false);
        controller.fourOfAKindButton.setDisable(false);
        controller.pokerButton.setDisable(false);
    }

    public void lockButtons() {
        controller.checkButton.setDisable(true);
        controller.oneCardButton.setDisable(true);
        controller.pairButton.setDisable(true);
        controller.twoPairsButton.setDisable(true);
        controller.straightButton.setDisable(true);
        controller.threeOfAKindButton.setDisable(true);
        controller.fullHouseButton.setDisable(true);
        controller.fourOfAKindButton.setDisable(true);
        controller.pokerButton.setDisable(true);
    }
}
