package com.liriansu.android.twentyfour.card;

public enum Rank {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String symbol;
    private final int point;

    Rank(String symbol) {
        this.symbol = symbol;
        switch (symbol) {
            case "A":
                this.point = 1;
                break;
            case "2":
                this.point = 2;
                break;
            case "3":
                this.point = 3;
                break;
            case "4":
                this.point = 4;
                break;
            case "5":
                this.point = 5;
                break;
            case "6":
                this.point = 6;
                break;
            case "7":
                this.point = 7;
                break;
            case "8":
                this.point = 8;
                break;
            case "9":
                this.point = 9;
                break;
            default:
                this.point = 10;
                break;
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPoint() {
        return point;
    }
}
