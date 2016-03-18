package com.liriansu.android.twentyfour.card;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    private Card spadesA, heartsQ, clubsEight, diamondThree;

    @Before
    public void setUp() throws Exception {
        spadesA = new Card(Suit.SPADES, Rank.ACE);
        heartsQ = new Card(Suit.HEARTS, Rank.QUEEN);
        clubsEight = new Card(Suit.CLUBS, Rank.EIGHT);
        diamondThree = new Card(Suit.DIAMONDS, Rank.THREE);
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
        assertEquals(Rank.ACE, spadesA.getRank());
        assertEquals(Rank.QUEEN, heartsQ.getRank());
        assertEquals(Rank.EIGHT, clubsEight.getRank());
        assertEquals(Rank.THREE, diamondThree.getRank());
    }

    @Test
    public void testGetPoint() throws Exception {
        assertEquals(1, spadesA.getPoint());
        assertEquals(10, heartsQ.getPoint());
        assertEquals(8, clubsEight.getPoint());
        assertEquals(3, diamondThree.getPoint());
    }

    @Test
    public void testGetSymbol() throws  Exception {
        assertEquals("♠A", spadesA.getSymbol());
        assertEquals("♥Q", heartsQ.getSymbol());
        assertEquals("♣8", clubsEight.getSymbol());
        assertEquals("♦3", diamondThree.getSymbol());
    }
}