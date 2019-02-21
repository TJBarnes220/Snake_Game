package com.example.travis.snakegame.GameComponents;

import android.util.Log;
import android.widget.ImageView;

import com.example.travis.snakegame.R;

public class Tile {
    ImageView image;
    Coordinates location;
    TileType type;

    public Tile(ImageView image, Coordinates location){
        this.image = image;
        this.location = location;
        setType(TileType.Empty);
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        //Change Image
        if(type == TileType.Empty){
            image.setImageResource(R.drawable.empty_tile);
            Log.d("TileType","Tile at " + location.toString() + " has been set to empty");
        }
        else if(type == TileType.Snake){
            image.setImageResource(R.drawable.snake_tile);
            Log.d("TileType","Tile at " + location.toString() + " has been set to snake");
        }
        else if(type == TileType.Pellet){
            image.setImageResource(R.drawable.pellet_tile);
            Log.d("TileType","Tile at " + location.toString() + " has been set to pellet");
        }
        this.type = type;
    }
}
