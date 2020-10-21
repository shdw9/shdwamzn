package me.shdwslyr.amzn.events;

import me.shdwslyr.amzn.command.SmiCommand;
import me.shdwslyr.amzn.inventory.SendMailInventory;
import me.shdwslyr.amzn.util.InventoryManager;
import me.shdwslyr.amzn.util.Pagination;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class RestoreItemsEvent implements Listener {
  @EventHandler
  public void onOpenMailbox(InventoryOpenEvent e) {
    if (!(e.getInventory().getHolder() instanceof me.shdwslyr.amzn.inventory.MailboxInventory))
      return; 
    Player player = (Player)e.getPlayer();
    ((Pagination)SmiCommand.playerPagination.get(player)).paginate();
  }
  
  @EventHandler
  public void onOpenSendMailInventory(InventoryOpenEvent e) {
    if (!(e.getInventory().getHolder() instanceof SendMailInventory))
      return; 
    Player player = (Player)e.getPlayer();
    SendMailInventory manager_inv = (SendMailInventory)InventoryManager.sendMailInv.get(player);
    manager_inv.restoreInventory(e.getInventory());
  }
}
