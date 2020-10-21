package me.shdwslyr.amzn.inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import me.shdwslyr.amzn.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class MainInventory implements InventoryHolder {
  Main plugin;
  
  Inventory inv;
  
  Player player;
  
  HashMap<Integer, ItemStack> storeList = new HashMap<>();
  
  public MainInventory(String inventoryName, int slotNumber) {
    this.inv = Bukkit.createInventory(this, slotNumber, inventoryName);
    this.plugin = Main.getMainInstance();
  }
  
  public void openInventory(Player player) {
    this.player = player;
    this.player.openInventory(this.inv);
  }
  
  public void storeInventory(Inventory inv, int from) {
    ItemStack[] items = inv.getContents();
    for (int i = from; i < items.length; i++) {
      if (items[i] != null)
        this.storeList.put(Integer.valueOf(i), items[i]); 
    } 
  }
  
  public void restoreInventory(Inventory inventory) {
    if (!this.storeList.isEmpty()) {
      Iterator<Map.Entry<Integer, ItemStack>> it = this.storeList.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<Integer, ItemStack> key = it.next();
        inventory.setItem(((Integer)key.getKey()).intValue(), key.getValue());
        it.remove();
      } 
    } 
  }
  
  @Nullable
  public void setButton(int[] slot, ItemStack[] item, Inventory inv) {
    for (int i = 0; i < slot.length; i++) {
      if (item[i] != null)
        inv.setItem(slot[i], item[i]); 
    } 
  }
  
  public boolean isInventoryEmpty(int from, Inventory inv) {
    ItemStack[] items = inv.getContents();
    int nullCount = 0;
    for (int i = from; i < items.length; i++) {
      if (items[i] != null)
        nullCount++; 
    } 
    if (nullCount > 0)
      return false; 
    return true;
  }
  
  public Inventory getInventory() {
    return this.inv;
  }
}
