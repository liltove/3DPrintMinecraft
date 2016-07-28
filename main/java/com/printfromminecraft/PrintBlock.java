package com.printfromminecraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PrintBlock extends Block {
    String name = "printBlock";
    
    public PrintBlock() {
        super(Material.ROCK);
        
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(PrintFromMinecraft.MODID + "_" + name);
        setCreativeTab(CreativeTabs.MATERIALS);
    }
    
    public String getName() {
        return name;
    }
}
