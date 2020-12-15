package me.bobomcc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class  main extends JavaPlugin {
	eventHandler event;

	@Override
	public void onEnable(){
		eventHandler event = new eventHandler(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("start") && sender.isOp()) {
			
		}
		else {
			if(label.equalsIgnoreCase("team") && args.length > 0) {
				
			}
			else if (label.equalsIgnoreCase("class") && args.length > 0) {
				event.playerToAbilityHashMap.get((Player) sender).name = args[0];
				return true;
			}
		}
		return false;
	}
}
