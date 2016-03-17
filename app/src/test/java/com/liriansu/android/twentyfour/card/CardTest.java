package com.liriansu.android.twentyfour.card;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    private Card spadesA, heartsQ, clubsEight, diamondThree;

    @Before
    public void setUp() throws Exception {
        spadesA = new Card(Suit.SPADES, 1);
        heartsQ = new Card(Suit.HEARTS, 12);
        clubsEight = new Card(Suit.CLUBS, 8);
        diamondThree = new Card(Suit.DIAMONDS, 3);
    }

    @Test
    public void testGetSuit() throws Exception {
        assertEquals(Suit.SPADES, spadesA.getSuit());
        assertEquals(Suit.HEARTS, heartsQ.getSuit());
        assertEquals(Suit.CLUBS, clubsEight.getSuit());
        assertEquals(Suit.DIAMONDS, diamondThree.getSuit());
    }

    @Test
    public void testGetRank() throws Exception {
        assertEquals(1, spadesA.getRank());
        assertEquals(12, heartsQ.getRank());
        assertEquals(8, clubsEight.getRank());
        assertEquals(3, diamondThree.getRank());
    }

    @Test
    public void testGetPoint() throws Exception {
        assertEquals(1, spadesA.getPoint());
        assertEquals(10, heartsQ.getPoint());
        assertEquals(8, clubsEight.getPoint());
        assertEquals(3, diamondThree.getPoint());
    }
}