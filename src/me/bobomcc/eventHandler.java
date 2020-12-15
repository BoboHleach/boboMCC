package me.bobomcc;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.HashMap;

public class eventHandler implements Listener {
	private final main plugin;
	HashMap<Player, playerClass> playerToAbilityHashMap = new HashMap<>();
	public eventHandler(main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	protected void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getPlayer().getItemInHand().getType() != Material.STICK)return;
		else {
			playerToAbilityHashMap.get(e.getPlayer()).run(e.getAction(),playerToAbilityHashMap,null);
		}
	}

	protected  void onPlayerHit(EntityDamageByEntityEvent e){
		if(e.getEntity().getType() == EntityType.PLAYER){
			playerToAbilityHashMap.get(e.getDamager()).run(null,playerToAbilityHashMap,(Player) e.getEntity());
		}
	}
	protected void onJoin(PlayerJoinEvent e){
		playerToAbilityHashMap.put(e.getPlayer(), new playerClass());
	}
}
