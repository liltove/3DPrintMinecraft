package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;



public class CheckBlocks {
    
    static List<Double> pos1 = new ArrayList();
    static List<Double> pos2 = new ArrayList();
    
    public CheckBlocks(){
        //temporarily initializing the lists here so we don't have to call the wand function
        //FEEL FREE TO CHANGE THESE NUMBERS
        pos1.add(0.0);
        pos1.add(0.0);
        pos1.add(0.0);
        pos2.add(1.0);
        pos2.add(2.0);
        pos2.add(5.0);
        //call the function that will check the matrix cube
        loopThruCoords(pos1, pos2);
    }
    
    public void loopThruCoords(List<Double> p1, List<Double> p2){
        //initialize the x, y, z -- these will chage as going through the matrix cube
        double x = 0.0;
        double y = 0.0;
        double z = 0.0;
        
        //do math here to loop through
        
        //to access x => p1.get(0), y => p1.get(1), z => p1.get(2)
        
        //to call checkBlock
        checkBlock(x,y,z);
    }
    
    public boolean checkBlock(double x, double y, double z){
        //get block for the coordiantes at (x,y,z)
        
        //check if that block is air or not
        
        return false;
    }
}
