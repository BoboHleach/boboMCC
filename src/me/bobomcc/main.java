package me.bobomcc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class  main extends JavaPlugin {
	private eventHandler event;
	private boolean hasStarted = false;
	@Override
	public void onEnable(){
		this.event = new eventHandler(this);
		playerJoinEvent playerJoinClass = new playerJoinEvent(event,this);
		this.getServer().getPluginManager().registerEvents(playerJoinClass,this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("start") && sender.isOp()) {
			hasStarted = true;
			this.getServer().getPluginManager().registerEvents(event,this);
			return true;
		}
		if(!hasStarted) {
				if (label.equalsIgnoreCase("team") && args.length > 0) {
				} else if (label.equalsIgnoreCase("class") && args.length > 0) {
					event.playerToAbilityHashMap.get(sender).name = args[0];
					return true;
				}
		}
		return false;
	}
}
