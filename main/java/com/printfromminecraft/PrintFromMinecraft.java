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
    public static final String VERSION = "0.01";
    
    public static Block printBlock;
    
    @SidedProxy(clientSide = "com.printfromminecraft.ClientProxy", serverSide = "com.printfromminecraft.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        printBlock = new PrintBlock();
        proxy.registerRendering();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        //add the recipe to create an apple
        GameRegistry.addRecipe(new ItemStack(printBlock),
            "XXX",
            "XXX",
            "XXX",
            'X', Blocks.DIRT
        );
        
        //add the new block type
        
    }
    
    @EventHandler
    public void init(FMLPostInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            renderItem.getItemModelMesher().register(Item.getItemFromBlock(printBlock),0,new ModelResourceLocation(MODID + ":" + ((PrintBlock)printBlock).getName(), "inventory"));
        }
        
    }
    
    @EventHandler
    public void registerCommands(FMLServerStartingEvent event) {
        //event.registerServerCommand(new PrintModel());
        event.registerServerCommand(new BlockFillCommand());
    }
    
    
}