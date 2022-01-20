package entities;

public class Cloud {
    private int x;
    private int y;
    private int z;
    private int size;

    public Cloud(int x, int y, int z, int size){
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getSize() {
        return size;
    }
}
