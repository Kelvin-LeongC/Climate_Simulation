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

        Cloud cloud_1 = new Cloud(25*scale, 25*scale, cloud_altitude, 1);
        Cloud cloud_2 = new Cloud(10*scale, 10*scale, cloud_altitude, 1);
        Cloud cloud_3 = new Cloud(10*scale, 40*scale, cloud_altitude, 1);
        Cloud cloud_4 = new Cloud(40*scale, 10*scale, cloud_altitude, 1);
        Cloud cloud_5 = new Cloud(40*scale, 40*scale, cloud_altitude, 1);
        cloud_array.add(cloud_1);
        cloud_array.add(cloud_2);
        cloud_array.add(cloud_3);
        cloud_array.add(cloud_4);
        cloud_array.add(cloud_5);

    }

    public void draw(){
        background(64);
        noStroke();
        lights();

        translate(width/2, height/2);
        rotateX(PI/3);
        translate(-w/2, -h/2 - 200, -200);

        drawTerrain();

        for(int i = 0; i < cloud_array.size(); i++){
            drawCloud(cloud_array.get(i));
        }



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
        drawSphere(cloud.getX(), cloud.getY(), cloud.getZ(), 0, 0, 0, 8);
        drawSphere(cloud.getX(), cloud.getY(), cloud.getZ(), -5, 0, 0, 5);
        drawSphere(cloud.getX(), cloud.getY(), cloud.getZ(), +5, 0, 0, 5);
        drawSphere(cloud.getX(), cloud.getY(), cloud.getZ(), 0, -5, 0, 5);
        drawSphere(cloud.getX(), cloud.getY(), cloud.getZ(), 0, +5, 0, 5);
        drawSphere(cloud.getX(), cloud.getY(), cloud.getZ(), 0, 0, +5, 5);
    }

    private void drawSphere(int x, int y, int z, int x_dev, int y_dev, int z_dev, float size){
        pushMatrix();
        translate(x + x_dev, y + y_dev, z + z_dev);
        fill(255);
        sphere(size);
        popMatrix();
    }
}
