package me.shdwslyr.amzn.inventory.resources;

import java.util.List;
import me.shdwslyr.amzn.util.Enchanter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
  public static ItemStack sendMailItemButton() {
    Enchanter enchanter = new Enchanter(Text.enchanter_namespace);
    ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
    ItemMeta meta = item.getItemMeta();
    meta.addEnchant((Enchantment)enchanter, 0, true);
    meta.setDisplayName(ChatColor.GOLD + "SEND");
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack nextButton() {
    ItemStack item = new ItemStack(Material.GREEN_WOOL);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.GREEN + "NEXT");
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack notofications(String name) {
    Enchanter enchanter = new Enchanter(Text.enchanter_namespace);
    ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.GREEN + "NOTIFICATIONS: " + name);
    meta.addEnchant((Enchantment)enchanter, 0, true);
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack sound() {
    Enchanter enchanter = new Enchanter(Text.enchanter_namespace);
    ItemStack item = new ItemStack(Material.JUKEBOX);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.GREEN + "SOUND");
    meta.addEnchant((Enchantment)enchanter, 0, true);
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack soundSent(String name) {
    Enchanter enchanter = new Enchanter(Text.enchanter_namespace);
    ItemStack item = new ItemStack(Material.JUKEBOX);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.GREEN + "SENT: " + name);
    meta.addEnchant((Enchantment)enchanter, 0, true);
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack soundEmpty(String name) {
    Enchanter enchanter = new Enchanter(Text.enchanter_namespace);
    ItemStack item = new ItemStack(Material.JUKEBOX);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.GREEN + "EMPTY: " + name);
    meta.addEnchant((Enchantment)enchanter, 0, true);
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack soundRecived(String name) {
    Enchanter enchanter = new Enchanter(Text.enchanter_namespace);
    ItemStack item = new ItemStack(Material.JUKEBOX);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.GREEN + "RECIEVED: " + name);
    meta.addEnchant((Enchantment)enchanter, 0, true);
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack soundOpenMail(String name) {
    Enchanter enchanter = new Enchanter(Text.enchanter_namespace);
    ItemStack item = new ItemStack(Material.JUKEBOX);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.GREEN + "OPEN MAIL: " + name);
    meta.addEnchant((Enchantment)enchanter, 0, true);
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack previousButton() {
    ItemStack item = new ItemStack(Material.RED_WOOL);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(ChatColor.RED + "PREVIOUS");
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack fillers() {
    ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(" ");
    item.setItemMeta(meta);
    return item;
  }
  
  public static ItemStack createCrate(String name, List<String> lore, int id) {
    Enchanter enchanter = new Enchanter(Text.enchanter_namespace);
    ItemStack item = new ItemStack(Material.CHEST);
    ItemMeta meta = item.getItemMeta();
    meta.addEnchant((Enchantment)enchanter, 0, true);
    meta.setCustomModelData(Integer.valueOf(id));
    meta.setDisplayName(ChatColor.WHITE + "FROM: " + ChatColor.AQUA + name);
    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }
}