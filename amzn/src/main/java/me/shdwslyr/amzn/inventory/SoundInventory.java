package me.shdwslyr.amzn.inventory;

import me.shdwslyr.amzn.Main;
import me.shdwslyr.amzn.inventory.resources.Items;
import me.shdwslyr.amzn.inventory.resources.Text;
import me.shdwslyr.amzn.util.ConfigSettingSet;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SoundInventory extends MainInventory {
  Main plugin;
  
  public SoundInventory() {
    super(Text.SOUNDINVENTORY_NAME, 9);
    this.plugin = Main.getMainInstance();
  }
  
  public void openInventory(Player player) {
    super.openInventory(player);
    ConfigSettingSet config = new ConfigSettingSet(player);
    int[] slots = { 1, 3, 5, 7 };
    ItemStack[] items = { Items.soundSent(config.getSentSound()), Items.soundEmpty(config.getEmptySound()), Items.soundRecived(config.getRecivedSound()), Items.soundOpenMail(config.getOpenSound()) };
    setButton(slots, items, this.inv);
  }
}
