package me.bobomcc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class  main extends JavaPlugin {
	
	@Override 
	public void onEnable(){
		
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
				
			}
		}
		return false;
	}
}
