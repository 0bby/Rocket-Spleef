package me.obby.rocketspleef.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.data.type.Fire;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack rocketlauncher;
    public static ItemStack firework;
    public static ItemStack elytra;

    public static void init() {
        createRocketLauncher();
        createFirework();
        createElytra();
    }

    private static void createRocketLauncher() {
        ItemStack item = new ItemStack(Material.IRON_SHOVEL);
        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
        //item naming and check for special shovel
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.LIGHT_PURPLE + "Right click to shoot, doofus");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.GOLD + "Rocket Launcher");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        rocketlauncher = item;
    }

    private static void createFirework() {
        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET);
        //item naming and check for special shovel
        FireworkMeta meta = (FireworkMeta) item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.LIGHT_PURPLE + "To infinity and beyond!");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.GOLD + "Firework");
        meta.setPower(3);
        item.setItemMeta(meta);
        firework = item;
    }

    private static void createElytra() {
        ItemStack item = new ItemStack(Material.ELYTRA);
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        item.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
        //item naming and check for special shovel
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.LIGHT_PURPLE + "Strapped to ya back");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        elytra = item;
    }



}
