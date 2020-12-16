package me.bobomcc;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class worldBorder {
    protected main plugin;
    World worldToShrink;
    protected worldBorder(main plugin, World worldToShrink){
        this.plugin = plugin;
        this.worldToShrink = worldToShrink;
        this.worldToShrink.getWorldBorder().setCenter(new Location(worldToShrink, 0,0,0));
    }

    protected void startShrink(){
        plugin.getServer().broadcastMessage("World Border shrinking in 5 Minutes");
        new BukkitRunnable() {
            @Override
            public void run() {
                worldToShrink.getWorldBorder().setSize((worldToShrink.getWorldBorder().getSize() * 0.25) - 500);
                plugin.getServer().broadcastMessage("World Border has Shrank");
            }
        }.runTaskTimer(plugin,6000,6000);
    }
}
