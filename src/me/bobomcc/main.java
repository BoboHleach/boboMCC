package me.bobomcc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.File;

public class  main extends JavaPlugin {
	protected eventHandler event;
	protected boolean hasStarted = false;
	private worldBorder worldBorderHandler;
	protected boolean isInGracePeriod = true;
	private int gracePeriodTicks;
	@Override
	public void onEnable(){
		event = new eventHandler(this);
		getServer().getPluginManager().registerEvents(event,this);
		createConfigIfEmpty();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("start") && sender.isOp() && !hasStarted) {
			hasStarted = true;
			gracePeriodTicks = ((int) getConfig().get("game.gracePeriodSeconds") )* 20;
			worldBorderHandler = new worldBorder(this, ((Player) sender).getWorld());
			worldBorderHandler.startShrink();
			getServer().broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "GRACE PERIOD ENDING IN "+ ((int) getConfig().get("game.gracePeriodSeconds") /60)+" Minutes");
			new BukkitRunnable() {
				@Override
				public void run() {
					getServer().broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "GRACE PERIOD IS OVER");
					isInGracePeriod = false;
				}
			}.runTaskLater(this,gracePeriodTicks);
			return true;
		}
		if(!hasStarted) {
			// Checks if flag 1 is a valid Option
			if (args.length != 0) {
				if (label.equalsIgnoreCase("team") && args.length > 0) {
					event.playerTeamHashMap.replace((Player) sender, args[0]);
					getConfig().set(((Player) sender).getDisplayName()+"."+"team", args[0]);
					saveConfig();
					return true;
				}
				if (!args[0].equalsIgnoreCase("erase") && !args[0].equalsIgnoreCase("compress") && !args[0].equalsIgnoreCase("copy") && !args[0].equalsIgnoreCase("warp") && !args[0].equalsIgnoreCase("collector") && !args[0].equalsIgnoreCase("tank")) {
					sender.sendMessage(ChatColor.DARK_RED + "Invalid Class, please select: " + ChatColor.GREEN + "Compress, Copy, Warp, Collector, Tank, Erase");
				} else if (label.equalsIgnoreCase("class") && args.length > 0) {
					getConfig().set(((Player) sender).getDisplayName() + "." + "class", args[0].toLowerCase());
					saveConfig();
					event.playerToAbilityHashMap.get(sender).name = args[0];
					sender.sendMessage(ChatColor.GREEN + "You Have Selected: " + args[0]);
					return true;
				}
			}
		}
		return false;
	}

	private void createConfigIfEmpty(){
		File configFile = new File(this.getDataFolder(), "config.yml");
		if(!configFile.exists()){
			getConfig().set("game.gracePeriodSeconds",300);
			getConfig().set("game.worldBorder.reductionTime",120);
			getConfig().set("game.worldBorder.delay",3000);
			getConfig().set("game.worldBorder.startingSize",2500);
			saveConfig();
		}
	}
}
