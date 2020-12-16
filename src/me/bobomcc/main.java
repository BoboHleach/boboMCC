package me.bobomcc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class  main extends JavaPlugin {
	private eventHandler event;
	protected boolean hasStarted = false;
	private worldBorder worldBorderHandler;
	protected boolean isInGracePeriod = true;
	@Override
	public void onEnable(){
		this.event = new eventHandler(this);
		this.getServer().getPluginManager().registerEvents(event,this);
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
				if (label.equalsIgnoreCase("team") && args.length > 0) {
					event.playerTeamHashMap.replace((Player) sender,args[0]);
					return true;
				} else if (label.equalsIgnoreCase("class") && args.length > 0) {
					event.playerToAbilityHashMap.get(sender).name = args[0];
					sender.sendMessage("You Have Selected: " + args[0]);
					return true;
				}
		}
		return false;
	}
}
