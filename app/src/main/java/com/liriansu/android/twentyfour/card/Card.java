package com.liriansu.android.twentyfour.card;

public class Card {
    private Suit suit;
    private Rank rank;

    public Card(Suit s, Rank r) {
        this.suit = s;
        this.rank = r;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public int getPoint() {
        return this.rank.getPoint();
    }

    public String getSymbol() {
        return suit.getSymbol() + rank.getSymbol();
    }
}
