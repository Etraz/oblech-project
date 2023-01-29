package pl.lumido.oblechproject.engine.cards.hands;

import pl.lumido.oblechproject.engine.cards.CardSet;
import pl.lumido.oblechproject.gui.Admin;

public class EmptyHand implements Hand {
    @Override
    public boolean isItInReverse(CardSet cardSet) {return false;}
    @Override
    public boolean isSmaller(Hand hand) {return true;}
    @Override
    public void check() {}

    @Override
    public Hand nextHand() {
        return new OneCard(Admin.game.getLowestCard());
    }
}
