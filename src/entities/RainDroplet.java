package entities;

import processing.core.PVector;
import processing.core.PApplet;

public class RainDroplet extends PApplet{

    private PVector cloud_pos;
    private float x;
    private float y;
    private float z;
    private PVector vel;
    private float length = random(5,10);
    private int ground_altitude;

    public RainDroplet(Cloud cloud, int ground_altitude){
        this.cloud_pos = cloud.getPos();
        this.x = cloud_pos.x + random(-0.5f, 0.5f);
        this.y = cloud_pos.y + random(-0.5f, 0.5f);
        this.z = cloud_pos.z;
        this.vel = new PVector(0, 0, random(-8, -4));
        this.ground_altitude = ground_altitude;
    }

    public void fall(){
        // Update the z-position of the droplet according to velocity
        z += vel.z;

        // If the rain hit the ground, reset it to the cloud
        if(z <= ground_altitude){
            z = cloud_pos.z;
            x = cloud_pos.x + random(-0.5f, 0.5f);
            y = cloud_pos.y + random(-0.5f, 0.5f);
            vel.z = random(-8, -4);
        }
    }

    // A function that gets the x-position of the droplet
    public float getX(){
        return x;
    }

    // A function that gets the y-position of the droplet
    public float getY(){
        return y;
    }

    // A function that gets the z-position of the droplet
    public float getZ(){
        return z;
    }

    // A function that gets the length of the droplet
    public float getLength(){
        return length;
    }

}
