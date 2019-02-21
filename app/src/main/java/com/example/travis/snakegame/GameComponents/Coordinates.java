package com.example.travis.snakegame.GameComponents;


import java.util.ArrayList;

/**
 * This class is used to signify the location of entities on the
 * game board.
 */
public class Coordinates {
    int x;
    int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Comparison method
     *
     * @param other Coordinate to be compared
     * @return true/false depending if coordinates match
     */
    public boolean equals(Coordinates other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Prints out Coordinate to System typically
     * used in testing
     */
    public void printLocation() {
        System.out.println("Coordinates: x = " + this.x + ", y = " + this.y);
    }

    public String toString() {

        return "(" + this.x + "," + this.y + ")";
    }

    /**
     * The following are used to return coordinates
     * surrounding the current location. North/South/East/West
     *
     * @return
     */
    public Coordinates getNorth() {
        return new Coordinates(x, y - 1);
    }

    public Coordinates getSouth() {
        return new Coordinates(x, y + 1);
    }

    public Coordinates getWest() {
        return new Coordinates(x - 1, y);
    }

    public Coordinates getEast() {
        return new Coordinates(x + 1, y);
    }

    public ArrayList<Coordinates> allAround() {
        ArrayList<Coordinates> locations = new ArrayList<Coordinates>();
        locations.add(getNorth());
        locations.add(getEast());
        locations.add(getSouth());
        locations.add(getWest());
        return locations;
    }

    public ArrayList<Coordinates> allAround(int distance) {
        ArrayList<Coordinates> locations = new ArrayList<Coordinates>();
        for (int i = 0; i < 4; i++) {
            Coordinates current = this;
            for (int j = 0; j < distance; j++) {
                switch (i) {
                    case (0):
                        current = current.getNorth();
                        locations.add(current);
                        break;
                    case (1):
                        current = current.getSouth();
                        locations.add(current);
                        break;
                    case (2):
                        current = current.getWest();
                        locations.add(current);
                        break;
                    case (3):
                        current = current.getEast();
                        locations.add(current);
                        break;
                }
            }
        }
        return locations;
    }
}