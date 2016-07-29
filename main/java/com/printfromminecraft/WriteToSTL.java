package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class WriteToSTL implements ICommand{
    private List aliases = new ArrayList();
    private Block block;
    
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
        }
        
        sendErrorMessage(sender, args[0]);
    }
    
    private void sendErrorMessage(ICommandSender sender, String message) {
        sender.addChatMessage(new TextComponentString(TextFormatting.DARK_RED + message));
    }
    
    //@Override
    public boolean canCommandSenderUse(ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }
    
    
}
