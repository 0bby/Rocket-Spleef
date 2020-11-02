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
                        //shoot if they have ammo,
                        if (plugin.getAmmo().get(p) > 0) {
                            shootFireball(p);
                            //if they had full ammo reload and run reload shit
                            if(plugin.getAmmo().get(p) == plugin.getMaxammo()){
                                plugin.getAmmo().put(p, plugin.getAmmo().get(p)-1);
                                new BukkitRunnable() {
                                    public void run() {
                                        plugin.getAmmo().put(p, plugin.getAmmo().get(p)+1);
                                        p.setExp(plugin.getXpdictionary().get(plugin.getAmmo().get(p)));
                                        if(plugin.getAmmo().get(p).equals(plugin.getMaxammo())) {
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(plugin, 20*(plugin.getAttackrate()/1000), 20*(plugin.getAttackrate()/1000));
                            } else {
                                //else let the thing do its job
                                plugin.getAmmo().put(p, plugin.getAmmo().get(p)-1);
                            }
                            //no ammo sadge
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
    }
}
