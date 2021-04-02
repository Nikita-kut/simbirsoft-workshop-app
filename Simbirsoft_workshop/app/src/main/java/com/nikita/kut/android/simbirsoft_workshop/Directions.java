package com.nikita.kut.android.simbirsoft_workshop;

import java.util.ArrayList;
import java.util.Arrays;

public enum Directions {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public int[] changeCoordinates(int x, int y, Directions p) {
        switch (p) {
            case UP:
                y += 1;
                break;
            case DOWN:
                y -= 1;
                break;
            case LEFT:
                x -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
            default:
                break;
        }

        return new int[]{x, y};
    }

    public int[] fewSteps() {
        Integer[] location = {0, 0};
        ArrayList<Integer> fullPath = new ArrayList<>(Arrays.asList(location));
        Directions[] path = {Directions.UP, Directions.UP, Directions.LEFT,
                Directions.DOWN, Directions.LEFT, Directions.DOWN, Directions.DOWN,
                Directions.RIGHT, Directions.RIGHT, Directions.DOWN,
                Directions.RIGHT};
        for (int i = 0; i < path.length; i++) {
            switch (path[i]) {
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
            fullPath.add(location[0]);
            fullPath.add(location[1]);
        }
        int[] finalPathArray = new int[fullPath.size()];
        for (int i = 0; i < fullPath.size(); i++) {
            finalPathArray[i] = fullPath.get(i);
        }
        return finalPathArray;
    }
}
