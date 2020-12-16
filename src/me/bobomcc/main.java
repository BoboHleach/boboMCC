package me.bobomcc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class  main extends JavaPlugin {
	protected eventHandler event;
	protected boolean hasStarted = false;
	private worldBorder worldBorderHandler;
	protected boolean isInGracePeriod = true;
	@Override
	public void onEnable(){
		event = new eventHandler(this);
		getServer().getPluginManager().registerEvents(event,this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("start") && sender.isOp()) {
			hasStarted = true;
			worldBorderHandler = new worldBorder(this, ((Player) sender).getWorld());
			worldBorderHandler.startShrink();
			getServer().broadcastMessage("Grace Period Ending in 5 Minutes");
			new BukkitRunnable() {
				@Override
				public void run() {
					getServer().broadcastMessage("Grace Period is over");
					isInGracePeriod = false;
				}
			}.runTaskLater(this,6000);
			return true;
		}
		if(!hasStarted) {
			// Checks if flag 1 is a valid Option
			if (args.length != 0) {
				if (!args[0].equalsIgnoreCase("compress") && !args[0].equalsIgnoreCase("copy") && !args[0].equalsIgnoreCase("warp") && !args[0].equalsIgnoreCase("collector") && !args[0].equalsIgnoreCase("tank")) {
					sender.sendMessage("Invalid Class\nPlease select: compress|copy|warp|collector|tank");
				} else if (label.equalsIgnoreCase("class") && args.length > 0) {
					event.playerToAbilityHashMap.get(sender).name = args[0];
					sender.sendMessage("You Have Selected: " + args[0]);
				}
				if (label.equalsIgnoreCase("team") && args.length > 0) {
					event.playerTeamHashMap.replace((Player) sender, args[0]);
				}
				return true;
			}
		}
		return false;
	}
}
