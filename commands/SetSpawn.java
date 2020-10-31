package me.obby.rocketspleef.commands;

import me.obby.rocketspleef.Rocketspleef;
import me.obby.rocketspleef.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SetSpawn implements CommandExecutor {
    private Rocketspleef plugin;
    public SetSpawn(Rocketspleef plugin) {this.plugin= plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.isOp()){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[RS] " + ChatColor.WHITE + "Spawn has been set");
            Location location = ((Player) sender).getLocation();
            plugin.setSpawn(location);
        }
        return true;
    }
}
