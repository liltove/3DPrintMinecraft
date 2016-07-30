package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;



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
    
    public CheckBlocks(World world){
        //temporarily initializing the lists here so we don't have to call the wand function
        //FEEL FREE TO CHANGE THESE NUMBERS
        pos1.add(0.0);
        pos1.add(0.0);
        pos1.add(0.0);
        pos2.add(1.0);
        pos2.add(2.0);
        pos2.add(5.0);
        
        //call the function that will check the matrix cube
        loopThruCoords(pos1, pos2, world);
    }
    
    public void loopThruCoords(List<Double> p1, List<Double> p2, World world){
        //initialize the x, y, z -- these will chage as going through the matrix cube
        double x = 0.0;
        double y = 0.0;
        double z = 0.0;
        
        //do math here to loop through
        
        //to access x => p1.get(0), y => p1.get(1), z => p1.get(2)
        
        //to call checkBlock
        checkBlock(x,y,z, world);
    }
    
    public void checkBlock(double x, double y, double z, World world){
        //get block for the coordiantes at (x,y,z)
        //check if that block is air or not
        BlockPos block = new BlockPos((int)x, (int)y-2, (int)z);
        if (world.getBlockState(block).getBlock().isAir(world.getBlockState(block), world, block)) {
            //it's air, do nothing
            return;
        }
        
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
            ymax = pos1.get(1);
            ymin = pos2.get(1);
        }else{
            ymax = pos2.get(1);
            ymin = pos1.get(1);
        }
        if(pos1.get(2)> pos2.get(2)){
            zmax = pos1.get(2);
            zmin = pos2.get(2);
        }else{
            zmax = pos2.get(2);
            zmin = pos1.get(2);
        }
    }
}
