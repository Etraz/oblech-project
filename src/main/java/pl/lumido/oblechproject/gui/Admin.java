package pl.lumido.oblechproject.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.Game;

import java.io.IOException;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class Admin extends Application {
    Game game = new Game();

    @FXML
    VBox players;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinHeight(425);
        stage.setMinWidth(600);
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
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addPlayer(ActionEvent event){
        Player player = new Player();
        player.askForName();
        if(player.getName() == null || !game.register(player)) return;
        System.out.println("addPlayer");
        Label label = new Label(player.getName());
        label.setFont(new Font("Arial", 16));
        players.getChildren().add(label);
    }

    @FXML
    public void startGame(ActionEvent event) throws InterruptedException {
        for (var player : game.players){
            player.show(new Stage());
        }
        new Thread(game::simulate).start();
        Stage stage = (Stage) players.getScene().getWindow();
        stage.close();
    }

    public void openSettings(ActionEvent event) {
        for (int i = 1; i <= 4; i++) {
            Player player = new Player();
            player.setName("Player " + i);
            if(!game.register(player)) return;
        }
        for (var player : game.players){
            player.show(new Stage());
        }
        new Thread(game::simulate).start();
        Stage stage = (Stage) players.getScene().getWindow();
        stage.close();
    }

    public void openAboutProject(ActionEvent event) {
    }
}
