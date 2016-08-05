package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;



public class CheckBlocks {
    //create an array to hold all the solid block position values
    //public static List<SolidBlockPositions> printObject = new ArrayList();
    public static List<SolidBlockPositions> printObject = new ArrayList<SolidBlockPositions>();
    
    public static List<Integer> widthHeightDepth = new ArrayList<Integer>();
    
    public static int maxX = 0;
    public static int maxY = 0;
    public static int maxZ = 0;
    
    public static int minX = 0;
    public static int minY = 0;
    public static int minZ = 0;
    
    public CheckBlocks(World world){
        //call the function that will check the matrix cube
        //loopThruCoords(MagicPrintWand.pos1, MagicPrintWand.pos2, world);
        System.out.println("initialized checker.");
    }
    
    public void loopThruCoords(List<Integer> p1, List<Integer> p2, World world){
        //to access x => p1.get(0), y => p1.get(1), z => p1.get(2)
        //do math here to loop through
        int width = (int)p2.get(0) - (int)p1.get(0) + 1;
        widthHeightDepth.add(width);
        
        int depth = (int)p2.get(2) - (int)p1.get(2) + 1;
        widthHeightDepth.add(depth);
        
        printObject.clear();
        
        //check for air blocks in layer
        int check = 0;
        //get area of print        
        int area = width * depth;
        //starting x
        int x = (int)p1.get(0);
        //starting z
        int z = (int)p1.get(2);
        //establish y for the layers
        int y = (int)p1.get(1);
        int starty = y;
        
        minX = p2.get(0);
        minY = p2.get(1);
        minZ = p2.get(2);
        
        
        System.out.println("width = " + width);
        System.out.println("depth = " + depth);
        System.out.println("Starting x = " + x);
        System.out.println("Starting z = " + z);
        System.out.println("Area of print: " + area);
        int found = 0;
        while ((check < area) && (y <= 256)) {
            System.out.println("y = " + y);
            System.out.println("Check = " + check + " And area = " + area + " And found = " +found);
            check = 0;
            found = 0;
            for (int i = x; i < width + x; i++) {
                //System.out.println("x = " + i);
                for (int j = z; j < depth + z; j++) {
                    //System.out.println("z = " + j);
                    //to call checkBlock
                    if (checkBlock(world,i,y,j)){
                        //System.out.println("Block of air at: (" + i + ", " + j + ", " + y + ")");
                        //System.out.println("Check = " + check);
                        check++;
                        //System.out.println("Check = " + check + " And area = " + area);
                    }else{
                        found++;
                        SolidBlockPositions curBlock = new SolidBlockPositions();
                        
                        
                        
                        //if not air, add a new solid block position to the array
                        int m = i - x;
                        int n = y - starty;
                        int o = j - z;
                        
                      
                        
                        
                        
                        if (m < minX){
                            minX = m;
                            //System.out.println("New Max X: " + maxX);
                        }
                        
                        if (n < minY){
                            minY = n;
                            //System.out.println("New Max Y: " + maxY);
                        }
                        
                        if (o < minZ){
                            minZ = o;
                            //System.out.println("New Max Z: " + maxZ);
                        }
                        
                        m = m - minX;
                        n = n - minY;
                        o = o - minZ;
                        
                        if (m > maxX){
                            maxX = m + 1;
                            System.out.println("New Max X: " + maxX);
                        }
                        
                        if (n > maxY){
                            maxY = n + 1;
                            //System.out.println("New Max Y: " + maxY);
                        }
                        
                        if (o > maxZ){
                            maxZ = o + 1;
                            //System.out.println("New Max Z: " + maxZ);
                        }
                        
                        curBlock.setX(m);
                        curBlock.setY(n);
                        curBlock.setZ(o);
                        printObject.add(curBlock);
                        System.out.println(printObject.get(printObject.size()-1).getX() + ", " + printObject.get(printObject.size()-1).getY() + ", " + printObject.get(printObject.size()-1).getZ());
                        
                        //add extra coords
                        curBlock.setX(m);
                        curBlock.setY(n);
                        curBlock.setZ(o+1);
                        printObject.add(curBlock);
                        System.out.println(printObject.get(printObject.size()-1).getX() + ", " + printObject.get(printObject.size()-1).getY() + ", " + printObject.get(printObject.size()-1).getZ());
                        
                        curBlock.setX(m+1);
                        curBlock.setY(n);
                        curBlock.setZ(o);
                        printObject.add(curBlock);
                        System.out.println(printObject.get(printObject.size()-1).getX() + ", " + printObject.get(printObject.size()-1).getY() + ", " + printObject.get(printObject.size()-1).getZ());
                        
                        curBlock.setX(m+1);
                        curBlock.setY(n);
                        curBlock.setZ(o+1);
                        printObject.add(curBlock);
                        System.out.println(printObject.get(printObject.size()-1).getX() + ", " + printObject.get(printObject.size()-1).getY() + ", " + printObject.get(printObject.size()-1).getZ());
                        
                        curBlock.setX(m);
                        curBlock.setY(n+1);
                        curBlock.setZ(o);
                        printObject.add(curBlock);
                        System.out.println(printObject.get(printObject.size()-1).getX() + ", " + printObject.get(printObject.size()-1).getY() + ", " + printObject.get(printObject.size()-1).getZ());
                        
                        //add extra coords
                        curBlock.setX(m);
                        curBlock.setY(n+1);
                        curBlock.setZ(o+1);
                        printObject.add(curBlock);
                        System.out.println(printObject.get(printObject.size()-1).getX() + ", " + printObject.get(printObject.size()-1).getY() + ", " + printObject.get(printObject.size()-1).getZ());
                        
                        curBlock.setX(m+1);
                        curBlock.setY(n+1);
                        curBlock.setZ(o);
                        printObject.add(curBlock);
                        System.out.println(printObject.get(printObject.size()-1).getX() + ", " + printObject.get(printObject.size()-1).getY() + ", " + printObject.get(printObject.size()-1).getZ());
                        
                        curBlock.setX(m+1);
                        curBlock.setY(n+1);
                        curBlock.setZ(o+1);
                        printObject.add(curBlock);
                        System.out.println(printObject.get(printObject.size()-1).getX() + ", " + printObject.get(printObject.size()-1).getY() + ", " + printObject.get(printObject.size()-1).getZ());
                        
                    }
                }
            }
            
            System.out.println("POST Check = " + check + " And area = " + area + " And found = " +found);
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
        }
    }
}