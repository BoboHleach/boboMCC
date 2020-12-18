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
        this.worldToShrink.getWorldBorder().setDamageAmount(1);
        this.worldToShrink.getWorldBorder().setSize(2500);
    }

    protected void startShrink(){
        plugin.getServer().broadcastMessage("World Border shrinking in 2.5 Minutes");
        new BukkitRunnable() {
            @Override
            public void run() {
                worldToShrink.getWorldBorder().setSize(worldToShrink.getWorldBorder().getSize() - 500, 120);
                plugin.getServer().broadcastMessage("World Border has Shrank");
                plugin.getServer().broadcastMessage("World Border is " + (worldToShrink.getWorldBorder().getSize() - 500));
            }
        }.runTaskTimer(plugin,3000,3000);
    }
}
