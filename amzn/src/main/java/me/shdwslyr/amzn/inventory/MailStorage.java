package me.shdwslyr.amzn.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.shdwslyr.amzn.inventory.resources.Text;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MailStorage {
  private List<HashMap<String, List<ItemStack>>> storage = new ArrayList<>();
  
  public void addToMailStorage(Player sender, List<ItemStack> itemList) {
    List<ItemStack> checkList = new ArrayList<>();
    for (ItemStack item : itemList) {
      if (item.hasItemMeta()) {
        checkList.add(item);
        continue;
      } 
      checkList.add(item);
    } 
    HashMap<String, List<ItemStack>> listToAdd = new HashMap<>();
    listToAdd.put(sender.getUniqueId().toString(), checkList);
    this.storage.add(listToAdd);
    sender.sendMessage(Text.MAIL_SENDED);
  }
  
  public List<HashMap<String, List<ItemStack>>> getStorage() {
    return this.storage;
  }
  
  public void setStorage(List<HashMap<String, List<ItemStack>>> list) {
    if (list != null) {
      this.storage = list;
    } else {
      this.storage = new ArrayList<>();
    } 
  }
}
