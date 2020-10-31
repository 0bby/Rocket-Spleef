package me.obby.rocketspleef.commands;

import me.obby.rocketspleef.Rocketspleef;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RocketCD implements CommandExecutor {
    private Rocketspleef plugin;
    public RocketCD(Rocketspleef plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.isOp() && args.length != 1){
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "[RS]" + ChatColor.WHITE + " you are dumb set a thing");
        } else {
            plugin.setAttackrate(Integer.parseInt(args[1]));
        }
        return true;
    }
}
