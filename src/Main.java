import entities.*;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

public class Main extends PApplet {
    private int cols, rows;
    private final int scale = 20;
    private final int w = 1000;
    private final int h = 1000;

    private final int cloud_altitude = 400;

    private float[][] terrain;
    private ArrayList<Cloud> cloud_array;
    //private float[][] color;

    public static void main(String[] args) {
        PApplet.main("Main",args);
    }

    public void settings() {
        size(1200, 800, PConstants.P3D);
        cols = w/scale;
        rows = h/scale;

        this.terrain = new Terrain(cols, rows).getTerrain();

        cloud_array = new ArrayList<>();

        int x_rand = 100;
        int y_rand = 100;
        Cloud cloud_1 = new Cloud(x_rand*scale, y_rand*scale, cloud_altitude, 10);
        cloud_array.add(cloud_1);

    }

    public void draw(){
        background(64);
        noStroke();
        lights();

        translate(width/2, height/2);
        rotateX(PI/3);
        translate(-w/2, -h/2 - 200, -200);

        drawTerrain();

        pushMatrix();
        translate(500,1000, 400);
        fill(255);
        sphere(10);

        popMatrix();


    }

    private void drawTerrain(){
        for(int y = 0; y < rows - 1; y++){
            for(int x = 0; x < cols - 1; x++){
                beginShape(TRIANGLE_STRIP);
                vertex(x*scale, y*scale, terrain[x][y]);
                vertex(x*scale, (y+1)*scale, terrain[x][y+1]);
                vertex((x+1)*scale, y*scale, terrain[x+1][y]);
                fill(0, 150, 0);
                endShape();

                beginShape(TRIANGLE_STRIP);
                vertex(x*scale, (y+1)*scale, terrain[x][y+1]);
                vertex((x+1)*scale, y*scale, terrain[x+1][y]);
                vertex((x+1)*scale, (y+1)*scale, terrain[x+1][y+1]);
                fill(0, 150, 0);
                endShape();
            }
        }
    }

    private void drawCloud(Cloud cloud){
        pushMatrix();

    }
}
