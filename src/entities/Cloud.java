package entities;

import processing.core.PApplet;
import processing.core.PVector;


public class Cloud extends PApplet {
    private PVector pos;
    private int waterStored = (int)random(20, 50);

    public Cloud(int x, int y, int z){
        float z_rand = random(-20, 20);
        this.pos = new PVector(x, y, z+z_rand);
    }

    // A function that gets that (x, y, z) position of this cloud
    public PVector getPos(){
        return pos;
    }

    // A function that get the amount of water stored in this cloud
    public int getWaterStored(){
        return waterStored;
    }

    // A probability function to deduct the amount of water stored
    // in this cloud
    public void deductWaterStored(){
        if(random(0, 1) < 0.1){
            waterStored--;
        }
    }
}
