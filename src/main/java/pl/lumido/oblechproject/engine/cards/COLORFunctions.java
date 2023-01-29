package pl.lumido.oblechproject.engine.cards;

import java.util.HashSet;

public class COLORFunctions {
    public static boolean isSmaller(COLOR first, COLOR second){
        if(first == second){
            return true;
        }
        if(first == COLOR.SPADES || second == COLOR.CLUBS){
            return false;
        }
        if(first == COLOR.CLUBS || second == COLOR.SPADES){
            return true;
        }
        return first == COLOR.DIAMONDS;
    }
    public static boolean doseReversSetContains(HashSet<Card> hashSet, COLOR color){
        for(Card x : hashSet){
            if(x.color() == color){
                return false;
            }
        }
        return true;
    }

    public static COLOR next(COLOR color){
        switch (color){
            case CLUBS -> {
                return COLOR.DIAMONDS;
            }
            case DIAMONDS -> {
                return COLOR.HEARTS;
            }
            case HEARTS -> {
                return COLOR.SPADES;
            }
            default -> {
                return null;
            }
        }
    }

}
