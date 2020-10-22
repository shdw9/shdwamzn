package me.shdwslyr.amzn.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import me.shdwslyr.amzn.Main;
import me.shdwslyr.amzn.inventory.MailStorage;
import me.shdwslyr.amzn.inventory.MailboxInventory;
import me.shdwslyr.amzn.inventory.SendMailInventory;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class InventoryManager {
  public static HashMap<Player, MailboxInventory> mailboxInv = new HashMap<>();
  
  public static HashMap<Player, SendMailInventory> sendMailInv = new HashMap<>();
  
  public static HashMap<UUID, MailStorage> mailStorage = new HashMap<>();
  
  private static Main plugin;
  
  public InventoryManager() {
    plugin = Main.getMainInstance();
  }
  
  public static void addPlayer(Player player) {
    if (!mailboxInv.containsKey(player))
      mailboxInv.put(player, new MailboxInventory()); 
    if (!sendMailInv.containsKey(player))
      sendMailInv.put(player, new SendMailInventory()); 
    if (!mailStorage.containsKey(player.getUniqueId())) {
      YamlConfiguration config = plugin.getMailConfig();
      if (config.contains("Storage." + player.getUniqueId())) {
        List<HashMap<String, List<ItemStack>>> list = (List<HashMap<String, List<ItemStack>>>)config.get("Storage." + player.getUniqueId());
        MailStorage storage = new MailStorage();
        storage.setStorage(list);
        mailStorage.put(player.getUniqueId(), storage);
      } else {
        mailStorage.put(player.getUniqueId(), new MailStorage());
      } 
    } 
  }
  
  public static void assingInventoriesOnReload() {
    Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
    for (Player p : players) {
      if (!mailboxInv.containsKey(p))
        mailboxInv.put(p, new MailboxInventory()); 
      if (!sendMailInv.containsKey(p))
        sendMailInv.put(p, new SendMailInventory()); 
    } 
  }
  
  public void serializeStorage() {
    for (Map.Entry<UUID, MailStorage> entry : mailStorage.entrySet()) {
      if (entry != null) {
        UUID playerUUID = entry.getKey();
        MailStorage storage = entry.getValue();
        YamlConfiguration config = plugin.getMailConfig();
        int nulls = 0;
        for (HashMap<String, List<ItemStack>> map : (Iterable<HashMap<String, List<ItemStack>>>)storage.getStorage()) {
          for (Map.Entry<String, List<ItemStack>> en : map.entrySet()) {
            if (en.getValue() == null)
              nulls++; 
          } 
        } 
        if (nulls == 0) {
          config.set("Storage." + playerUUID, storage.getStorage());
          plugin.saveMailConfig();
        } 
      } 
    } 
  }
  
  public void restoreStorage() {
    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)plugin, new Runnable() {
          public void run() {
            ConfigSettingSet configSet = new ConfigSettingSet();
            configSet.reloadSetUp();
            Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
            for (Player p : players) {
              if (!InventoryManager.mailStorage.containsKey(p.getUniqueId())) {
                YamlConfiguration config = InventoryManager.plugin.getMailConfig();
                if (config.contains("Storage." + p.getUniqueId())) {
                  List<HashMap<String, List<ItemStack>>> list = (List<HashMap<String, List<ItemStack>>>)config.get("Storage." + p.getUniqueId());
                  MailStorage storage = new MailStorage();
                  storage.setStorage(list);
                  InventoryManager.mailStorage.put(p.getUniqueId(), storage);
                  continue;
                } 
                InventoryManager.mailStorage.put(p.getUniqueId(), new MailStorage());
              } 
            } 
          }
        });
  }
}
