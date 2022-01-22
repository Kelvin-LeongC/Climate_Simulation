package entities;

import processing.core.PApplet;

public class SurfaceWater extends PApplet {
    private float[][] surfaceWater;

    public SurfaceWater(float[][] terrain){
        int cols = terrain.length;
        int rows = terrain[0].length;
        surfaceWater = new float[cols][rows];

        for(int x = 0; x < cols; x++){
            for(int y = 0; y < rows; y++){
                surfaceWater[x][y] = 256 - terrain[x][y];
            }
        }
    }

    public boolean evaporated(int x, int y){
        if(random(0,1) < surfaceWater[x][y]/1000){
            return true;
        }
        return false;
    }
}
