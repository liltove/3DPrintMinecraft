package com.printfromminecraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MagicPrintWand extends Item {
    
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

        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }
}
