package pl.lumido.oblechproject.gui;

import javafx.concurrent.Task;
import pl.lumido.oblechproject.engine.cards.hands.Hand;

public class HandQuestion extends Task<Hand> {
    public Hand hand;
    HumanPlayer player;

    public HandQuestion(Hand hand, HumanPlayer player){
        this.hand = hand;
        this.player = player;
    }

    @Override
    protected Hand call() throws Exception {
        return player.play(hand);
    }

    public Hand perform(){
        Thread t = new Thread(this);
        t.start();
        return this.getValue();
    }
}
