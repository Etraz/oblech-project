package pl.lumido.oblechproject.engine.cards;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class CardSet {
    private ArrayList<HashSet<Card>> arrayList;

    public CardSet(){
        arrayList = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            arrayList.add(new HashSet<>());
        }
    }

    public void clear(){
        for(HashSet<Card> set : arrayList){
            set.clear();
        }
    }

    public void addCard(Card card){
        arrayList.get(card.number()-2).add(card);
    }


    public HashSet<Card> getCardsOfGivenNumber(int cardNumber){
        return arrayList.get(cardNumber-2);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(var x: arrayList){
            if(!x.isEmpty()) stringBuilder.append(x.toString());
        }
        return stringBuilder.toString();
    }

    public void showInHBox(HBox hbox){
        for(var x: arrayList){
            for(Card card : x){
                String imageLocation = card.toString().toLowerCase(Locale.ROOT).replace(" ","_") + ".png";
                //ImageView imageView = new ImageView(new Image("C:\\Users\\Owner\\Documents\\Projekty\\Java\\oblech-project\\src\\main\\resources\\pl\\lumido\\oblechproject\\engine\\cards\\cardImages\\" + imageLocation));
                ImageView imageView = new ImageView(new Image("/" + imageLocation));
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                hbox.getChildren().add(imageView);
            }
        }
    }
}
