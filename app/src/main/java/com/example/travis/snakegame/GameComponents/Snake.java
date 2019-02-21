package com.example.travis.snakegame.GameComponents;

import android.util.Log;

public class Snake {
    private int size;
    private Direction velocity;
    private PathQueue path;

    public Snake(Tile staringPoint){
        size = 1;
        velocity = Direction.Down;
        path = new PathQueue();
        push(staringPoint);
    }

    public Tile pop(){
        Tile tailEnd = path.pop();
        tailEnd.setType(TileType.Empty);
        return tailEnd;
    }

    public void push(Tile nHead){
        path.push(nHead);
        Log.d("SnakePush" ,"Pushed tile at \" + nHead.location.toString() + \" to Head");
        nHead.setType(TileType.Snake);
    }

    public void increaseSize(){
        size += 1;
    }

    public void DecreaseSize(){
        size -= 1;
    }

    //Make a new result type
    public int Move(Tile next){
        if(next.getType() == TileType.Empty){
            pop();
            push(next);
        }
        else if(next.getType() == TileType.Pellet){
            push(next);
            increaseSize();
            return 2;
        }
        else if (next.getType() == TileType.Snake) {
            return 3;
        }
        return 1;
    }









    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Tile getHead() {
        return path.getHead();
    }

    public Direction getVelocity() {
        return velocity;
    }

    public void setVelocity(Direction velocity) {
        this.velocity = velocity;
    }
}

