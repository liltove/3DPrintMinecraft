package com.printfromminecraft;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class WriteToSTL implements ICommand{
    private List aliases = new ArrayList();
    private Block block;
    public int scalar;
    public int adjuster;
    
    public WriteToSTL(){
        aliases.add("printmodel");
        aliases.add("pm");
    }
    
    @Override
    public String getCommandName() {
        return null;
    }
    
    @Override
    public int compareTo(ICommand command){
        return 0;
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender){
        return "printmodel <filename>";
    }
    
    @Override
    public List getCommandAliases(){
        return aliases;
    }
    
    @Override
    public List getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos){
        return null;
    }
    
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender){
        return true;
    }
    
    @Override
    public boolean isUsernameIndex(String[] args, int index){
        return false;
    }
    
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args){
        //error checking
        if(args.length != 1){
            sendErrorMessage(sender, "Invalid number of arguments");
            return;
        }
        
        
        //check that y's are equal
        if(!checkLevel()){
            sendErrorMessage(sender, "Print area must be level.");
            return;
        }
        //check that positions aren't the same
        if(samePos()){
            sendErrorMessage(sender, "Print area must be more than one block.");
            return;
        }
        //make sure p1 is smaller
        swapPos();
        
        scalar = 1;
        adjuster = 1;
        
        sendErrorMessage(sender, "Writing to file...");
        String filename = args[0];
        String stlfile = filename + ".stl";
        
        System.out.println("Initiating checker...");
        CheckBlocks checker = new CheckBlocks(sender.getEntityWorld());
        checker.loopThruCoords(MagicPrintWand.pos1, MagicPrintWand.pos2, sender.getEntityWorld());

        Writer writer = null;
        String end = "    endloop\n endfacet\n";
        
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(stlfile), "utf-8"));
            sender.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Starting to print to file: " + stlfile));
            //front matter of the stl file
            writer.write("solid Minecraft\n");
            //while there are still blocks in the arraylist
            System.out.println("size of printobject array " + CheckBlocks.printObject.size());
            System.out.println(CheckBlocks.printObject);
            for (int i = 0; i < CheckBlocks.printObject.size(); i++) {
                int curX = CheckBlocks.printObject.get(i).getX()*scalar;
                int curY = CheckBlocks.printObject.get(i).getY()*scalar;
                int curZ = CheckBlocks.printObject.get(i).getZ()*scalar;
                //System.out.println("current block coords: (" + curX + ", " + curZ + ", " + curY + ")");
                //if (curX == 0){
                    writer.write(facetNormal(-1, 0, 0));
                    writer.write(vertex(curX, curZ+adjuster, curY));
                    writer.write(vertex(curX, curZ, curY+1));
                    writer.write(vertex(curX, curZ+adjuster, curY+adjuster));
                    writer.write(end);
                    writer.write(facetNormal(-1, 0, 0));
                    writer.write(vertex(curX, curZ+adjuster, curY));
                    writer.write(vertex(curX, curZ, curY));
                    writer.write(vertex(curX, curZ, curY+adjuster));
                    writer.write(end);
                //}
                //if (curX == CheckBlocks.maxX){
                    writer.write(facetNormal(1, 0, 0));
                    writer.write(vertex(curX+adjuster, curZ+adjuster, curY));
                    writer.write(vertex(curX+adjuster, curZ+adjuster, curY+adjuster));
                    writer.write(vertex(curX+adjuster, curZ, curY+adjuster));
                    writer.write(end);
                    writer.write(facetNormal(1, 0, 0));
                    writer.write(vertex(curX+adjuster, curZ+adjuster, curY));
                    writer.write(vertex(curX+adjuster, curZ, curY+adjuster));
                    writer.write(vertex(curX+adjuster, curZ, curY));
                    writer.write(end);
                //}
                //if (curZ == 0){
                    writer.write(facetNormal(0, 0, -1));
                    writer.write(vertex(curX, curZ, curY));
                    writer.write(vertex(curX+adjuster, curZ, curY+adjuster));
                    writer.write(vertex(curX, curZ, curY+adjuster));
                    writer.write(end);
                    writer.write(facetNormal(0, 0, -1));
                    writer.write(vertex(curX, curZ, curY));
                    writer.write(vertex(curX+adjuster, curZ, curY));
                    writer.write(vertex(curX+adjuster, curZ, curY+adjuster));
                    writer.write(end);
                //}
                //if (curZ == CheckBlocks.maxZ){
                    writer.write(facetNormal(0, 0, 1));
                    writer.write(vertex(curX, curZ+1, curY));
                    writer.write(vertex(curX, curZ+1, curY+1));
                    writer.write(vertex(curX+1, curZ+1, curY+1));
                    writer.write(end);
                    writer.write(facetNormal(0, 0, 1));
                    writer.write(vertex(curX, curZ+1, curY));
                    writer.write(vertex(curX+1, curZ+1, curY+1));
                    writer.write(vertex(curX+1, curZ+1, curY));
                    writer.write(end);
                //}
                //if (curY == 0){
                    writer.write(facetNormal(0, -1, 0));
                    writer.write(vertex(curX+1, curZ, curY));
                    writer.write(vertex(curX, curZ+1, curY));
                    writer.write(vertex(curX+1, curZ+1, curY));
                    writer.write(end);
                    writer.write(facetNormal(0, -1, 0));
                    writer.write(vertex(curX+1, curZ, curY));
                    writer.write(vertex(curX, curZ, curY));
                    writer.write(vertex(curX, curZ+1, curY));
                    writer.write(end);
                //}
                //if (curY == CheckBlocks.maxY){
                    writer.write(facetNormal(0, 1, 0));
                    writer.write(vertex(curX+1, curZ, curY+1));
                    writer.write(vertex(curX+1, curZ+1, curY+1));
                    writer.write(vertex(curX, curZ+1, curY+1));
                    writer.write(end);
                    writer.write(facetNormal(0, 1, 0));
                    writer.write(vertex(curX+1, curZ, curY+1));
                    writer.write(vertex(curX, curZ+1, curY+1));
                    writer.write(vertex(curX, curZ, curY+1));
                    writer.write(end);
                //}
            }
            writer.write("endsolid Minecraft\n");
            sender.addChatMessage(new TextComponentString(TextFormatting.GREEN +"Finished writing to file: " + stlfile));
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
        
    }
    
    private void sendErrorMessage(ICommandSender sender, String message) {
        sender.addChatMessage(new TextComponentString(TextFormatting.DARK_RED + message));
    }
    
    //@Override
    public boolean canCommandSenderUse(ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }
    
    public String facetNormal(int x, int y, int z){
        String beg = "  facet normal ";
        String mid = x + " " + y + " " + z;
        String end = beg + mid + "\n    outer loop\n";
        return end;
    }
    
    public String vertex(int x, int y, int z){
        String beg = "    vertex ";
        String mid = x + " " + y + " " + z;
        String end = beg + mid + "\n";
        return end;
    }
    
    public void swapPos(){
        List<Integer> temp = new ArrayList();
        List<Integer> temp2 = new ArrayList();
        System.out.println("Checking for swapped positions");
        if (MagicPrintWand.pos1.get(0) > MagicPrintWand.pos2.get(0)){
            temp.add(MagicPrintWand.pos1.get(0));
            temp.add(MagicPrintWand.pos1.get(1));
            temp.add(MagicPrintWand.pos1.get(2));
            temp2.add(MagicPrintWand.pos2.get(0));
            temp2.add(MagicPrintWand.pos2.get(1));
            temp2.add(MagicPrintWand.pos2.get(2));
            MagicPrintWand.pos1.clear();
            MagicPrintWand.pos1.add(MagicPrintWand.pos2.get(0));
            MagicPrintWand.pos1.add(temp.get(1));
            MagicPrintWand.pos1.add(temp.get(2));
            MagicPrintWand.pos2.clear();
            MagicPrintWand.pos2.add(temp.get(0));
            MagicPrintWand.pos2.add(temp2.get(1));
            MagicPrintWand.pos2.add(temp2.get(2));
            temp.clear();
            temp2.clear();
            System.out.println("Swapped x.");
        }
        
        if (MagicPrintWand.pos1.get(2) > MagicPrintWand.pos2.get(2)){
            temp.add(MagicPrintWand.pos1.get(0));
            temp.add(MagicPrintWand.pos1.get(1));
            temp.add(MagicPrintWand.pos1.get(2));
            temp2.add(MagicPrintWand.pos2.get(0));
            temp2.add(MagicPrintWand.pos2.get(1));
            temp2.add(MagicPrintWand.pos2.get(2));
            MagicPrintWand.pos1.clear();
            MagicPrintWand.pos1.add(temp.get(0));
            MagicPrintWand.pos1.add(temp.get(1));
            MagicPrintWand.pos1.add(MagicPrintWand.pos2.get(2));
            MagicPrintWand.pos2.clear();
            MagicPrintWand.pos2.add(temp2.get(0));
            MagicPrintWand.pos2.add(temp2.get(1));
            MagicPrintWand.pos2.add(temp.get(2));
            temp.clear();
            temp2.clear();
            System.out.println("Swapped z.");
        }
    }
    
    public boolean checkLevel(){
        System.out.println("Is the print area level?");
        //check to make sure the print area is level
        if (MagicPrintWand.pos1.get(1) == MagicPrintWand.pos2.get(1)){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean samePos(){
        System.out.println("Are position 1 and 2 the same?");
        //check to make sure pos1 and pos 2 aren't in the same spot
        if ((MagicPrintWand.pos1.get(0) == MagicPrintWand.pos2.get(0)) && (MagicPrintWand.pos1.get(2) == MagicPrintWand.pos2.get(2))){
            return true;
        } else {
            return false;
        }
    }
}
