package me.bobomcc;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class scoreboardManager {
    Scoreboard playerScoreboard;
    Objective playerObjective;
    Player player;
    public scoreboardManager(Player p){
        this.player = p;
        resetScoreboard();
    }
    protected void update(int timeLeft, boolean isUltimateAvailable, String abilityName, String teamName){
        resetScoreboard();
        if(timeLeft < 0) timeLeft = 0;
        playerObjective.getScore("Activation Cooldown: "+ timeLeft).setScore(10);
        playerObjective.getScore("Ultimate: " + isUltimateAvailable).setScore(20);
        playerObjective.getScore("Ability: " + abilityName).setScore(0);
        playerObjective.getScore("Team: " + teamName).setScore(5);
        player.setScoreboard(playerScoreboard);
    }

    private void resetScoreboard(){
        playerScoreboard = player.getServer().getScoreboardManager().getNewScoreboard();
        playerObjective = playerScoreboard.registerNewObjective("test", "dummy");
        playerObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        playerObjective.setDisplayName("Cooldown");
    }
}
