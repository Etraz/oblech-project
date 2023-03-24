package pl.lumido.oblechproject.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.Game;
import pl.lumido.oblechproject.gui.handCreation.BotPlayer;

import java.io.IOException;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class Admin extends Application {
    public static Game game = new Game();

    static int numberOfBots = 0, numberOfHumans = 0;

    @FXML
    VBox players;

    @Override
    public void start(Stage stage) {
        show(stage);
    }

    public static void main(String ... args){
        launch(args);
    }

    public void show(Stage stage){
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Admin.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root,600,400);
        stage.setMinHeight(4);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addHumanPlayer(ActionEvent event){
        HumanPlayer player = new HumanPlayer();
        player.askForName();
        if(player.getName() == null || !game.register(player)) return;
        numberOfHumans = numberOfHumans + 1;
//        System.out.println("addHumanPlayer");
        Label label = new Label(player.getName());
        label.setFont(new Font("Arial", 16));
        players.getChildren().add(label);
    }

    @FXML
    public void startGame(ActionEvent event) {
        if (numberOfHumans == 0) return;
        for (var player : game.players){
            player.show(new Stage());
        }
        new Thread(game::simulate).start();
        Stage stage = (Stage) players.getScene().getWindow();
        stage.close();
    }

    public void addBotPlayer(ActionEvent event) {
        BotPlayer player = new BotPlayer();
        player.setName("BOT" + numberOfBots);
        if (!game.register(player)) return;
        numberOfBots = numberOfBots + 1;
//        System.out.println("addBotPlayer");
        Label label = new Label(player.getName());
        label.setFont(new Font("Arial", 16));
        players.getChildren().add(label);
    }

    public void startDemo(ActionEvent ignoredEvent) {
        for (int i = 0; i < 3; i++) {
            BotPlayer player = new BotPlayer();
            player.setName("BOT" + numberOfBots);
            if (!game.register(player)) return;
            numberOfBots = numberOfBots + 1;
        }
        HumanPlayer humanPlayer = new HumanPlayer();
        humanPlayer.setName("Player");
        if(!game.register(humanPlayer)) return;
        for (var player : game.players){
            player.show(new Stage());
        }
        new Thread(game::simulate).start();
        Stage stage = (Stage) players.getScene().getWindow();
        stage.close();
    }
}
