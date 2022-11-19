package pl.lumido.oblechproject.engine;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.engine.cards.Deck;
import pl.lumido.oblechproject.engine.cards.hands.EmptyHand;
import pl.lumido.oblechproject.engine.cards.hands.Hand;
import pl.lumido.oblechproject.gui.Admin;
import pl.lumido.oblechproject.gui.HandQuestion;
import pl.lumido.oblechproject.gui.Player;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.*;

public class Game {
    public ArrayList<Player> players = new ArrayList<>();
    private int maxNumberOfPlayers, lowestCard, numberOfCardsToGoBust;

    public Game() {
        maxNumberOfPlayers = 4;
        lowestCard = 8;
        numberOfCardsToGoBust = 6;
    }

    public boolean register(Player player) {
        if (player == null || player.isRegister()) {
            throw new IllegalArgumentException("Player is null or he is already register");
        }
        if (players.size() >= maxNumberOfPlayers) {
            return false;
        }
        players.add(player);
        player.setRegister(true);
        return true;
    }

    public Game(int maxNumberOfPlayers, int lowestCard, int numberOfCardsToGoBust) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.lowestCard = lowestCard;
        this.numberOfCardsToGoBust = numberOfCardsToGoBust;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public void setMaxNumberOfPlayers(int maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
    }

    public int getLowestCard() {
        return lowestCard;
    }

    public void setLowestCard(int lowestCard) {
        this.lowestCard = lowestCard;
    }

    public int getNumberOfCardsToGoBust() {
        return numberOfCardsToGoBust;
    }

    public void setNumberOfCardsToGoBust(int numberOfCardsToGoBust) {
        this.numberOfCardsToGoBust = numberOfCardsToGoBust;
    }

    boolean removePlayer(Player player) {
        if (!players.contains(player)) {
            return false;
        }
        player.setRegister(false);
        players.remove(player);
        return true;
    }

    void clearPlayersHands() {
        for (Player player : players) {
            player.getCardSet().clear();
        }
    }

    Pair<ArrayList<Integer>, ArrayList<CardSet>> createPlayersCardsSets(LinkedList<Player> currentPlayers) {
        Pair<ArrayList<Integer>, ArrayList<CardSet>> toReturn = new Pair<>(new ArrayList<>(), new ArrayList<>());
        for (Player p : currentPlayers) {
            if (p.isPlaying()) {
                toReturn.getKey().add(p.getNumberOfCardsWanted());
                toReturn.getValue().add(p.getCardSet());
            }
        }
        return toReturn;
    }

    //TODO restart after end
    public void simulate() {
        Deck deck = new Deck(lowestCard);
        clearPlayersHands();
        LinkedList<Player> currentPlayers = new LinkedList<>();
        for (Player p : players) {
            p.setPlaying(true);
            p.setNumberOfCardsWanted(1);
            currentPlayers.add(p);
        }
        while (currentPlayers.size() > 1) {
            for(Player p : players) p.clearCardSet();
            Pair<ArrayList<Integer>, ArrayList<CardSet>> playersCardsSets = createPlayersCardsSets(currentPlayers);
            CardSet rest = deck.deal(playersCardsSets.getKey(), playersCardsSets.getValue());
            //TODO 5. ALSO Shows how many cards where dealt how many are in deck and how many to bust
            for (Player p : currentPlayers) {
                p.dealt();
                for (Player q : players) {
                    q.addOtherPlayers(p.getName() + ":    " + p.getNumberOfCardsWanted() + " cards");
                }
            }
            boolean stillPlay = true;
            Player activePlayer, lastPlayer = null;
            Hand currentHand = new EmptyHand();
            while (stillPlay) {
                activePlayer = currentPlayers.removeFirst();
                Hand newHand = activePlayer.play(currentHand);
                if (newHand == null) {
                    for (Player p :players){
                        p.addToHistory(activePlayer.getName() + ":    checked");
                    }
                    lastPlayer = activePlayer;
                    stillPlay = false;
                } else {
                    for (Player p : players){
                        p.addToHistory(activePlayer.getName() + ":    " + newHand.toString());
                    }
                    currentHand = newHand;
                    currentPlayers.add(activePlayer);
                }
            }
            if (currentHand.isItInReverse(rest)) {
                Player prevPlayer = currentPlayers.removeLast();
                prevPlayer.setNumberOfCardsWanted(prevPlayer.getNumberOfCardsWanted() + 1);
                if (prevPlayer.getNumberOfCardsWanted() == numberOfCardsToGoBust) {
                    prevPlayer.setPlaying(false);
                } else {
                    currentPlayers.add(prevPlayer);
                }
                currentPlayers.add(lastPlayer);
            } else {
                lastPlayer.setNumberOfCardsWanted(lastPlayer.getNumberOfCardsWanted() + 1);
                if (lastPlayer.getNumberOfCardsWanted() == numberOfCardsToGoBust) {
                    lastPlayer.setPlaying(false);
                } else {
                    currentPlayers.add(lastPlayer);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Player player : players) {
            player.addToHistory(currentPlayers.get(0).getName() + ":    won");
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Player player : players){
            player.close();
        }
        Platform.runLater(() -> {
            Admin admin = new Admin();
            admin.show(new Stage());
        });
    }

}
