package com.printfromminecraft;

import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

@Mod(modid = PrintFromMinecraft.MODID, version = PrintFromMinecraft.VERSION)
public class PrintFromMinecraft {
    public static final String MODID = "printfromminecraft";
    public static final String VERSION = "0.02";
    
    public static Item magicPrintWand;
    
    @SidedProxy(clientSide = "com.printfromminecraft.ClientProxy", serverSide = "com.printfromminecraft.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        //register the wand texture and model
        magicPrintWand = new MagicPrintWand();
        GameRegistry.register(magicPrintWand);

        //add the recipe to create an apple
        GameRegistry.addRecipe(new ItemStack(magicPrintWand),
            "XXX",
            "XXX",
            "XXX",
            'X', Blocks.DIRT
        );
        
    }
    
    @EventHandler
    public void init(FMLPostInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelResourceLocation magicPrintWandModel = new ModelResourceLocation(magicPrintWand.getRegistryName(), "inventory");
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(magicPrintWand, 0, magicPrintWandModel);
        }
        
    }
    
    @EventHandler
    public void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new BlockFillCommand());
    }
    
    
}