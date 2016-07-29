package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;



public class SolidBlockPositions {
    static List<Double> solidBlockPos = new ArrayList();
    
    public SolidBlockPositions(){
        
    }
    
    public SolidBlockPositions(Double x, Double y, Double z){
        solidBlockPos.add(x);
        solidBlockPos.add(y);
        solidBlockPos.add(z);
    }
    
    public double getX(){
        return solidBlockPos.get(0);
    }
    
    public double getY(){
        return solidBlockPos.get(1);
    }
    
    public double getZ(){
        return solidBlockPos.get(2);
    }
}
