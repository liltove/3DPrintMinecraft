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
    
    public MagicPrintWand(){
        super();
        setRegistryName("magicPrintWand");
        setUnlocalizedName("magicPrintWand");
        setCreativeTab(CreativeTabs.TOOLS);
        GameRegistry.register(this);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote){
            BlockPos b = new BlockPos(getBlockCoords(world, player));
            if (pos1.isEmpty() && pos2.isEmpty()){
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
    
    public BlockPos getBlockCoords(World world, EntityPlayer player){
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        
        BlockPos block = new BlockPos((int)x, (int)y-2, (int)z);
        
        player.addChatMessage(new TextComponentString(world.getBlockState(block).getBlock().getLocalizedName()));
        return block; 
    }
    
    private void swapPositions(int index) {
        int temp = pos1.get(index);
        pos1.set(index,pos2.get(index));
        pos2.set(index, temp);
    }
}
