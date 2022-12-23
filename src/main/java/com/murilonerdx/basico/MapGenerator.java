package com.murilonerdx.basico;

import java.util.Arrays;
import java.util.Random;

public class MapGenerator {
    private static final int MAP_WIDTH = 64;
    private static final int MAP_HEIGHT = 64;
    private static final int NUM_TREES = 128;
    private static final int NUM_ROCKS = 64;
    private static final int NUM_ENEMIES = 32;

    private static final int[][] map = new int[MAP_WIDTH][MAP_HEIGHT];
    private static final Random rand = new Random();

    public static void generateMap() {
        // Gera o terreno
        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int j = 0; j < MAP_HEIGHT; j++) {
                map[i][j] = rand.nextInt(2);
            }
        }

        // Gera árvores
        for (int i = 0; i < NUM_TREES; i++) {
            int x = rand.nextInt(MAP_WIDTH);
            int y = rand.nextInt(MAP_HEIGHT);
            map[x][y] = 2;
        }

        // Gera rochas
        for (int i = 0; i < NUM_ROCKS; i++) {
            int x = rand.nextInt(MAP_WIDTH);
            int y = rand.nextInt(MAP_HEIGHT);
            map[x][y] = 3;
        }

        // Gera inimigos
        for (int i = 0; i < NUM_ENEMIES; i++) {
            int x = rand.nextInt(MAP_WIDTH);
            int y = rand.nextInt(MAP_HEIGHT);
            map[x][y] = 4;
        }
    }

    public static int[][] getMap() {
        return map;
    }

    public static void main(String[] args){
        generateMap();
        System.out.println(Arrays.deepToString(getMap()));
    }
}


