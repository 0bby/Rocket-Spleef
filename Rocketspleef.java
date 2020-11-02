package me.obby.rocketspleef;

import me.obby.rocketspleef.commands.SetSpawn;
import me.obby.rocketspleef.commands.StartGame;
import me.obby.rocketspleef.events.PlayerFallEvent;
import me.obby.rocketspleef.events.PlayerHitEvent;
import me.obby.rocketspleef.events.PlayerJoinServerEvent;
import me.obby.rocketspleef.events.RocketLauncherEvent;
import me.obby.rocketspleef.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new RocketLauncherEvent(this), this);
        pm.registerEvents(new PlayerFallEvent(this), this);
        pm.registerEvents(new PlayerHitEvent(this), this);
        pm.registerEvents(new PlayerJoinServerEvent(this), this);
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

    //ammohashmap
    private HashMap<Player, Integer> ammo = new HashMap<Player, Integer>();
    public HashMap<Player, Integer> getAmmo() {
        return ammo;
    }

    private static int maxammo = 3;
    public int getMaxammo() {
        return maxammo;
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

    private Location deathaxis;
    public Location getDeathaxis() {
        return deathaxis;
    }
    public Location setDeathaxis(Location location) {
        deathaxis = location;
        return deathaxis;
    }

    //lastdamaged
    private HashMap<Player, Player> attackhistory = new HashMap<Player, Player>();
    public HashMap<Player, Player> getAttackhistory() {
        return attackhistory;
    }

    //set rocket launcher cd
    private static int attackrate = 2;
    public int getAttackrate() {
        return attackrate;
    }


    //"kill" player
    public void kill(Player p) {
        if(this.getAlive().contains(p)){
            this.getAlive().remove(p);
            p.setGameMode(GameMode.SPECTATOR);
            p.setGlowing(false);
            if (this.getAttackhistory().containsKey(p)){
                Bukkit.broadcastMessage(ChatColor.RED  + p.getDisplayName() + ChatColor.WHITE + " has been eliminated by " + ChatColor.DARK_RED + this.getAttackhistory().get(p.getPlayer()).getDisplayName());
            } else {
                Bukkit.broadcastMessage(ChatColor.RED + p.getDisplayName() + ChatColor.WHITE  +" jumped off lmao");
            }
        } else {
            p.setGameMode(GameMode.SPECTATOR);
            p.setGlowing(false);
        }

        // check how many players left when someone dies
        if(this.getAlive().size() == 1){
            Player winner = this.getAlive().get(0);
            Bukkit.broadcastMessage(ChatColor.GOLD + winner.getDisplayName() + ChatColor.WHITE + " has won the game");
            winner.setGameMode(GameMode.SPECTATOR);
            winner.setGlowing(false);
        }
    }

    //is game going on
    private static boolean gamestate = false;
    //check when join
    public Boolean checkGamestate() {
        if(this.getAlive().size() == 0){
            setGamestate(false);
        } else {
            setGamestate(true);
        }
        return gamestate;
    }
    public void setGamestate(boolean bool) {
        gamestate = bool;
    }
    //more efficient but doesnt check but doesnt have to
    public boolean getGamestate() {
        return gamestate;
    }



    private HashMap<Player, Boolean> reloading = new HashMap<Player, Boolean>();
    public HashMap<Player, Boolean> getReloading() {
        return reloading;
    }




}
