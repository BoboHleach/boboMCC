package me.bobomcc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

public class playerClass {

    protected main plugin;
    protected Player player;
    public String name = "default";
    protected float abilityCooldown, holdingDamageAmount = 0;
    protected boolean hasUltimate = true;
    protected boolean isCompress,isHoldingDamage = false;
    protected scoreboardManager playerScoreBoardClass;

    protected playerClass(Player playerObject, main mainPlugin) {
        player = playerObject;
        plugin = mainPlugin;
        playerScoreBoardClass = new scoreboardManager(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                playerScoreBoardClass.update((int) abilityCooldown, hasUltimate);
                abilityCooldown = abilityCooldown - 1;
            }
        }.runTaskTimer(plugin, 20, 20);
    }

    private void compressTeleportEvent(playerClass attackedPlayerClass, Player attackedPlayer, Plugin p, int timerDelay) {
        attackedPlayer.sendMessage("You have been Compressed, Releasing in 15 seconds");
        attackedPlayerClass.isCompress = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                attackedPlayerClass.isCompress = false;
            }
        }.runTaskLater(p, timerDelay);
    }

    private void getOreToPlayer(int min, int max) {
        World playerWorld = player.getWorld();
        for (int x = min; x < max; x++) {
            for (int y = min; y < max; y++) {
                for (int z = min; z < max; z++) {
                    Material blockType = playerWorld.getBlockAt((int) (player.getLocation().getX() + x), (int) (player.getLocation().getY() + y), (int) (player.getLocation().getZ() + z)).getType();
                    if (blockType == Material.IRON_ORE || blockType == Material.DIAMOND_ORE || blockType == Material.COAL_ORE || blockType == Material.GOLD_ORE) {
                        player.getInventory().addItem(new ItemStack(blockType, 1));
                        playerWorld.getBlockAt((int) (player.getLocation().getX() + x), (int) (player.getLocation().getY() + y), (int) (player.getLocation().getZ() + z)).setType(Material.AIR);
                    }
                }
            }
        }
    }

    private void playerTeleport(Player p, int min, int max) {
        Random rand = new Random();
        int xCoordinate = rand.nextInt(max + -min) - min;
        int zCoordinate = rand.nextInt(max + -min) - min;
        player.teleport(new Location(player.getWorld(), player.getLocation().getX() + xCoordinate, player.getWorld().getHighestBlockYAt((int) player.getLocation().getX() + xCoordinate, (int) player.getLocation().getZ() + zCoordinate) + 1, player.getLocation().getZ() + zCoordinate));
    }

    protected void copyNormal(Player attackedPlayer, HashMap<Player, playerClass>  playerToPlayerClass) {
            this.name = playerToPlayerClass.get(attackedPlayer).name;
            this.abilityCooldown = 30;
            new BukkitRunnable() {
                @Override
                public void run() {
                    name = "Copy";
                }
            }.runTaskLater(plugin, 600);
    }
    protected void compressNormal(Player attackedPlayer, HashMap<Player, playerClass> playerToPlayerClass){
        compressTeleportEvent(playerToPlayerClass.get(player), attackedPlayer, plugin, 600);
        this.abilityCooldown = 30;
    }

    protected void warpNormal(){
        playerTeleport(player, -10, 10);
        this.abilityCooldown = 30;
    }

    protected void collectorNormal() {
        getOreToPlayer(-5, 5);
        this.abilityCooldown = 30;
    }

    protected void collectorUltimate() {
        if(hasUltimate) {
            getOreToPlayer(-15, 15);
            this.hasUltimate = false;

        }
    }

    protected void warpUltimate(Player attackedPlayer, Player damager, HashMap<Player, playerClass>  playerToPlayerClass){
        if(hasUltimate) {
            attackedPlayer.teleport(attackedPlayer.getLocation().add(0, 25, 0));
            playerToPlayerClass.get(damager).hasUltimate = false;
        }
    }

    protected void tankToggleDamageHold(){
        isHoldingDamage = !isHoldingDamage;
    }
}

