package me.bobomcc;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;


public class worldBorder {
    protected main plugin;
    private static World WORLD_TO_SHRINK;
    private static int WORLD_BORDER_DELAY = 600;
    private static long WORLD_BORDER_STARTING_SIZE = 2500;
    private static int SHRINK_BLOCK_SIZE = 500;
    private long timeSinceLastShrink = 0;
    protected worldBorder(main plugin, World worldToShrink){
        this.plugin = plugin;
        this.WORLD_TO_SHRINK = worldToShrink;
        this.WORLD_TO_SHRINK.getWorldBorder().setCenter(new Location(worldToShrink, 0,0,0));
        this.WORLD_TO_SHRINK.getWorldBorder().setDamageAmount(1);
        this.timeSinceLastShrink = System.currentTimeMillis();
    }
    
    protected void checkShrinkTime() {
    	if(plugin.timeInMillis - this.timeSinceLastShrink >= this.WORLD_BORDER_DELAY) {
    		timeSinceLastShrink = System.currentTimeMillis();
    		
    		shrink();
    	}
    }
    
    
    private void shrink() {
    	WORLD_TO_SHRINK.getWorldBorder().setSize(WORLD_TO_SHRINK.getWorldBorder().getSize() - SHRINK_BLOCK_SIZE,WORLD_BORDER_DELAY * 20);
    }
}
