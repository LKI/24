package com.liriansu.android.twentyfour.card;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DeckTest {
    private Deck deck;

    @Before
    public void setUp() throws Exception {
        deck = new Deck();
    }

    @Test
    public void testDrawCard() throws Exception {
        int sum = 0;
        for (int i = 0; i < 52; i++) {
            sum += deck.drawCard().getPoint();
        }
        assertEquals(340, sum);
        assertNull(deck.drawCard());
    }

    @Test
    public void testGetSize() throws Exception {
        assertEquals((new Deck()).getSize(), 52);
    }

    @Test
    public void testGetCurrentCard() throws Exception {
        deck = new Deck();
        assertEquals(deck.drawCard(), deck.getCurrentCard());
    }
}