package me.shdwslyr.amzn.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import me.shdwslyr.amzn.inventory.MailStorage;
import me.shdwslyr.amzn.inventory.MailboxInventory;
import me.shdwslyr.amzn.inventory.resources.Items;
import me.shdwslyr.amzn.inventory.resources.Text;
import me.shdwslyr.amzn.util.SoundSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Pagination implements InventoryHolder {
  private MailboxInventory mailboxInventory;
  
  private MailStorage storage;
  
  private List<Inventory> invList = new ArrayList<>();
  
  private Player player;
  
  private int currentPage = 0;
  
  private double maxItemsPerPage = 27.0D;
  
  private HashMap<Integer, ItemStack> listOfItems = new HashMap<>();
  
  boolean isFirstOpen = true;
  
  public Pagination(Player player, MailStorage storage) {
    this.player = player;
    this.storage = storage;
    this.mailboxInventory = InventoryManager.mailboxInv.get(player);
  }
  
  public void doesPlayerHaveMail() {
    if (this.storage.getStorage().size() == 0) {
      this.player.closeInventory();
      this.player.sendMessage(Text.NO_MAILS);
    } else {
      paginate();
    } 
  }
  
  public void paginate() {
    List<HashMap<String, List<ItemStack>>> crates = this.storage.getStorage();
    this.invList = new ArrayList<>();
    this.listOfItems = new HashMap<>();
    int crateSize = crates.size();
    int id = 0;
    double pagesNeeded = Math.ceil(crateSize / this.maxItemsPerPage);
    for (int i = 0; i < pagesNeeded; i++) {
      Inventory inv = Bukkit.createInventory(this, 36, ChatColor.BLUE + "Mailbox Page: " + (i + 1));
      setButtons_Fillers(inv);
      this.invList.add(inv);
    } 
    if (this.storage.getStorage().size() > 0) {
      if (this.isFirstOpen) {
        ConfigSettingSet config = new ConfigSettingSet(this.player);
        if (config.isSoundEnabled(4)) {
          SoundSystem soundSystem = new SoundSystem(Ssound.OPEN_MAILBOX, this.player); 
        }
        this.isFirstOpen = false;
      } 
      for (HashMap<String, List<ItemStack>> map : crates) {
        for (Map.Entry<String, List<ItemStack>> entry : map.entrySet()) {
          UUID senderUUID = UUID.fromString(entry.getKey());
          Player senderName = Bukkit.getPlayer(senderUUID);
          String sender = "";
          if (senderName != null)
            sender = senderName.getName(); 
          ItemStack item = Items.createCrate(sender, Text.getCrateLore(entry.getValue()), id);
          this.listOfItems.put(Integer.valueOf(id), item);
          id++;
        } 
      } 
      int itemCount = 0;
      for (int j = 0; j < this.invList.size(); j++) {
        Inventory inv = this.invList.get(j);
        for (int b = 0; b < this.maxItemsPerPage; ) {
          if (this.listOfItems.get(Integer.valueOf(itemCount)) != null) {
            if (b == 27) {
              inv.setItem(b, this.listOfItems.get(Integer.valueOf(itemCount)));
              itemCount++;
              break;
            } 
            inv.setItem(b, this.listOfItems.get(Integer.valueOf(itemCount)));
            itemCount++;
            b++;
          } 
          break;
        } 
      } 
      this.player.openInventory(this.invList.get(this.currentPage));
    } else {
      this.player.closeInventory();
      this.player.sendMessage(Text.NO_MORE_MAILS);
    } 
  }
  
  private void setButtons_Fillers(Inventory inv) {
    inv.setItem(27, Items.previousButton());
    for (int i = 28; i < 35; i++)
      inv.setItem(i, Items.fillers()); 
    inv.setItem(35, Items.nextButton());
  }
  
  public void nextPage() {
    int nextPage = this.currentPage + 1;
    if (this.invList.size() > nextPage) {
      this.player.openInventory(this.invList.get(nextPage));
      this.currentPage++;
    } else {
      this.player.sendMessage(Text.NO_PAGES);
    } 
  }
  
  public void previousPage() {
    int previousPage = this.currentPage - 1;
    if (previousPage >= 0) {
      this.player.openInventory(this.invList.get(previousPage));
      this.currentPage--;
    } else {
      this.player.sendMessage(Text.NO_PAGES);
    } 
  }
  
  private void setRightPage() {
    double pagesNeeded = Math.ceil(this.storage.getStorage().size() / this.maxItemsPerPage) - 1.0D;
    while (this.currentPage > pagesNeeded)
      this.currentPage--; 
  }
  
  public void getItemsFromCrate(ItemStack crate) {
    if (this.storage.getStorage().size() > 0 && 
      crate.hasItemMeta()) {
      ItemMeta meta = crate.getItemMeta();
      int id = meta.getCustomModelData();
      HashMap<String, List<ItemStack>> items = this.storage.getStorage().get(id);
      for (Map.Entry<String, List<ItemStack>> entry : items.entrySet()) {
        for (ItemStack item : entry.getValue()) {
          if (item != null) {
            HashMap<Integer, ItemStack> excess = this.player.getInventory().addItem(new ItemStack[] { item });
            for (Map.Entry<Integer, ItemStack> et : excess.entrySet()) {
              if (et.getValue() != null)
                this.player.getWorld().dropItem(this.player.getLocation(), et.getValue()); 
            } 
          } 
        } 
      } 
      this.storage.getStorage().remove(id);
      ((Inventory)this.invList.get(this.currentPage)).remove(crate);
      setRightPage();
      paginate();
    } 
  }
  
  private void checkSpace(List<ItemStack> list) {
    int nItems = list.size();
    PlayerInventory playerInventory = this.player.getInventory();
  }
  
  public Inventory getInventory() {
    return null;
  }
}
