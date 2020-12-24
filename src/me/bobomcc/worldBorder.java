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
    World worldToShrink;
    int worldBorderDelayTicks = 3000;
    long worldBorderStartingSizeBlocks = 2500;
    int worldBorderReductionTimeSeconds = 120;
    protected worldBorder(main plugin, World worldToShrink){
        this.plugin = plugin;
        this.worldToShrink = worldToShrink;
        this.worldToShrink.getWorldBorder().setCenter(new Location(worldToShrink, 0,0,0));
        this.worldToShrink.getWorldBorder().setDamageAmount(1);
        this.worldToShrink.getWorldBorder().setSize(worldBorderStartingSizeBlocks);

        worldBorderReductionTimeSeconds = (int) plugin.getConfig().get("game.worldBorder.reductionTime");
        worldBorderDelayTicks = (int) plugin.getConfig().get("game.worldBorder.delay");
        worldBorderStartingSizeBlocks = (int) plugin.getConfig().get("game.worldBorder.startingSize");
    }

    protected void startShrink(){
    	IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + "WORLD BORDER IS SHRINKING IN " + ((float)worldBorderDelayTicks) / 1200 + " Minutes" + "\",color:" + ChatColor.DARK_RED.name().toLowerCase() + "}");
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		PacketPlayOutTitle length = new PacketPlayOutTitle(15, 80, 15);
		for (Player player : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
        plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "WORLD BORDER IS SHRINKING IN " + ((float)worldBorderDelayTicks) / 1200 + " Minutes");
        new BukkitRunnable() {
            @Override
            public void run() {
                worldToShrink.getWorldBorder().setSize(worldToShrink.getWorldBorder().getSize() - 500, worldBorderReductionTimeSeconds);
                IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + "WORLD BORDER IS " + (worldToShrink.getWorldBorder().getSize() / 2) + " AND IS SHRINKING TO " + ((worldToShrink.getWorldBorder().getSize() / 2) - 250) + "\",color:" + ChatColor.DARK_RED.name().toLowerCase() + "}");
        		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
        		PacketPlayOutTitle length = new PacketPlayOutTitle(15, 80, 15);
    			((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    			((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
            plugin.getServer().broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "WORLD BORDER IS " + (worldToShrink.getWorldBorder().getSize() / 2) + " AND IS SHRINKING TO " + ((worldToShrink.getWorldBorder().getSize() / 2) - 250));
            }
        }.runTaskTimer(plugin,(long) worldBorderDelayTicks,(long) worldBorderDelayTicks);
    }
}
}
