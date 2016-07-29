package com.printfromminecraft;

import static com.printfromminecraft.BlockFillerPositionSelector.pos1;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MagicPrintWand extends Item {
    
    static List<Double> pos1 = new ArrayList();
    static List<Double> pos2 = new ArrayList();
    
    public MagicPrintWand(){
        super();
        setRegistryName("magicPrintWand");
        setUnlocalizedName("magicPrintWand");
        setCreativeTab(CreativeTabs.TOOLS);
        GameRegistry.register(this);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        
        EntityEnderman enderman = new EntityEnderman(world);
        
        enderman.setLocationAndAngles(player.posX, player.posY, player.posZ, 0,0);
        world.spawnEntityInWorld(enderman);
        
        if (pos1.isEmpty()){
            pos1.add(player.posX);
            pos1.add(player.posY);
            pos1.add(player.posZ);

            player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 1 set to " + player.posX + ", " + player.posY + ", " + player.posZ + "."));
        } else if (pos2.isEmpty()){
            pos2.add(player.posX);
            pos2.add(player.posY);
            pos2.add(player.posZ);

            player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 2 set to " + player.posX + ", " + player.posY + ", " + player.posZ + "."));
        } else {
            pos1.clear();
            pos2.clear();

            pos1.add(player.posX);
            pos1.add(player.posY);
            pos1.add(player.posZ);

            player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 1 set to " + player.posX + ", " + player.posY + ", " + player.posZ + "."));
        }

        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }
}
