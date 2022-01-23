package entities;

import processing.core.PApplet;

public class DustMap extends PApplet {
    private float[][] dustMap;

    public DustMap(int cols, int rows){
        this.dustMap = new float[cols][rows];

        //Generate another 2D map for dust distribution using Perlin noises
        float x_off = 0;
        for(int x = 0; x < cols; x++){
            float y_off = 0;
            for(int y = 0; y < rows; y++){
                dustMap[x][y] = map(noise(x_off, y_off), 0, 1, 0, 300);
                y_off += 0.2;
            }
            x_off += 0.2;
        }
    }

    // A function that gets the Dust maps
    public float[][] getDustMap(){
        return dustMap;
    }
}
