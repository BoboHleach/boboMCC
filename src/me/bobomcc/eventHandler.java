package me.bobomcc;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
		if(playerToAbilityHashMap.get(e.getPlayer()).abilityCooldown > 0)return;
		else {
			if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(playerToAbilityHashMap.get(e.getPlayer()).name.equalsIgnoreCase( "collector")){
					playerToAbilityHashMap.get(e.getPlayer()).collectorNormal();
				}
				else if(playerToAbilityHashMap.get(e.getPlayer()).name.equalsIgnoreCase("warp")){
					playerToAbilityHashMap.get(e.getPlayer()).warpNormal();
				}
			}
			else if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
				if(playerToAbilityHashMap.get(e.getPlayer()).name.equalsIgnoreCase( "collector")){
					playerToAbilityHashMap.get(e.getPlayer()).collectorUltimate();
				}
			}
		}
	}
	@EventHandler
	protected  void EntityDamageByEntityEvent(EntityDamageByEntityEvent e){
		if(e.getEntity().getType() != EntityType.PLAYER || e.getDamager().getType() != EntityType.PLAYER)return;
		if(playerToAbilityHashMap.get((Player) e).abilityCooldown > 0) return;
		if(playerToAbilityHashMap.get(e).name.equalsIgnoreCase( "ultimate") && playerToAbilityHashMap.get(e).hasUltimate){
			playerToAbilityHashMap.get(e).warpUltimate(((Player) e), playerToAbilityHashMap);
		}
		else if(playerToAbilityHashMap.get(e).name.equalsIgnoreCase("copy")){
			playerToAbilityHashMap.get(e).copyNormal(((Player) e),playerToAbilityHashMap);
		}
		else if(playerToAbilityHashMap.get(e).name.equalsIgnoreCase("compress")){
			playerToAbilityHashMap.get(e).compressNormal((Player ) e, playerToAbilityHashMap);
		}
	}
}
