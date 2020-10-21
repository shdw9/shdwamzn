package me.shdwslyr.amzn;

import java.io.File;
import java.lang.reflect.Field;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import me.shdwslyr.amzn.command.SmiCommand;
import me.shdwslyr.amzn.events.InventoryClickEvents;
import me.shdwslyr.amzn.events.PlayerJoinEvents;
import me.shdwslyr.amzn.events.RestoreItemsEvent;
import me.shdwslyr.amzn.events.StoreItemsEvent;
import me.shdwslyr.amzn.inventory.resources.Text;
import me.shdwslyr.amzn.util.Enchanter;
import me.shdwslyr.amzn.util.InventoryManager;

public class Main extends JavaPlugin {
  SmiCommand smiCommand = new SmiCommand();
  
  private static Main plugin;
  
  File storeFiel;
  
  FileConfiguration storeConfig;
  
  public void onEnable() {
    plugin = this;
    getCommand(this.smiCommand.command).setExecutor((CommandExecutor)new SmiCommand());
    registerEvents();
    registerEnchantment();
    InventoryManager.assingInventoriesOnReload();
    createFolder();
    createConfig();
    registerConfig();
    InventoryManager manager = new InventoryManager();
    manager.restoreStorage();
  }
  
  public void onDisable() {
    InventoryManager manager = new InventoryManager();
    manager.serializeStorage();
    plugin = null;
  }
  
  public static Main getMainInstance() {
    return plugin;
  }
  
  private void registerEvents() {
    Bukkit.getPluginManager().registerEvents((Listener)new StoreItemsEvent(), (Plugin)this);
    Bukkit.getPluginManager().registerEvents((Listener)new RestoreItemsEvent(), (Plugin)this);
    Bukkit.getPluginManager().registerEvents((Listener)new PlayerJoinEvents(), (Plugin)this);
    Bukkit.getPluginManager().registerEvents((Listener)new InventoryClickEvents(), (Plugin)this);
  }
  
  private void registerEnchantment() {
    Enchanter enchanter = new Enchanter(Text.enchanter_namespace);
    try {
      Field f = Enchantment.class.getDeclaredField("acceptingNew");
      f.setAccessible(true);
      f.set((Object)null, Boolean.valueOf(true));
      Enchantment.registerEnchantment((Enchantment)enchanter);
    } catch (Exception exception) {}
  }
  
  private void createConfig() {
    this.storeFiel = new File("plugins/shdwamzn/mails.yml");
    if (!this.storeFiel.exists())
      try {
        this.storeFiel.createNewFile();
      } catch (Exception e) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "mails.yml " + ChatColor.RED + "couldn't be created , try restarting the server");
      }  
    this.storeConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.storeFiel);
    this.storeConfig.options().copyDefaults(true);
    saveMailConfig();
  }
  
  public void saveMailConfig() {
    try {
      this.storeConfig.save(this.storeFiel);
    } catch (Exception e) {
      Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "mails.yml " + ChatColor.RED + "couldn't be saved , try again");
      System.out.println(e.getLocalizedMessage());
      System.out.println(e.getMessage());
      try {
        this.storeConfig.save(this.storeFiel);
      } catch (Exception e1) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "mails.yml " + ChatColor.RED + "couldn't be saved , for second time!!");
        System.out.println(e.getCause());
      } 
    } 
  }
  
  public YamlConfiguration getMailConfig() {
    return (YamlConfiguration)this.storeConfig;
  }
  
  public void createFolder() {
    File dir = new File("plugins/shdwamzn");
    if (!dir.exists())
      dir.mkdirs(); 
  }
  
  private void registerConfig() {
    FileConfiguration config = getConfig();
    config.options().copyDefaults(true);
    config.options().header("This file stores , all the settings for the players");
    config.options().copyHeader(true);
    saveConfig();
  }
}
