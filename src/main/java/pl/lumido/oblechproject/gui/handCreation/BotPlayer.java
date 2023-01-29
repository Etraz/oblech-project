package pl.lumido.oblechproject.gui.handCreation;

import pl.lumido.oblechproject.engine.cards.hands.Hand;
import pl.lumido.oblechproject.gui.Player;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BotPlayer extends Player {
    static Random r = new Random();
    @Override
    public Hand play(Hand currentHand) {
        Hand nextHand = null;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double d = r.nextDouble();
        if(d > 0.1){
            nextHand = currentHand.nextHand();
        }
        return nextHand;
    }

}
