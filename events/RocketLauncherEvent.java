package me.obby.rocketspleef.events;

import me.obby.rocketspleef.Rocketspleef;
import me.obby.rocketspleef.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.text.DecimalFormat;


public class RocketLauncherEvent implements Listener {

    private Rocketspleef plugin;
    public RocketLauncherEvent(Rocketspleef plugin) {this.plugin = plugin;}

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getItem() != null) {
                if (e.getItem().getItemMeta().equals(ItemManager.rocketlauncher.getItemMeta())) {
                    Player p = e.getPlayer();
                    if (!(plugin.getCooldown().containsKey(p))) {
                        plugin.getCooldown().put(p, System.currentTimeMillis());
                    }
                    Long timer = (Long) plugin.getCooldown().get(p);
                    Long currenttime = System.currentTimeMillis();
                    if (System.currentTimeMillis() >= timer + plugin.getAttackrate()) {
                        //fireball
                        Location eye = p.getEyeLocation();
                        Location loc = eye.add(eye.getDirection().multiply(1.2));
                        Fireball fireball = (Fireball) loc.getWorld().spawnEntity(loc, EntityType.FIREBALL);
                        fireball.setVelocity(loc.getDirection().normalize().multiply(2));
                        fireball.setShooter(p);
                        plugin.getCooldown().put(p, System.currentTimeMillis());
                    } else {
                        DecimalFormat df = new DecimalFormat("#.#");
                        Long cdtime = (plugin.getAttackrate()-(currenttime-timer));
                        Float cdtimefloat = (cdtime.floatValue())/1000;
                        String cdtimefloatformat = df.format(cdtimefloat);
                        p.sendMessage(ChatColor.LIGHT_PURPLE + "[RS] "+   ChatColor.GOLD + cdtimefloatformat + " seconds left");
                    }
                }
            }
        }
    }
}
