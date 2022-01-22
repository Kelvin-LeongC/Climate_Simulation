package entities;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Cloud extends PApplet {
    private PVector pos;
    private int dropletAmount;
    private ArrayList<RainDroplet> rainDropletsArray;

    public Cloud(int x, int y, int z, int dropletAmount){
        float z_rand = random(-10, 10);
        this.pos = new PVector(x, y, z+z_rand);
        this.dropletAmount = dropletAmount;
        rainDropletsArray = new ArrayList<>();
    }

    public PVector getPos(){
        return pos;
    }

    public int getDropletAmount(){
        return dropletAmount;
    }

    public ArrayList<RainDroplet> getRainDropletsArray(){
        return rainDropletsArray;
    }

    public void addDroplets(RainDroplet rainDroplet){
        rainDropletsArray.add(rainDroplet);
    }
}
