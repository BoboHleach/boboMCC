package me.bobomcc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.HashMap;
import me.boboclass.*;

public class eventHandler implements Listener {
	private main plugin;
	HashMap<Player,Class> playerToAbilityHashMap = new HashMap<>();
	public eventHandler(main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	protected void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getPlayer().getItemInHand().getType() != Material.STICK)return;
		else {

		}
	}

	protected void onJoin(PlayerJoinEvent e){
		playerToAbilityHashMap.put(e.getPlayer(), playerClass.class);
	}
}
