package com.javaminecraft;

import java.util.logging.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.*;

public class PrintModel extends JavaPlugin {
    public static final Logger LOG = Logger.getLogger("Minecraft");
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (label.equalsIgnoreCase("printmodel")) {
            if (sender instanceof Player) {
                //get the player
                Player me = (Player) sender;
                //get the player's location
                Location spot = me.getLocation();
                //get the game world
                World world = me.getWorld();
                //write to player console
                me.sendMessage("You have summoned the 3D-printer");
            }
        }
        return false;
    }
}