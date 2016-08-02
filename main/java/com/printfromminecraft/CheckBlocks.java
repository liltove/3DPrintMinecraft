package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CheckBlocks {
    //create an array to hold all the solid block position values
    //public static List<SolidBlockPositions> printObject = new ArrayList();
    public static List<SolidBlockPositions> printObject = new ArrayList<SolidBlockPositions>();

    public static List<Integer> widthHeightDepth = new ArrayList();
    
    public CheckBlocks(World world){
        //call the function that will check the matrix cube
        //loopThruCoords(MagicPrintWand.pos1, MagicPrintWand.pos2, world);
        System.out.println("initialized checker.");
    }
    
    public void loopThruCoords(List<Integer> p1, List<Integer> p2, World world){
        //to access x => p1.get(0), y => p1.get(1), z => p1.get(2)
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
        int width = p2.get(0) - p1.get(0) + 1;
        widthHeightDepth.add(width);
        
        int depth = p2.get(2) - p1.get(2) + 1;
        widthHeightDepth.add(depth);

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
        
        //check for air blocks in layer
        int check = 0;
        //get area of print        
        int area = width * depth;
        //starting x
        int x = p1.get(0);
        //starting z
        int z = p1.get(2);
        //establish y for the layers
        int y = p1.get(1);
        int starty = y;
        
        
        System.out.println("width = " + width);
        System.out.println("depth = " + depth);
        System.out.println("Starting x = " + x);
        System.out.println("Starting z = " + z);
        System.out.println("Area of print: " + area);
        
        while ((check < area) || (y == 256)) {
            System.out.println("y = " + y);
            System.out.println("Check = " + check + " And area = " + area);
            check = 0;
            for (int i = x; i <= width + x; i++) {
                System.out.println("x = " + i);
                for (int j = z; j <= depth + z; j++) {
                    System.out.println("z = " + j);
                    //to call checkBlock
                    if (checkBlock(world,i,y,j)){
                        //System.out.println("Block of air at: (" + i + ", " + j + ", " + y + ")");
                        //System.out.println("Check = " + check);
                        check++;
                        System.out.println("Check = " + check + " And area = " + area);
                    }else{
                        SolidBlockPositions curBlock = new SolidBlockPositions();
                        
                        //if not air, add a new solid block position to the array
                        int m = i - x;
                        int n = y - starty;
                        int o = j - z;
                        
                        curBlock.setX(m);
                        curBlock.setY(n);
                        curBlock.setZ(o);
                        
                        printObject.add(curBlock);
                        
                        System.out.println("Added block to printobject array at coords: " + curBlock);
                        System.out.println(printObject);
                    }
                }
            }
            
            y++;
            
            
        }
        
        widthHeightDepth.add(y);
        System.out.println("Height in list: " + widthHeightDepth.get(2));
    }
    public boolean checkBlock(World world, double x, double y, double z){
        //get block for the coordiantes at (x,y,z)
        BlockPos block = new BlockPos((int)x, (int)y, (int)z);
        //check if that block is air or not
        if (world.getBlockState(block).getBlock().isAir(world.getBlockState(block), world, block)){
            return true;
        } else {
            return false;

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
