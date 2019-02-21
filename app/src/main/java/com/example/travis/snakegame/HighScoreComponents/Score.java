package com.example.travis.snakegame.HighScoreComponents;

public class Score {
    int value;
    String owner;

    public Score(int value, String owner){
        this.value = value;
        this.owner = owner;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
