package me.bobomcc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

import java.io.File;

public class  main extends JavaPlugin {
	protected eventHandler event;
	protected boolean hasStarted = false;
	private worldBorder worldBorderHandler;
	protected boolean isInGracePeriod = true;
	private int gracePeriodTicks;
	protected long timeInMillis;
	
	private void startGame(CommandSender sender) {
		worldBorderHandler = new worldBorder(this, ((Player) sender).getWorld());
		this.hasStarted = true;
		timer();
	}
	
	public void timer() {
		new BukkitRunnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				timeInMillis = System.currentTimeMillis();
				worldBorderHandler.checkShrinkTime();
			}
		}.runTaskTimer(this, 1, 1);
	}
	
	@Override
	public void onEnable(){
		event = new eventHandler(this);
		getServer().getPluginManager().registerEvents(event,this);
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("start") && sender.isOp() && !hasStarted) {
			startGame(sender);
			return true;
		}
		return false;
	}
}
	
