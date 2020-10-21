package me.shdwslyr.amzn.events;
import me.shdwslyr.amzn.command.SmiCommand;
import me.shdwslyr.amzn.inventory.SendMailInventory;
import me.shdwslyr.amzn.inventory.SettingsInventory;
import me.shdwslyr.amzn.inventory.SoundInventory;
import me.shdwslyr.amzn.inventory.resources.Items;
import me.shdwslyr.amzn.util.ConfigSettingSet;
import me.shdwslyr.amzn.util.InventoryManager;
import me.shdwslyr.amzn.util.Pagination;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickEvents implements Listener {
  @EventHandler
  public void onClickSendInventory(InventoryClickEvent e) {
    Inventory inv = e.getInventory();
    if (!(inv.getHolder() instanceof SendMailInventory))
      return; 
    Player player = (Player)e.getWhoClicked();
    SendMailInventory smi = (SendMailInventory)InventoryManager.sendMailInv.get(player);
    ItemStack itemClicked = e.getCurrentItem();
    if (itemClicked != null && itemClicked.equals(Items.sendMailItemButton())) {
      Player target = (Player)SmiCommand.targetMap.get(player);
      smi.sendItems(player, target, inv);
      e.setCancelled(true);
      return;
    } 
  }
  
  @EventHandler
  public void onClickPagination(InventoryClickEvent e) {
    Inventory inv = e.getInventory();
    if (!(inv.getHolder() instanceof Pagination))
      return; 
    ItemStack itemClicked = e.getCurrentItem();
    Player player = (Player)e.getWhoClicked();
    Pagination pag = (Pagination)SmiCommand.playerPagination.get(player);
    if (itemClicked != null) {
      if (itemClicked.equals(Items.fillers())) {
        e.setCancelled(true);
        return;
      } 
      if (itemClicked.getType().equals(Material.CHEST)) {
        pag.getItemsFromCrate(itemClicked);
        e.setCancelled(true);
        return;
      } 
      if (itemClicked.equals(Items.nextButton())) {
        pag.nextPage();
        e.setCancelled(true);
        return;
      } 
      if (itemClicked.equals(Items.previousButton())) {
        pag.previousPage();
        e.setCancelled(true);
        return;
      } 
    } 
  }
  
  @EventHandler
  public void onClickSettings(InventoryClickEvent e) {
    Inventory inv = e.getInventory();
    if (!(inv.getHolder() instanceof SettingsInventory))
      return; 
    ItemStack itemClicked = e.getCurrentItem();
    Player player = (Player)e.getWhoClicked();
    ConfigSettingSet config = new ConfigSettingSet(player);
    if (itemClicked != null)
      if (itemClicked.getType().equals(Material.WRITABLE_BOOK)) {
        config.setNotifications();
        SettingsInventory settings = new SettingsInventory(player);
        e.setCancelled(true);
        settings.openInventory(player);
      }  
  }
  
  @EventHandler
  public void onClickSoundButton(InventoryClickEvent e) {
    Inventory inv = e.getInventory();
    if (!(inv.getHolder() instanceof SettingsInventory))
      return; 
    ItemStack itemClicked = e.getCurrentItem();
    Player player = (Player)e.getWhoClicked();
    SoundInventory soundInv = new SoundInventory();
    if (itemClicked != null)
      if (itemClicked.equals(Items.sound())) {
        e.setCancelled(true);
        soundInv.openInventory(player);
      }  
  }
  
  @EventHandler
  public void onClickSoundMenu(InventoryClickEvent e) {
    Inventory inv = e.getInventory();
    if (!(inv.getHolder() instanceof SoundInventory))
      return; 
    Player player = (Player)e.getWhoClicked();
    SoundInventory soundInv = new SoundInventory();
    ConfigSettingSet config = new ConfigSettingSet(player);
    if (e.getRawSlot() == 1 || e.getRawSlot() == 3 || e.getRawSlot() == 5 || e.getRawSlot() == 7) {
      config.setSound(e.getRawSlot());
      soundInv.openInventory(player);
    } 
    e.setCancelled(true);
  }
}
