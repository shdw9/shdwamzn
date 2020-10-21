package me.shdwslyr.amzn.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import me.shdwslyr.amzn.Main;
import me.shdwslyr.amzn.inventory.resources.Text;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConfigSettingSet {
  Main plugin;
  
  Player player;
  
  public ConfigSettingSet(Player player) {
    this.plugin = Main.getMainInstance();
    this.player = player;
  }
  
  public ConfigSettingSet() {
    this.plugin = Main.getMainInstance();
  }
  
  public void firstSetup(Player player) {
    FileConfiguration config = this.plugin.getConfig();
    if (!config.contains("Settings." + player.getUniqueId())) {
      List<String> filter = new ArrayList<>();
      config.set("Settings." + player.getUniqueId() + ".name", player.getName());
      config.set("Settings." + player.getUniqueId() + ".notifications", Boolean.valueOf(true));
      config.set("Settings." + player.getUniqueId() + ".notifications_filter", filter);
      config.set("Settings." + player.getUniqueId() + ".sound.sent", Boolean.valueOf(true));
      config.set("Settings." + player.getUniqueId() + ".sound.empty", Boolean.valueOf(true));
      config.set("Settings." + player.getUniqueId() + ".sound.recived", Boolean.valueOf(true));
      config.set("Settings." + player.getUniqueId() + ".sound.open_mail", Boolean.valueOf(true));
      this.plugin.saveConfig();
    } 
  }
  
  public void reloadSetUp() {
    Collection<? extends Player> players = Bukkit.getOnlinePlayers();
    for (Player p : players)
      firstSetup(p); 
  }
  
  public void setNotifications() {
    FileConfiguration config = this.plugin.getConfig();
    boolean not = config.getBoolean("Settings." + this.player.getUniqueId() + ".notifications");
    if (not) {
      config.set("Settings." + this.player.getUniqueId() + ".notifications", Boolean.valueOf(false));
    } else {
      config.set("Settings." + this.player.getUniqueId() + ".notifications", Boolean.valueOf(true));
    } 
    this.plugin.saveConfig();
  }
  
  public String getNotifications() {
    FileConfiguration config = this.plugin.getConfig();
    boolean isEnabled = config.getBoolean("Settings." + this.player.getUniqueId() + ".notifications");
    if (isEnabled)
      return ChatColor.AQUA + "YES"; 
    return ChatColor.RED + "NO";
  }
  
  public String getSentSound() {
    FileConfiguration config = this.plugin.getConfig();
    boolean isEnabled = config.getBoolean("Settings." + this.player.getUniqueId() + ".sound.sent");
    if (isEnabled)
      return ChatColor.AQUA + "YES"; 
    return ChatColor.RED + "NO";
  }
  
  public String getOpenSound() {
    FileConfiguration config = this.plugin.getConfig();
    boolean isEnabled = config.getBoolean("Settings." + this.player.getUniqueId() + ".sound.open_mail");
    if (isEnabled)
      return ChatColor.AQUA + "YES"; 
    return ChatColor.RED + "NO";
  }
  
  public String getEmptySound() {
    FileConfiguration config = this.plugin.getConfig();
    boolean isEnabled = config.getBoolean("Settings." + this.player.getUniqueId() + ".sound.empty");
    if (isEnabled)
      return ChatColor.AQUA + "YES"; 
    return ChatColor.RED + "NO";
  }
  
  public String getRecivedSound() {
    FileConfiguration config = this.plugin.getConfig();
    boolean isEnabled = config.getBoolean("Settings." + this.player.getUniqueId() + ".sound.recived");
    if (isEnabled)
      return ChatColor.AQUA + "YES"; 
    return ChatColor.RED + "NO";
  }
  
  public void setSound(int row) {
    String yml;
    FileConfiguration config = this.plugin.getConfig();
    switch (row) {
      case 1:
        yml = "sent";
        break;
      case 3:
        yml = "empty";
        break;
      case 5:
        yml = "recived";
        break;
      case 7:
        yml = "open_mail";
        break;
      default:
        return;
    } 
    boolean not = config.getBoolean("Settings." + this.player.getUniqueId() + ".sound." + yml);
    if (not) {
      config.set("Settings." + this.player.getUniqueId() + ".sound." + yml, Boolean.valueOf(false));
    } else {
      config.set("Settings." + this.player.getUniqueId() + ".sound." + yml, Boolean.valueOf(true));
    } 
    this.plugin.saveConfig();
  }
  
  public boolean isSoundEnabled(int option) {
    FileConfiguration config = this.plugin.getConfig();
    switch (option) {
      case 1:
        return config.getBoolean("Settings." + this.player.getUniqueId() + ".sound.sent");
      case 2:
        return config.getBoolean("Settings." + this.player.getUniqueId() + ".sound.empty");
      case 3:
        return config.getBoolean("Settings." + this.player.getUniqueId() + ".sound.recived");
      case 4:
        return config.getBoolean("Settings." + this.player.getUniqueId() + ".sound.open_mail");
    } 
    return false;
  }
  
  public boolean isNotificationsEnabled() {
    return this.plugin.getConfig().getBoolean("Settings." + this.player.getUniqueId() + ".notifications");
  }
  
  public void addPlayerToFavorites(Player target) {
    FileConfiguration config = this.plugin.getConfig();
    List<String> names = (List<String>) config.getList("Settings." + this.player.getUniqueId() + ".notifications_filter");
    if (names.contains(target.getName())) {
      this.player.sendMessage(String.valueOf(Text.prefix) + ChatColor.RED + "The player is already on favourites");
    } else {
      names.add(target.getName());
      config.set("Settings." + this.player.getUniqueId() + ".notifications_filter", names);
      this.plugin.saveConfig();
      this.player.sendMessage(String.valueOf(Text.prefix) + ChatColor.AQUA + target.getName() + ChatColor.GREEN + " added to favourites");
    } 
  }
  
  public void removePlayerFromFavorites(String target) {
    FileConfiguration config = this.plugin.getConfig();
    List<String> names = (List<String>) config.getList("Settings." + this.player.getUniqueId() + ".notifications_filter");
    int indexToDelete = 0;
    if (names.contains(target)) {
      for (int i = 0; i < names.size(); i++) {
        if (((String)names.get(i)).equals(target)) {
          indexToDelete = i;
          break;
        } 
      } 
      names.remove(indexToDelete);
      config.set("Settings." + this.player.getUniqueId() + ".notifications_filter", names);
      this.plugin.saveConfig();
      this.player.sendMessage(String.valueOf(Text.prefix) + ChatColor.AQUA + target + ChatColor.GREEN + " player removed!");
    } else {
      this.player.sendMessage(String.valueOf(Text.prefix) + ChatColor.AQUA + target + ChatColor.RED + " is not in favorites");
    } 
  }
  
  public boolean isPlayerOnFav(Player target) {
    FileConfiguration config = this.plugin.getConfig();
    List<String> names = (List<String>) config.getList("Settings." + this.player.getUniqueId() + ".notifications_filter");
    return names.contains(target.getName());
  }
  
  public void getFavList() {
    FileConfiguration config = this.plugin.getConfig();
    List<String> names = (List<String>) config.getList("Settings." + this.player.getUniqueId() + ".notifications_filter");
    if (names.isEmpty()) {
      this.player.sendMessage(String.valueOf(Text.prefix) + ChatColor.RED + "You dont have any player added");
    } else {
      List<String> msg = new ArrayList<>();
      msg.add(ChatColor.GOLD + "------------------");
      for (String n : names)
        msg.add(" - " + ChatColor.AQUA + n); 
      msg.add(ChatColor.GOLD + "------------------");
      for (String s : msg)
        this.player.sendMessage(s); 
    } 
  }
}
