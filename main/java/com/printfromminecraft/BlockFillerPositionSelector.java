package com.printfromminecraft;

import java.util.List;
import java.util.ArrayList;
import net.minecraft.init.Items;
//import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.util.text.TextComponentString; 
import net.minecraft.util.text.TextFormatting;

public class BlockFillerPositionSelector {
    static List<Integer> pos1 = new ArrayList();
    static List<Integer> pos2 = new ArrayList();
    
    @SubscribeEvent
    public void choosePositions(PlayerInteractEvent event) {
        if (event.getEntityPlayer().getHeldItemMainhand()==null || event.getEntityPlayer().getHeldItemMainhand().getItem() != Items.WOODEN_AXE || !event.getEntityPlayer().capabilities.isCreativeMode) {
            return;
        }
        
        if (event.getItemStack().useItemRightClick(event.getWorld(), event.getEntityPlayer(), EnumHand.OFF_HAND).getType() == EnumActionResult.SUCCESS) {
            if (pos1.isEmpty()){
                pos1.add(event.getPos().getX());
                pos1.add(event.getPos().getY());
                pos1.add(event.getPos().getZ());
                
                event.getEntityPlayer().addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 1 set to " + event.getPos().getX() + ", " + event.getPos().getY() + ", " + event.getPos().getZ() + "."));
                event.setCanceled(true);
            } else if (pos2.isEmpty()){
                pos2.add(event.getPos().getX());
                pos2.add(event.getPos().getY());
                pos2.add(event.getPos().getZ());
            
                event.getEntityPlayer().addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 2 set to " + event.getPos().getX() + ", " + event.getPos().getY() + ", " + event.getPos().getZ() + "."));
                event.setCanceled(true);
            } else {
                pos1.clear();
                pos2.clear();
                
                pos1.add(event.getPos().getX());
                pos1.add(event.getPos().getY());
                pos1.add(event.getPos().getZ());
                
                event.getEntityPlayer().addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 1 set to " + event.getPos().getX() + ", " + event.getPos().getY() + ", " + event.getPos().getZ() + "."));
                event.setCanceled(true);
            }
        }     
    }
}
