package entities;

import processing.core.PApplet;

public class Terrain extends PApplet {
    private float[][] terrain;

    public Terrain(int cols, int rows){
        this.terrain = new float[cols][rows];

        // Generate a 2D elevation map using Perlin noises
        float x_off = 0;
        for(int x = 0; x < cols; x++){
            float y_off = 0;
            for(int y = 0; y < rows; y++){
                terrain[x][y] = map(noise(x_off, y_off), 0, 1, 0, 256);
                y_off += 0.07;
            }
            x_off += 0.07;
        }
    }

    public float[][] getTerrain(){
        return this.terrain;
    }
}
