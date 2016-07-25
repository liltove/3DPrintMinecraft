package com.javaminecraft;

import java.util.logging.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.*;

public class PetWolf extends JavaPlugin {
    public static final Logger LOG = Logger.getLogger("Minecraft");
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (label.equalsIgnoreCase("petwolf")) {
            if (sender instanceof Player) {
                //get the player
                Player me = (Player) sender;
                //get the player's location
                Location spot = me.getLocation();
                //get the game world
                World world = me.getWorld();
                //spawn one wolf
                Wolf wolf = world.spawn(spot, Wolf.class);
                //set collar color
                wolf.setCollarColor(DyeColor.PURPLE);
                //make the player the owner
                wolf.setOwner(me);
                LOG.info("[PetWolf] Howl!");
                return true;
            }
        }
        return false;
    }
}