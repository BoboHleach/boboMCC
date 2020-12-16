package me.bobomcc;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class eventHandler implements Listener {
	protected main plugin;
	public HashMap<Player, playerClass> playerToAbilityHashMap = new HashMap<>();
	public HashMap<Player, String> playerTeamHashMap = new HashMap<>();
	public eventHandler(main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	protected void PlayerInteractEvent(PlayerInteractEvent e) {
		if(!plugin.hasStarted)e.setCancelled(true);
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
	protected void EntityDamageByEntityEvent(EntityDamageByEntityEvent e){
		if(!plugin.hasStarted || plugin.isInGracePeriod){
			e.setCancelled(true);
			return;
		}
		else{
		Player damager = (Player) e.getDamager();
		Player receiver = (Player) e.getEntity();
		if(playerToAbilityHashMap.get(damager).abilityCooldown > 0 || playerTeamHashMap.get(receiver) == playerTeamHashMap.get(damager) || damager.getInventory().getItemInHand().getType() != Material.STICK)return;
		else {
			if (playerToAbilityHashMap.get(damager).name.equalsIgnoreCase("warp") && playerToAbilityHashMap.get(damager).hasUltimate) {
				playerToAbilityHashMap.get(damager).warpUltimate(receiver, damager, playerToAbilityHashMap);
			} else if (playerToAbilityHashMap.get(damager).name.equalsIgnoreCase("copy")) {
				playerToAbilityHashMap.get(damager).copyNormal(receiver, playerToAbilityHashMap);
			} else if (playerToAbilityHashMap.get(damager).name.equalsIgnoreCase("compress")) {
				playerToAbilityHashMap.get(damager).compressNormal(receiver, playerToAbilityHashMap);
			}
		}
		}
	}
}
