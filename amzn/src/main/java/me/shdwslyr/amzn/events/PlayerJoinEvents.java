package me.shdwslyr.amzn.events;

import me.shdwslyr.amzn.util.ConfigSettingSet;
import me.shdwslyr.amzn.util.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEvents implements Listener {
  @EventHandler
  public void onClose(PlayerJoinEvent e) {
    Player player = e.getPlayer();
    InventoryManager.addPlayer(player);
    ConfigSettingSet config = new ConfigSettingSet(player);
    config.firstSetup(player);
  }
}
