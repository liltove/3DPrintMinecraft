package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;



public class CheckBlocks {
    
    static List<Double> pos1 = new ArrayList();
    static List<Double> pos2 = new ArrayList();
    
    //so we know the min and max of our area
    public static double xmin = 0.0;
    public static double xmax = 0.0;
    public static double ymin = 0.0;
    public static double ymax = 0.0;
    public static double zmin = 0.0;
    public static double zmax = 0.0;
    
    //create an array to hold all the solid block position values
    public static List<SolidBlockPositions> printObject = new ArrayList();
    public static List<Double> widthHeightDepth = new ArrayList();
    
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
    
    public void checkBlock(double x, double y, double z){
        //get block for the coordiantes at (x,y,z)
        
        //check if that block is air or not
        
        //if not air, add a new solid block position to the array
        printObject.add(new SolidBlockPositions(x, y, z));
    }
    
    public void setMinMax(){
        //sets all the mins and maxes for the marked coordinates
        if(pos1.get(0)> pos2.get(0)){
            xmax = pos1.get(0);
            xmin = pos2.get(0);
        }else{
            xmax = pos2.get(0);
            xmin = pos1.get(0);
        }
        if(pos1.get(1)> pos2.get(1)){
            xmax = pos1.get(1);
            xmin = pos2.get(1);
        }else{
            xmax = pos2.get(1);
            xmin = pos1.get(1);
        }
        if(pos1.get(2)> pos2.get(2)){
            xmax = pos1.get(2);
            xmin = pos2.get(2);
        }else{
            xmax = pos2.get(2);
            xmin = pos1.get(2);
        }
    }
}
