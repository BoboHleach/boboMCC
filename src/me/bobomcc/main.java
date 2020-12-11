package me.bobomcc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import me.boboclass.*;

public class  main extends JavaPlugin {
	configHandler config;
	eventHandler event;
	@Override
	public void onEnable(){
		configHandler config = new configHandler(this);
		eventHandler event = new eventHandler(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("start") && sender.isOp()) {
			
		}
		else {
			if(label.equalsIgnoreCase("team") && args.length > 0) {
				
			}
			else if (label.equalsIgnoreCase("class") && args.length > 0) {
				if(args[0].equalsIgnoreCase("compress")){
					event.playerToAbilityHashMap.replace((Player) sender,compress.class);
					return true;
				}
				else if(args[0].equalsIgnoreCase("collector")){
					event.playerToAbilityHashMap.replace((Player) sender,collector.class);
					return true;
				}
			}
		}
		return false;
	}
}
