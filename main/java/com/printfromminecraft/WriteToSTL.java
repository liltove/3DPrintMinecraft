package com.printfromminecraft;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
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
    
    //temp until check blocks is functional
    public static List<SolidBlockPositions> printObject = new ArrayList();
    public static List<Integer> widthHeightDepth = new ArrayList();
    
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
        if(args.length != 1){
            sendErrorMessage(sender, "Invalid number of arguments");
            return;
        }
        
        String filename = args[0];
        String stlfile = "/stlfiles/" + filename + ".stl";
        
        //call CheckBlocks to fill up the SolidBlockPositions array here
        SolidBlockPositions b1 = new SolidBlockPositions(0.0,0.0,0.0);
        SolidBlockPositions b2 = new SolidBlockPositions(1.0,1.0,1.0);
        SolidBlockPositions b3 = new SolidBlockPositions(2.0,2.0,2.0);
        SolidBlockPositions b4 = new SolidBlockPositions(3.0,3.0,3.0);
        printObject.add(b1);
        printObject.add(b2);
        printObject.add(b3);
        printObject.add(b4);
        
        int width = 4;
        int height = 4;
        int depth = 4;
        widthHeightDepth.add(width);
        widthHeightDepth.add(height);
        widthHeightDepth.add(depth);
        
        Writer writer = null;
        String end = "    endloop\n endfacet\n";
        
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(stlfile), "utf-8"));
            sender.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Starting to print to file: " + stlfile));
            //front matter of the stl file
            writer.write("solid Minecraft\n");
            //while there are still blocks in the arraylist
            for (int i = 0; i < printObject.size(); i++) {
                if ((int)printObject.get(i).getX() == 0){
                    writer.write(facetNormal(-1, 0, 0));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()+1));
                    writer.write(end);
                    writer.write(facetNormal(-1, 0, 0));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(end);
                }
                if ((int)printObject.get(i).getX() == widthHeightDepth.get(0) - 1){
                    writer.write(facetNormal(1, 0, 0));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(end);
                    writer.write(facetNormal(1, 0, 0));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()));
                    writer.write(end);
                }
                if ((int)printObject.get(i).getZ() == 0){
                    writer.write(facetNormal(0, 0, -1));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(end);
                    writer.write(facetNormal(0, 0, -1));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(end);
                }
                if ((int)printObject.get(i).getZ() == widthHeightDepth.get(2) - 1){
                    writer.write(facetNormal(0, 0, 1));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()+1));
                    writer.write(end);
                    writer.write(facetNormal(0, 0, 1));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(end);
                }
                if ((int)printObject.get(i).getY() == 0){
                    writer.write(facetNormal(0, -1, 0));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(end);
                    writer.write(facetNormal(0, -1, 0));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()));
                    writer.write(end);
                }
                if ((int)printObject.get(i).getY() == widthHeightDepth.get(1) - 1){
                    writer.write(facetNormal(0, 1, 0));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()+1));
                    writer.write(end);
                    writer.write(facetNormal(0, 1, 0));
                    writer.write(vertex((int) printObject.get(i).getX()+1, (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ()+1, (int) printObject.get(i).getY()+1));
                    writer.write(vertex((int) printObject.get(i).getX(), (int) printObject.get(i).getZ(), (int) printObject.get(i).getY()+1));
                    writer.write(end);
                }
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
    
}
