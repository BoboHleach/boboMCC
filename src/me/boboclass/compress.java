package me.boboclass;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class compress extends playerClass{
    protected compress(){
        this.name = "compress";
    }

    public void normal(Player attackedPlayer, Plugin p){
        compressTeleportEvent(attackedPlayer, p, 300);
    }

    public void ultimate(Player attackedPlayer, Plugin p){
        compressTeleportEvent(attackedPlayer,p,72000);
    }

    private void compressTeleportEvent(Player attackedPlayer, Plugin p, int timerDelay){
        attackedPlayer.sendMessage("You have been Compressed, Releasing in 15 seconds");
        isCompressed = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                isCompressed = false;
            }
        }.runTaskLater(p,timerDelay);
    }
}
