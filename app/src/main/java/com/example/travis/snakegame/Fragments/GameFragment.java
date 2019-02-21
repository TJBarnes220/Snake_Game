package com.example.travis.snakegame.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.travis.snakegame.GameComponents.Controller;
import com.example.travis.snakegame.GameComponents.Coordinates;
import com.example.travis.snakegame.GameComponents.Direction;
import com.example.travis.snakegame.R;
import com.example.travis.snakegame.GameComponents.Snake;
import com.example.travis.snakegame.GameComponents.Tile;
import com.example.travis.snakegame.GameComponents.TileType;

import java.util.Random;

import androidx.navigation.Navigation;

public class GameFragment extends Fragment {
    TableLayout layout;
    Tile[][] board;
    Snake snek;
    Tile pelletPosition;
    Controller gamePad;
    /*
     * Below is method used to run game and update UI constantly
     * mSampleDurationTime dictates how long intervals are
     * continueToRun dictates whether handler continues
     */
    int mSampleDurationTime = 100; // 1/10 of a sec
    boolean continueToRun = true;
    Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable(){
        public void run() {
            traverse();
            if(continueToRun == true){
                mHandler.postDelayed(mRunnable, mSampleDurationTime);
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_screen, container, false);
        layout = view.findViewById(R.id.tableLayout);
        board = new Tile[0][0];
        gamePad = new Controller((Button) view.findViewById(R.id.up_btn),(Button) view.findViewById(R.id.down_btn),
                (Button) view.findViewById(R.id.left_btn),(Button) view.findViewById(R.id.right_btn));
        //start();
        return view;
    }

    public void start(){
        //Makes game board
        makeBoard();
        //Creates player snake and assigns to controller
        snek = new Snake(board[0][0]);
        gamePad.setPlayer(snek);
        //Places pellet on game board
        placePellet();
        //Starts Handler loop to update UI
        continueToRun = true;
        mHandler.postDelayed(mRunnable, mSampleDurationTime);
    }

    public void makeBoard(){
        int rows = 10;
        int columns = 10;
        layout.removeAllViews();
        board = new Tile[rows][columns];

        //board = new TableLayout(game);
        final TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT,1);
        final TableRow.LayoutParams imgParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,1);
        for(int i = 0; i < rows; i++){
            TableRow row = new TableRow(layout.getContext());
            row.setLayoutParams(rowParams);
            for(int j = 0; j < columns; j++){

                final ImageView pic = new ImageView(layout.getContext());
                pic.setLayoutParams(imgParams);
                Tile tile = new Tile(pic, new Coordinates(j,i));
                pic.setTag(tile);
                board[j][i] = tile;
                row.addView(pic);
                Log.d("Board Fragment","i: " + i + " j: " + j);
            }
            Log.d("Board Fragment","added a row");
            layout.addView(row,rowParams);
        }
    }

    public void placePellet(){
        Random random = new Random();
        boolean acceptableSpot = false;
        while(acceptableSpot == false) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            if(board[x][y].getType() == TileType.Empty){
                acceptableSpot = true;
                pelletPosition = board[x][y];
                pelletPosition.setType(TileType.Pellet);
            }
        }

    }

    public void traverse(){
        Tile next;
        Coordinates nextLocation = new Coordinates(0,0);
        Direction velocity = snek.getVelocity();
        if(velocity == Direction.Down){
            nextLocation = snek.getHead().getLocation().getSouth();
            if(!inBounds(nextLocation)){
                nextLocation = new Coordinates(nextLocation.getX(),0);
            }
        }
        else if(velocity == Direction.Up){
            nextLocation = snek.getHead().getLocation().getNorth();
            if(!inBounds(nextLocation)){
                nextLocation = new Coordinates(nextLocation.getX(),board[0].length - 1);
            }
        }
        else if(velocity == Direction.Left){
            nextLocation = snek.getHead().getLocation().getWest();
            if(!inBounds(nextLocation)){
                nextLocation = new Coordinates(board.length - 1, nextLocation.getY());
            }
        }
        else if(velocity == Direction.Right){
            nextLocation = snek.getHead().getLocation().getEast();
            if(!inBounds(nextLocation)){
                nextLocation = new Coordinates(0, nextLocation.getY());
            }
        }
        next = board[nextLocation.getX()][nextLocation.getY()];
        int tag = snek.Move(next);
        if(tag == 2){

            placePellet();
        }
        //if 3 then gameover
    }



    public boolean inBounds(Coordinates location){
        if(location.getX() < 0 | location.getX() >= board.length){
            return false;
        }
        if(location.getY() < 0 | location.getY() >= board[0].length){
            return false;
        }
        return true;
    }

}
