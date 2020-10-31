package me.obby.rocketspleef.events;

import me.obby.rocketspleef.Rocketspleef;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;


public class PlayerFallEvent implements Listener {

    private Rocketspleef plugin;
    public PlayerFallEvent(Rocketspleef plugin) {this.plugin= plugin;}

    @EventHandler

    public void onCustomDeathEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(plugin.getAlive().contains(p)){
            if(p.getLocation().getY() <= 90) {
                plugin.getAlive().remove(p);
                p.setGameMode(GameMode.SPECTATOR);
                if (plugin.getAttackhistory().containsKey(p)){
                    Bukkit.broadcastMessage(ChatColor.RED  + p.getDisplayName() + ChatColor.WHITE + " has been eliminated by " + ChatColor.DARK_RED + plugin.getAttackhistory().get(p.getPlayer()).getDisplayName());
                } else {
                    Bukkit.broadcastMessage(ChatColor.RED + p.getDisplayName() + ChatColor.WHITE  +" jumped off lmao");
                }
                if(plugin.getAlive().size() <= 1){
                    Player winner = plugin.getAlive().get(0);
                    Bukkit.broadcastMessage(ChatColor.GOLD + winner.getDisplayName() + ChatColor.WHITE + " has won the game");
                }
            }
        }

    }
}
