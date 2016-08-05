package com.printfromminecraft;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

@Mod(modid = PrintFromMinecraft.MODID, version = PrintFromMinecraft.VERSION)
public class PrintFromMinecraft {
    public static final String MODID = "printfromminecraft";
    public static final String VERSION = "1.0";
    
    public static List<Item> namesList = new ArrayList<Item>();
    public static Item magicPrintWand;
    
    //TutorialEventHandler handler = new TutorialEventHandler(); 
    @SidedProxy(clientSide = "com.printfromminecraft.ClientProxy", serverSide = "com.printfromminecraft.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        //event handler registry
//    	FMLCommonHandler.instance().bus().register(handler);
//    	MinecraftForge.EVENT_BUS.register(handler);
    
    	//GameRegistry.registerItem(tutorialItem, "tutorialItem");
        //register the wand texture and model
        magicPrintWand = new MagicPrintWand();
        GameRegistry.register(magicPrintWand);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
  
        
        if (event.getSide() == Side.CLIENT) {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            renderItem.getItemModelMesher().register(magicPrintWand, 0, new ModelResourceLocation(MODID + ":" + ((MagicPrintWand) magicPrintWand).getName(), "inventory"));
        }
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
        
        
    }
    
    @EventHandler
    public void registerCommands(FMLServerStartingEvent event) {
        //event.registerServerCommand(new BlockFillCommand());
        event.registerServerCommand(new WriteToSTL());
    }
    
    
}