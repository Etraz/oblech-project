package pl.lumido.oblechproject.engine.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> list = new ArrayList<>();
    private List<Card> underLowest = new ArrayList<>();
    private final int lowest;

    public Deck(){
        this(2);
    }

    public Deck(int lowest) throws IllegalArgumentException{
        if(lowest < 2 || lowest > 14){
            throw new IllegalArgumentException("Lowest value is wrong");
        }
        this.lowest = lowest;
        for (int i = 2; i <= lowest; i++) {
            for(COLOR color : COLOR.values()) {
                underLowest.add(new Card(i, color));
            }
        }
        for (int i = lowest; i <= 14; i++) {
            for(COLOR color : COLOR.values()) {
                list.add(new Card(i, color));
            }
        }
    }


    private void shuffle(){
        Collections.shuffle(this.list);
    }

    public CardSet deal(List<Integer> NumbersOfWantedCards, List<CardSet> CardsSets) throws IllegalArgumentException{
        shuffle();
        CardSet toReturn =  new CardSet();
        if(NumbersOfWantedCards == null || CardsSets == null || NumbersOfWantedCards.size() != CardsSets.size()){
            throw new IllegalArgumentException("Wrong input to deal function");
        }
        int sumOfWantedCards = 0;
        for(int x : NumbersOfWantedCards){
            if(x <= 0){
                throw new IllegalArgumentException("Wrong input to deal function. One of wanted cards numbers is non positive");
            }
            sumOfWantedCards += x;

        }
        if(sumOfWantedCards > (15-lowest)*4){
            throw new IllegalArgumentException("Wrong input to deal function. Too many wanted cards");
        }
        int index = 0;
        for(Card card : list){
            if (index >= NumbersOfWantedCards.size()){
                toReturn.addCard(card);
            }
            else{
                if(NumbersOfWantedCards.get(index) == 0){
                    index++;
                    if (index >= NumbersOfWantedCards.size()){
                        toReturn.addCard(card);
                    }
                    else{
                        CardsSets.get(index).addCard(card);
                        NumbersOfWantedCards.set(index,NumbersOfWantedCards.get(index) - 1);
                    }
                }
                else {
                    CardsSets.get(index).addCard(card);
                    NumbersOfWantedCards.set(index,NumbersOfWantedCards.get(index) - 1);
                }
            }
        }
        for (Card card :underLowest){
            toReturn.addCard(card);
        }
        return toReturn;
    }

}
