package me.bobomcc;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class eventHandler implements Listener {
	private main plugin;
	public eventHandler(main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	protected void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getPlayer().getItemInHand().getType() != Material.STICK)return;
		else {
			// Interact Code
		}
	}
}
