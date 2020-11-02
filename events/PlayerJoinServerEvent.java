package me.obby.rocketspleef.events;

import me.obby.rocketspleef.Rocketspleef;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinServerEvent implements Listener {

    private Rocketspleef plugin;
    public PlayerJoinServerEvent(Rocketspleef plugin) {this.plugin = plugin;}



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (plugin.checkGamestate() == true) {
            plugin.kill(p);
            p.sendMessage(ChatColor.LIGHT_PURPLE +"[RS] " + ChatColor.WHITE + "Game has started already, join in the next round");
        } else {
            p.setTotalExperience(0);
            p.setGameMode(GameMode.ADVENTURE);
            p.setFlying(true);
            p.setHealth(20);
            p.getInventory().clear();
        }


    }
}
