package pl.lumido.oblechproject.engine.cards.hands;

import pl.lumido.oblechproject.engine.cards.CardSet;

public class EmptyHand implements Hand {
    @Override
    public boolean isItInReverse(CardSet cardSet) {return false;}
    @Override
    public boolean isSmaller(Hand hand) {return true;}
    @Override
    public void check() {}
}
