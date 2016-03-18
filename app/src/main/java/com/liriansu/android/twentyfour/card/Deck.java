package com.liriansu.android.twentyfour.card;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private Random random;
    private Card currentCard;

    public Deck() {
        random = new Random();
        deck = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                Card c = new Card(s, r);
                deck.add(c);
            }
        }
        currentCard = null;
    }

    public Card drawCard() {
        if (getSize() == 0) {
            return null;
        }
        currentCard = deck.remove(random.nextInt(deck.size()));
        return currentCard;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public int getSize() {
        return deck.size();
    }
}
