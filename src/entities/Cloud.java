package entities;

import processing.core.PVector;

public class Cloud {
    private PVector pos;
    private int x;
    private int y;
    private int z;

    public Cloud(int x, int y, int z){
        this.pos = new PVector(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PVector getPos(){
        return pos;
    }
}
