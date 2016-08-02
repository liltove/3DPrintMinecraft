package com.printfromminecraft;

public class SolidBlockPositions {
    private int x;
    private int y;
    private int z;
    
    public SolidBlockPositions(){
        this(0,0,0);
    }
    
    public SolidBlockPositions(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getZ(){
        return z;
    }
    
    public void setX(int X) {
        this.x = X;
    }
    
    public void setY(int Y) {
        this.y = Y;
    }
    
    public void setZ(int Z) {
        this.z = Z;
    }
}
