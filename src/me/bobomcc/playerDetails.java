package me.bobomcc;

import org.bukkit.entity.Player;

public class playerDetails {
	protected abilityHandler ability;
	protected boolean dead;
	protected int delay_period;
	protected Player playerObject;
	private main plugin;
	
	public playerDetails(main plugin, Player playerObject) {
		this.plugin = plugin;
		this.playerObject = playerObject;
	}
	
	protected boolean setDetails(Player p, abilityHandler ability){
		try {
			this.playerObject = p;
			this.ability = ability;
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
