package me.boboclass;

import org.bukkit.entity.Player;

public class playerClass {
    Player playerObject;
    int delay = 0;
    compress abilityClass;
    boolean hasUltimate= true;
    boolean isCompressed = false;

    protected void normal(){
        playerObject.sendMessage("Normal Used");
        this.delay = 0;
    }

    protected void ultimate(){
        playerObject.sendMessage("Ultimate Used");
        this.hasUltimate = false;
    }
}
