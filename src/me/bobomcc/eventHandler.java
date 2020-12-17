package me.bobomcc;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class eventHandler implements Listener {
	protected main plugin;
	public HashMap<Player, playerClass> playerToAbilityHashMap = new HashMap<>();
	public HashMap<Player, String> playerTeamHashMap = new HashMap<>();
	public eventHandler(main plugin){this.plugin = plugin;}

	@EventHandler
	protected void PlayerInteractEvent(PlayerInteractEvent e) {
		if (!plugin.hasStarted) e.setCancelled(true);
		else if(e.getPlayer().getItemInHand().getType() != Material.STICK || playerToAbilityHashMap.get(e.getPlayer()).abilityCooldown > 0)return;
		else {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (playerToAbilityHashMap.get(e.getPlayer()).name.equalsIgnoreCase("collector")) {
					playerToAbilityHashMap.get(e.getPlayer()).collectorNormal();
				} else if (playerToAbilityHashMap.get(e.getPlayer()).name.equalsIgnoreCase("warp")) {
					playerToAbilityHashMap.get(e.getPlayer()).warpNormal();
				}
				else if(playerToAbilityHashMap.get(e.getPlayer()).name.equalsIgnoreCase("tank")){
					playerToAbilityHashMap.get(e.getPlayer()).tankToggleDamageHold();
				}
			}

			else if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (playerToAbilityHashMap.get(e.getPlayer()).name.equalsIgnoreCase("collector")) {
					playerToAbilityHashMap.get(e.getPlayer()).collectorUltimate();
				}
			}
		}
	}
	@EventHandler
	protected void EntityDamageByEntityEvent(EntityDamageByEntityEvent e){
		Player damager;
		Player receiver;
		try {
			damager = (Player) e.getDamager();
			receiver = (Player) e.getEntity();
		}
		catch (Exception exempt){
			return;
		}

		// Disables PvP Until Game has started and Grace Period is Over
		if(!plugin.hasStarted || plugin.isInGracePeriod || playerTeamHashMap.get(e.getDamager()).equalsIgnoreCase(playerTeamHashMap.get(e.getEntity())))e.setCancelled(true);
		else {
			if(damager.getInventory().getItemInHand().getType() != Material.STICK )return;


			if (playerToAbilityHashMap.get(receiver).name.equalsIgnoreCase("tank") && playerToAbilityHashMap.get(receiver).isHoldingDamage){
				playerToAbilityHashMap.get(receiver).holdingDamageAmount = (float) (playerToAbilityHashMap.get(receiver).holdingDamageAmount +e.getDamage());
			}
			if (playerToAbilityHashMap.get(damager).name.equalsIgnoreCase("warp") && playerToAbilityHashMap.get(damager).hasUltimate) {
				playerToAbilityHashMap.get(damager).warpUltimate(receiver, damager, playerToAbilityHashMap);
			} else if (playerToAbilityHashMap.get(damager).name.equalsIgnoreCase("copy")) {
				playerToAbilityHashMap.get(damager).copyNormal(receiver, playerToAbilityHashMap);
			} else if (playerToAbilityHashMap.get(damager).name.equalsIgnoreCase("compress")) {
				playerToAbilityHashMap.get(damager).compressNormal(receiver, playerToAbilityHashMap);
			}
			else if(playerToAbilityHashMap.get(damager).name.equalsIgnoreCase("tank") && !playerToAbilityHashMap.get(damager).isHoldingDamage && playerToAbilityHashMap.get(damager).holdingDamageAmount > 0){
					((Player) e.getEntity()).damage(playerToAbilityHashMap.get(damager).holdingDamageAmount);
					playerToAbilityHashMap.get(damager).holdingDamageAmount = 0;
			}
		}
	}

	@EventHandler
	protected void PlayerJoinEvent(PlayerJoinEvent e){
		playerToAbilityHashMap.putIfAbsent(e.getPlayer(), new playerClass(e.getPlayer(), plugin));
		playerTeamHashMap.putIfAbsent(e.getPlayer(), e.getPlayer().getDisplayName().toString());
		e.getPlayer().getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
	}

	@EventHandler
	protected void onPlayerDeath(PlayerDeathEvent e){
		e.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);
	}
}
