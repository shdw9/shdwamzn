package me.shdwslyr.amzn.inventory;

import me.shdwslyr.amzn.Main;
import me.shdwslyr.amzn.inventory.resources.Items;
import me.shdwslyr.amzn.inventory.resources.Text;
import me.shdwslyr.amzn.util.ConfigSettingSet;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SettingsInventory extends MainInventory {
  Main plugin;
  
  public SettingsInventory(Player player) {
    super(Text.SETTINGSNVENTORY_NAME, 9);
    this.player = player;
    this.plugin = Main.getMainInstance();
  }
  
  public void openInventory(Player player) {
    super.openInventory(player);
    ConfigSettingSet config = new ConfigSettingSet(player);
    int[] slots = { 2, 4 };
    ItemStack[] items = { Items.notofications(config.getNotifications()), Items.sound() };
    setButton(slots, items, this.inv);
  }
}
