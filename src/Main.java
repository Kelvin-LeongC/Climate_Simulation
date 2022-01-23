import entities.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.ArrayList;

public class Main extends PApplet {
    private int cols, rows;
    private final int scale = 20;
    private final int w = 1000;
    private final int h = 1000;

    private final int cloud_altitude = 400;

    private float[][] terrain;
    private SurfaceWater surfaceWater;
    private ArrayList<Cloud> cloud_array;

    private int[][] waterEvaporated;
    private float[][] dustMap;

    private boolean raining = false;

    private ArrayList<RainDroplet> rainDropletsArray;


    // The function that runs the Processing Applet in library
    public static void main(String[] args) {
        PApplet.main("Main",args);
    }

    // Setup of the window/applet before running
    public void settings() {
        size(1200, 800, PConstants.P3D);
        cols = w/scale;
        rows = h/scale;

        this.terrain = new Terrain(cols, rows).getTerrain();
        this.surfaceWater = new SurfaceWater(terrain);
        this.dustMap = new DustMap(cols, rows).getDustMap();

        // Initialize the grid for evaporation counts
        // and the grid for confirming if a cloud already exist
        this.waterEvaporated = new int[cols][rows];
        for(int x = 0; x < cols; x++){
            for(int y = 0; y < rows; y++){
                waterEvaporated[x][y] = 0;
            }
        }

        cloud_array = new ArrayList<>();
        rainDropletsArray = new ArrayList<>();
    }

    // The implicit for-loop/run function to animate on the new window
    public void draw(){
        // Window settings
        background(64);
        noStroke();
        lights();

        // Camera settings
        translate(width/2, height/2);
        rotateX(PI/3);
        translate(-w/2, -h/2 - 200, -200);

        // Visualizing the terrain
        drawTerrain();

        // If it is currently not raining
        if(!raining) {
            // Determine if surface water would evaporate
            updateEvaporation();

            // Determines if cloud at a location will form
            for (int x = 0; x < cols; x++) {
                for (int y = 0; y < cols; y++) {
                    if (waterEvaporated[x][y] > 100) {
                        cloud_array.add(new Cloud(x, y, cloud_altitude));
                        waterEvaporated[x][y] = -1;
                    }
                }
            }

            // Critical condition to determine if it turns raining
            float grid_total = cols * rows;
            if(cloud_array.size()/grid_total > 0.04){
                raining = true;

                // Initialize all the droplets here
                for(Cloud cloud: cloud_array){
                    rainDropletsArray.add(new RainDroplet(cloud, (int)terrain[(int)cloud.getPos().x][(int)cloud.getPos().y]));
                }
            }
        }

        // Draw all the clouds
        for(Cloud cloud: cloud_array) {
            drawCloud(cloud);
        }

        // If the condition determines it is raining
        if(raining) {
            // Draw the rain droplets
            for (RainDroplet rainDroplet: rainDropletsArray) {
                drawRainDroplet(rainDroplet);

                // Remove droplets if its cloud disappear
                if(!rainDropletsArray.contains(rainDroplet)){
                    rainDropletsArray.remove(rainDroplet);
                }
            }

            // Deduct the amount of water stored in clouds
            for(Cloud cloud: cloud_array) {
                cloud.deductWaterStored();
            }

            // If a cloud has no more water, remove the cloud
            if (cloud_array.get(0).getWaterStored() <= 0) {
                cloud_array.remove(0);
            }

            if(cloud_array.size() == 0){
                resetGridPoints();
                raining = false;
            }

        }
    }

    // A function that draws the terrain/elevation map
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

    // A function that uses the probability functions to determine if water has
    // been evaporated
    private void updateEvaporation(){
        for(int x = 0; x < cols; x++){
            for(int y = 0; y < rows; y++){
                if(waterEvaporated[x][y] != -1 && surfaceWater.evaporated(x, y, dustMap[x][y])){
                    waterEvaporated[x][y]++;
                }
            }
        }
    }

    // A function that draws the shape of a cloud
    private void drawCloud(Cloud cloud){
        drawSphere(cloud.getPos(), 0, 0, 0, 9);
        drawSphere(cloud.getPos(), -5, 0, 0, 6);
        drawSphere(cloud.getPos(), +5, 0, 0, 6);
        drawSphere(cloud.getPos(), 0, -5, 0, 6);
        drawSphere(cloud.getPos(), 0, +5, 0, 6);
        drawSphere(cloud.getPos(), 0, 0, +5, 6);
    }

    // Drawing a component of the cloud
    private void drawSphere(PVector pos, int x_dev, int y_dev, int z_dev, int size){
        pushMatrix();
        translate(pos.x * scale + x_dev, pos.y * scale + y_dev, pos.z + z_dev);
        if(!raining){
            fill(255);
        }else{
            fill(200);
        }
        sphere(size);
        popMatrix();
    }

    // A function that draws the shape of a droplet (and update its position)
    private void drawRainDroplet(RainDroplet rainDroplet){
        stroke(188);
        line(rainDroplet.getX() * scale, rainDroplet.getY() * scale, rainDroplet.getZ(),
                rainDroplet.getX() * scale, rainDroplet.getY() * scale, rainDroplet.getZ() - rainDroplet.getLength());
        rainDroplet.fall();
    }


    // A function that resets the water vapor once raining is finished
    private void resetGridPoints(){
        for(int x = 0; x < cols; x++){
            for(int y = 0; y < rows; y++){
                waterEvaporated[x][y] = 0;
            }
        }
    }
}
