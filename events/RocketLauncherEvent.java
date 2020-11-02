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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class RocketLauncherEvent implements Listener {

    private Rocketspleef plugin;
    public RocketLauncherEvent(Rocketspleef plugin) {this.plugin = plugin;}

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (plugin.getGamestate() == true) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (e.getItem() != null) {
                    if (e.getItem().getItemMeta().equals(ItemManager.rocketlauncher.getItemMeta())) {
                        Player p = e.getPlayer();
                        if(plugin.getReloading().get(p) == false){
                            plugin.getReloading().put(p, true);
                            new BukkitRunnable() {
                                public void run() {
                                    if(!(plugin.getAmmo().get(p).equals(plugin.getMaxammo()))) {
                                        plugin.getAmmo().put(p, plugin.getAmmo().get(p)+1);
                                        p.setLevel(plugin.getAmmo().get(p));
                                    } else {
                                        this.cancel();
                                        plugin.getReloading().put(p, false);
                                    }

                                }
                            }.runTaskTimer(plugin, 20*(plugin.getAttackrate()), 20*(plugin.getAttackrate()));
                        }
                        //shoot if they have ammo,
                        if (plugin.getAmmo().get(p) > 0) {
                            shootFireball(p);
                            plugin.getAmmo().put(p, plugin.getAmmo().get(p)-1);
                            p.setLevel(plugin.getAmmo().get(p));
                        } else {
                            p.sendMessage(ChatColor.LIGHT_PURPLE + "[RS] " + ChatColor.WHITE + "Reloading...");
                        }
                    }
                }
            }
        }
    }

    public void shootFireball(Player p) {
        //fireball
        Location eye = p.getEyeLocation();
        Location loc = eye.add(eye.getDirection().multiply(1.2));
        Fireball fireball = (Fireball) loc.getWorld().spawnEntity(loc, EntityType.FIREBALL);
        fireball.setVelocity(loc.getDirection().normalize().multiply(2));
        fireball.setShooter(p);
        fireball.setIsIncendiary(false);
    }
}
