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
    public PlayerFallEvent(Rocketspleef plugin) {this.plugin = plugin;}

    @EventHandler

    public void onCustomDeathEvent(PlayerMoveEvent e) {
        if(plugin.getGamestate() == true) {
            Player p = e.getPlayer();
            if(plugin.getAlive().contains(p)){
                if(p.getLocation().getY() <= plugin.getDeathaxis().getY()) {
                    plugin.kill(p);
                }
            }
        }
    }
}
