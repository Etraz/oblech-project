package pl.lumido.oblechproject.engine.cards;


public record Card(int number, COLOR color) {
    /**
     * @throws IllegalArgumentException
     */
    public Card {
        if (color == null || number < 2 || number > 14) {
            throw new IllegalArgumentException("That card don't exists");
        }
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        if (number < 11) {
            toReturn.append(number);
        } else if (number == 11) {
            toReturn.append("Jack");
        } else if (number == 12) {
            toReturn.append("Queen");
        } else if (number == 13) {
            toReturn.append("King");
        } else {
            toReturn.append("Ace");
        }
        toReturn.append(" of ");
        toReturn.append(color);
        return toReturn.toString();
    }

}
