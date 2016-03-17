package com.liriansu.android.twentyfour.card;

public class Card {
    private Suit suit;
    private int rank;

    public Card(Suit s, int r) {
        this.suit = s;
        this.rank = r;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public int getRank() {
        return this.rank;
    }

    public int getPoint() {
        if (this.rank > 10) {
            return 10;
        } else {
            return this.rank;
        }
    }
}
