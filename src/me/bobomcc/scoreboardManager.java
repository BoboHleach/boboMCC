package me.bobomcc;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class scoreboardManager {
    Scoreboard playerScoreboard;
    Objective playerObjective;
    Score timerScore, ultimateScore;
    Player player;
    public scoreboardManager(Player p){
        this.player = p;
        reset();
    }
    protected void update(int timeLeft, boolean isUltimateAvailable){
        reset();
        playerObjective.getScore("Timer: "+ timeLeft).setScore(0);
        playerObjective.getScore("Ultimate: " + isUltimateAvailable).setScore(1);
        player.setScoreboard(playerScoreboard);
    }

    private void reset(){
        playerScoreboard = player.getServer().getScoreboardManager().getNewScoreboard();
        playerObjective = playerScoreboard.registerNewObjective("test", "dummy");
        playerObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        playerObjective.setDisplayName("Timer");
        playerObjective.setDisplayName("Cooldown");
    }
}
