package com.example.travis.snakegame.GameComponents;

import android.view.View;
import android.widget.Button;

public class Controller {
    Button Up;
    Button Down;
    Button  Left;
    Button  Right;
    Snake player;
    boolean delay;

    public Controller(Button  Up, final Button  Down, Button Left, Button  Right){
        delay = false;
        this.Up = Up;
        this.Up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!delay) {
                    if (player.getVelocity() == Direction.Down & player.getSize() != 1) {
                        return;
                    }
                    player.setVelocity(Direction.Up);
                    delay = true;
                }
            }
        });

        this.Down = Down;
        this.Down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!delay) {
                    if (player.getVelocity() == Direction.Up & player.getSize() != 1) {
                        return;
                    }
                    player.setVelocity(Direction.Down);
                    delay = true;
                }
            }
        });

        this.Left = Left;
        this.Left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!delay) {
                    if (player.getVelocity() == Direction.Right & player.getSize() != 1) {
                        return;
                    }
                    player.setVelocity(Direction.Left);
                    delay = true;
                }
            }
        });

        this.Right = Right;
        this.Right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!delay) {
                    if (player.getVelocity() == Direction.Left & player.getSize() != 1) {
                        return;
                    }
                    player.setVelocity(Direction.Right);
                    delay = true;
                }
            }
        });
    }

    public void setPlayer(Snake player) {
        this.player = player;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }
}
