package com.example.travis.snakegame.GameComponents;

import java.util.ArrayList;

public class PathQueue {
    ArrayList<Tile> pathway;
    Tile head;
    Tile tail;

    public PathQueue(){
        pathway = new ArrayList<>();
        head = null;
        tail = null;
    }

    public void push(Tile addition){
        pathway.add(addition);
        if(pathway.size() == 1){
            tail = pathway.get(0);
        }
        head = pathway.get(pathway.size() - 1);
    }

    public Tile pop(){
        Tile popped = pathway.remove(0);
        if(pathway.size() == 0){
            tail = null;
            head = null;

        }
        else {
            tail = pathway.get(0);
        }
        return popped;
    }

    public Tile getHead() {
        return head;
    }

    public void setHead(Tile head) {
        this.head = head;
    }

    public Tile getTail() {
        return tail;
    }

    public void setTail(Tile tail) {
        this.tail = tail;
    }
}
