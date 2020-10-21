package me.shdwslyr.amzn.inventory;

import java.util.ArrayList;
import java.util.List;
import me.shdwslyr.amzn.inventory.resources.Items;
import me.shdwslyr.amzn.inventory.resources.Text;
import me.shdwslyr.amzn.util.ConfigSettingSet;
import me.shdwslyr.amzn.util.InventoryManager;
import me.shdwslyr.amzn.util.SoundSystem;
import me.shdwslyr.amzn.util.Ssound;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SendMailInventory extends MainInventory {
  public SendMailInventory() {
    super(Text.SENDMAILINVENTORY_NAME, 9);
  }
  
  public void openInventory(Player player) {
    super.openInventory(player);
    int[] slot = new int[1];
    ItemStack[] item = { Items.sendMailItemButton() };
    setButton(slot, item, this.inv);
  }
  
  public void sendItems(Player player, Player target, Inventory inv) {
    ConfigSettingSet soundConfig = new ConfigSettingSet(player);
    if (isInventoryEmpty(1, inv)) {
      player.sendMessage(Text.SEND_MAIL_EMPTY);
      if (soundConfig.isSoundEnabled(2)) {
        SoundSystem soundSystem = new SoundSystem(Ssound.EMPTY, player); 
      }
    } else {
      ItemStack[] items = inv.getContents();
      List<ItemStack> itemsToSend = new ArrayList<>();
      for (int i = 1; i < items.length; i++) {
        if (items[i] != null) {
          itemsToSend.add(items[i]);
          inv.remove(items[i]);
        } 
      } 
      MailStorage storage = (MailStorage)InventoryManager.mailStorage.get(target.getUniqueId());
      storage.addToMailStorage(player, itemsToSend);
      ConfigSettingSet config = new ConfigSettingSet(target);
      if (config.isNotificationsEnabled() || config.isPlayerOnFav(player)) {
    	  target.sendMessage(String.valueOf(String.valueOf(Text.prefix)) + ChatColor.GOLD + player.getName() + ChatColor.DARK_GRAY + " has sent you a package! Type " + ChatColor.GOLD + "/amzn open" + ChatColor.DARK_GRAY + " to open!"); 
        if (soundConfig.isSoundEnabled(3)) {
          SoundSystem soundSystem = new SoundSystem(Ssound.RECIVED, player); 
        }
      } 
      if (soundConfig.isSoundEnabled(1)) {
        SoundSystem soundSystem = new SoundSystem(Ssound.SENT, player); 
      }
    } 
  }
}
