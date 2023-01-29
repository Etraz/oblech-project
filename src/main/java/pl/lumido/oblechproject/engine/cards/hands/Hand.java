package pl.lumido.oblechproject.engine.cards.hands;
//name isn't best but for now is enough

import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.gui.handCreation.PairController;

//abstract class represents one of Oblech hands
public interface Hand {

    //Checks if in reverse of given CardSet is hand that is represented by given instance of any class that extends this
    boolean isItInReverse(CardSet cardSet);

    //returns true when this is smaller(less valuable) then hand
    boolean isSmaller(Hand hand);

    void check();

    default int getHandIndex() {
        return -1;
    }

    Hand nextHand();
}
