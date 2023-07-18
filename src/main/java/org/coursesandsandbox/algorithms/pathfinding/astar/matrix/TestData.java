package org.coursesandsandbox.algorithms.pathfinding.astar.matrix;

import java.util.Random;

public class TestData {

    public static int[][] getRandomMatrix(int xSize, int ySize) {
        int[][] matrix = new int[xSize][ySize];
        for(int x = 0; x<xSize; x++) {
            for(int y=0; y<ySize; y++) {
                matrix[x][y] = getRandomValue(-1, 2);
            }
        }
        return matrix;
    }

    private static int getRandomValue(int min, int max) {
        return new Random().nextInt(max-min) + min;
    }
}
