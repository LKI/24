package com.liriansu.android.twentyfour.card;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private Random random;

    public Deck() {
        deck = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (int r = 1; r <= 13; r++) {
                Card c = new Card(s, r);
                deck.add(c);
            }
        }
    }

    public Card drawCard() {
        if (getSize() == 0) {
            return null;
        }
        if (random == null) {
            random = new Random();
        }
        return deck.remove(random.nextInt(deck.size()));
    }

    public int getSize() {
        return deck.size();
    }
}
