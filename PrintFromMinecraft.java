package com.printfromminecraft;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = PrintFromMinecraft.MODID, version = PrintFromMinecraft.VERSION)
public class PrintFromMinecraft {
    public static final String MODID = "printfromminecraft";
    public static final String VERSION = "0.01";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        //add the recipe to create an apple
        GameRegistry.addRecipe(new ItemStack(Items.APPLE),
            "XXX",
            "XXX",
            "XXX",
            'X', Blocks.DIRT
        );
    }
}