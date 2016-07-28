
package com.printfromminecraft;

import java.util.List;
import java.util.ArrayList;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos; 
import net.minecraft.util.ChatComponentText; 
import net.minecraft.util.EnumChatFormatting; 
import net.minecraft.world.World; 
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;

public class BlockFillCommand extends CommandBase{
    private List aliases = new ArrayList();
    private Block block;
    
    public BlockFillCommand(){
        aliases.add("fillblocks");
    }
    
    @Override
    public String getName() {
        return null;
    }
    
    @Override
    public int compareTo(Object o){
        return 0;
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender){
        return "fillblocks <block ID>";
    }
    
    @Override
    public List getAliases(){
        return aliases;
    }
    
    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos){
        return null;
    }
    
    @Override
    public boolean isUsernameIndex(String[] args, int index){
        return false;
    }
    
    @Override
    public void execute(ICommandSender sender, Strings[] args){
        if(args.length != 1){
            sendErrorMessage(sender, "Invalid number of arguments");
            return;
        }
        
        try {
            block = Block.getBlockById(Integer.parseInt(args[0]));
            
            if (block == Blocks.AIR && Integer.parseInt(args[0]) != 0) {
                sendErrorMessage(sender,"The argument \"" + args[0]) + "\" is not a valid black ID.");
                return;
            }
        } catch (NumberFormatException e){
            if(Block.getBlockFromName(args[0])==null) {
                sendErrorMessage(sender, "The argument \"" + args[0]) + "\" is not a valid black name/");
                return;
            } else {
                block = Block.getBlockFromName(args[0]);
            }
        }
        
        if (BlockFillerPositionSelector.pos1.isEmpty() || BlockFillerPositionSelector.pos2.isEmpty()) {
            sendErrorMessage(sender, "Make a region selction first.");
            return;
        }
        
        if (BlockFillerPositionSelector.pos1.get(0) > BlockFillerPositionSelector.pos2.get(0)) {
            swapPositions(0);
        }
        
        if(BlockFillerPositionSelector.pos1.get(1) > BlockFillerPositionSelector.pos2.get(1)) {
            swapPositions(1);
        }
        
        if(BlockFillerPositionSelector.pos1.get(2) > BlockFillerPositionSelector.pos2.get(2)) {
            swapPositions(2);
        }
        
        for (int x = BlockFillerPositionSelector.pos1.get(0); x <= BlockFillerPositionSelector.pos2.get(0); x++){
            for (int y = BlockFillerPositionSelector.pos1.get(1); y <= BlockFillerPositionSelector.pos2.get(1); y++){
                for (int z = BlockFillerPositionSelector.pos1.get(2); z <= BlockFillerPositionSelector.pos2.get(2); z++){
                    ((EntityPlayer)sender).worldObj.setBlockState(new BlockPos(x,y,z),block.getBlockState().getBaseState());
                }
            }
                    
        }
    }
    
    private void swapPositions(int index) {
        int temp = BlockFillerPositionSelector.pos1.get(index);
        BlockFillerPositionSelector.pos1.set(index,BlockFillerPositionSelector.pos2.get(index));
        BlockFillerPositionSelector.pos2.set(index, temp);
    }
    
    private void sendErrorMessage(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + message));
    }
    
    @Override
    public boolean canCommandSenderUse(ICommandSender sender) {
        return sender instanceOf EntityPlayer;
    }
}
