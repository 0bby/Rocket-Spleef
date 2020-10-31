package me.obby.rocketspleef;

import me.obby.rocketspleef.commands.RocketCD;
import me.obby.rocketspleef.commands.SetSpawn;
import me.obby.rocketspleef.commands.StartGame;
import me.obby.rocketspleef.events.PlayerFallEvent;
import me.obby.rocketspleef.events.PlayerHitEvent;
import me.obby.rocketspleef.events.RocketLauncherEvent;
import me.obby.rocketspleef.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Rocketspleef extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ItemManager.init();
        getCommand("start").setExecutor(new StartGame(this));
        getCommand("arena").setExecutor(new SetSpawn(this));
        getCommand("attackrate").setExecutor(new RocketCD(this));
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new RocketLauncherEvent(this), this);
        pm.registerEvents(new PlayerFallEvent(this), this);
        pm.registerEvents(new PlayerHitEvent(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //cooldown hashmap
    private HashMap<Player, Long> cooldown = new HashMap<Player, Long>();
    public HashMap<Player, Long> getCooldown() {
        return cooldown;
    }

    //alive dead status
    private List<Player> alive = new ArrayList<Player>();
    public void everyoneAlive() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            alive.add(p);
            p.setGameMode(GameMode.ADVENTURE);
        }
    }
    public List<Player> getAlive() {
        return alive;
    }

    //spawn
    private Location spawn;
    public Location getSpawn() {
        return spawn;
    }
    public Location setSpawn(Location location) {
        spawn = location;
        return spawn;
    }

    //lastdamaged
    private HashMap<Player, Player> attackhistory = new HashMap<Player, Player>();
    public HashMap<Player, Player> getAttackhistory() {
        return attackhistory;
    }


    //set rocket launcher cd
    private Integer attackrate = 1000;
    public Integer setAttackrate(Integer integer) {
        attackrate = integer;
        return attackrate;
    }
    public Integer getAttackrate() {
        return attackrate;
    }



}
