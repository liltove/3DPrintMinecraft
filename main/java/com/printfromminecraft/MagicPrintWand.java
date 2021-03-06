package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MagicPrintWand extends Item {
    
    static List<Integer> pos1 = new ArrayList();
    static List<Integer> pos2 = new ArrayList();
    private final String name = "magicPrintWand";
    
    public MagicPrintWand(){
        
        //setUnlocalizedName("printfromminecraft" + "_" + name);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabs.TOOLS);
        //PrintFromMinecraft.namesList.add(this);
        GameRegistry.register(this);
        GameRegistry.registerItem(this, name);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote){
            BlockPos b = new BlockPos(getBlockCoords(world, player));
            if (pos1.isEmpty()){
                pos1.add(b.getX());
                pos1.add(b.getY());
                pos1.add(b.getZ());

                player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 1 set to " + player.posX + ", " + player.posY + ", " + player.posZ + "."));
            } else if (!pos1.isEmpty() && pos2.isEmpty()){
                pos2.add(b.getX());
                pos2.add(b.getY());
                pos2.add(b.getZ());

                player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 2 set to " + player.posX + ", " + player.posY + ", " + player.posZ + "."));
            } else {
                pos1.clear();
                pos2.clear();

                pos1.add(b.getX());
                pos1.add(b.getY());
                pos1.add(b.getZ());
        
                player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 1 set to " + player.posX + ", " + player.posY + ", " + player.posZ + "."));
            }
            
        }
        
        
        

        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }
    
    public String getName() {
        return name;
        }
    
    public BlockPos getBlockCoords(World world, EntityPlayer player){
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        
        BlockPos block = new BlockPos((int)x, (int)y, (int)z);
        
        player.addChatMessage(new TextComponentString(world.getBlockState(block).getBlock().getLocalizedName()));
        return block; 
    }
}