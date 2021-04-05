package com.nikita.kut.android.simbirsoft_workshop;

import java.util.ArrayList;
import java.util.Arrays;

public enum Directions {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public void changeCoordinates(Integer[] location) {
        switch (this) {
            case UP:
                location[1] += 1;
                break;
            case DOWN:
                location[1] -= 1;
                break;
            case LEFT:
                location[0] -= 1;
                break;
            case RIGHT:
                location[0] += 1;
                break;
            default:
                break;
        }
    }

    public Integer[] fewSteps() {
        Integer[] location = {0, 0};
        ArrayList<Integer> fullPath = new ArrayList<>(Arrays.asList(location));
        Directions[] path = {Directions.UP, Directions.UP, Directions.LEFT,
                Directions.DOWN, Directions.LEFT, Directions.DOWN, Directions.DOWN,
                Directions.RIGHT, Directions.RIGHT, Directions.DOWN,
                Directions.RIGHT};
        for (Directions directions: path) {
            directions.changeCoordinates(location);
        }
        return location;
    }
}
