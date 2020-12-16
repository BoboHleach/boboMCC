package me.bobomcc;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class eventHandler implements Listener {
	protected main plugin;
	public HashMap<Player, playerClass> playerToAbilityHashMap = new HashMap<>();
	public eventHandler(main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	protected void PlayerInteractEvent(PlayerInteractEvent e) {
		if(e.getPlayer().getItemInHand().getType() != Material.STICK)return;
		else {
			playerToAbilityHashMap.get(e.getPlayer()).run(e.getAction(),playerToAbilityHashMap,null);
		}
	}
	@EventHandler
	protected  void EntityDamageByEntityEvent(EntityDamageByEntityEvent e){
		if(e.getEntity().getType() == EntityType.PLAYER){
			playerToAbilityHashMap.get(e.getDamager()).run(null,playerToAbilityHashMap,(Player) e.getEntity());
		}
	}



}
