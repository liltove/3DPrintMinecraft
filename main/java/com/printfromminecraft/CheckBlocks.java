package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;



public class CheckBlocks {
    //so we know the min and max of our area
    public static double xmin = 0.0;
    public static double xmax = 0.0;
    public static double ymin = 0.0;
    public static double ymax = 0.0;
    public static double zmin = 0.0;
    public static double zmax = 0.0;
    
    //create an array to hold all the solid block position values
    public static List<SolidBlockPositions> printObject = new ArrayList();
    public static List<Integer> widthHeightDepth = new ArrayList();
    
    public CheckBlocks(World world){
        //call the function that will check the matrix cube
        loopThruCoords(MagicPrintWand.pos1, MagicPrintWand.pos2, world);
    }
    
    public void loopThruCoords(List<Integer> p1, List<Integer> p2, World world){
        //initialize the x, y, z -- these will chage as going through the matrix cube
        double x = 0.0;
        double y = 0.0;
        double z = 0.0;
        //to access x => p1.get(0), y => p1.get(1), z => p1.get(2)
        //do math here to loop through
        
        widthHeightDepth.add(p2.get(0) - p1.get(0) + 1);
        widthHeightDepth.add(p2.get(2) - p1.get(2) + 1);
        
        //check for air blocks in layer
        int check = 0;
        //get area of print        
        int area = widthHeightDepth.get(0) * widthHeightDepth.get(1);
        //establish y for the layers
        y = p1.get(1);
        System.out.println("Area of print: " + area);
        
        while (check < area) {
            for (int i = p1.get(0); i <= widthHeightDepth.get(0); i++) {
                for (int j = p1.get(2); j <= widthHeightDepth.get(1); j++) {
                    //to call checkBlock
                    if (checkBlock(world,x,y,z)){
                        System.out.println("Block of air at: (" + x + ", " + z + ", " + y + ")");
                        check++;
                    }else{
                        System.out.println("Added block to printobject array");
                    }
                }
            }
            y++;
            check = 0;
            
        }
    }
    
    public boolean checkBlock(World world, double x, double y, double z){
        //get block for the coordiantes at (x,y,z)
        BlockPos block = new BlockPos((int)x, (int)y, (int)z);
        //check if that block is air or not
        if (world.getBlockState(block).getBlock().isAir(world.getBlockState(block), world, block)){
            return true;
        } else {
            //if not air, add a new solid block position to the array
            printObject.add(new SolidBlockPositions(x, y, z));
            return false;
        }
    }
    
//    public void setMinMax(){
//        //sets all the mins and maxes for the marked coordinates
//        if(pos1.get(0)> pos2.get(0)){
//            xmax = pos1.get(0);
//            xmin = pos2.get(0);
//        }else{
//            xmax = pos2.get(0);
//            xmin = pos1.get(0);
//        }
//        if(pos1.get(1)> pos2.get(1)){
//            xmax = pos1.get(1);
//            xmin = pos2.get(1);
//        }else{
//            xmax = pos2.get(1);
//            xmin = pos1.get(1);
//        }
//        if(pos1.get(2)> pos2.get(2)){
//            xmax = pos1.get(2);
//            xmin = pos2.get(2);
//        }else{
//            xmax = pos2.get(2);
//            xmin = pos1.get(2);
//        }
//    }
}
