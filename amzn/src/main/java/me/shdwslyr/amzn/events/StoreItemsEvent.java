package me.shdwslyr.amzn.events;

import me.shdwslyr.amzn.inventory.MailboxInventory;
import me.shdwslyr.amzn.inventory.SendMailInventory;
import me.shdwslyr.amzn.util.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class StoreItemsEvent implements Listener {
  InventoryManager invManager = new InventoryManager();
  
  @EventHandler
  public void onCloseMailbox(InventoryCloseEvent e) {
    if (!(e.getInventory().getHolder() instanceof MailboxInventory))
      return; 
    Player player = (Player)e.getPlayer();
    MailboxInventory manager_inv = (MailboxInventory)InventoryManager.mailboxInv.get(player);
    manager_inv.storeInventory(e.getInventory(), 3);
  }
  
  @EventHandler
  public void onCloseSendMailInventory(InventoryCloseEvent e) {
    if (!(e.getInventory().getHolder() instanceof SendMailInventory))
      return; 
    Player player = (Player)e.getPlayer();
    SendMailInventory manager_inv = (SendMailInventory)InventoryManager.sendMailInv.get(player);
    manager_inv.storeInventory(e.getInventory(), 1);
  }
}
