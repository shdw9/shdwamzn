package me.shdwslyr.amzn.inventory;

import me.shdwslyr.amzn.inventory.resources.Text;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MailboxInventory extends MainInventory implements InventoryHolder {
  public MailboxInventory() {
    super(Text.MAILINVENTORY_NAME, 36);
  }
  
  public void openInventory(Player player) {
    super.openInventory(player);
  }
  
  public Inventory getInventory() {
    return this.inv;
  }
}
