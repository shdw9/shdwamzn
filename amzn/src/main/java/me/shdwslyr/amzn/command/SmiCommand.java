package me.shdwslyr.amzn.command;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;

import me.shdwslyr.amzn.inventory.MailStorage;
import me.shdwslyr.amzn.inventory.SendMailInventory;
import me.shdwslyr.amzn.inventory.SettingsInventory;
import me.shdwslyr.amzn.inventory.resources.Text;
import me.shdwslyr.amzn.util.ConfigSettingSet;
import me.shdwslyr.amzn.util.InventoryManager;
import me.shdwslyr.amzn.util.Pagination;

public class SmiCommand implements CommandExecutor {
  public String command = "amzn";
  
  public static HashMap<Player, Player> targetMap = new HashMap<>();
  
  public static HashMap<Player, Pagination> playerPagination = new HashMap<>();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (cmd.getName().equalsIgnoreCase(this.command)) {
        if (args.length == 0) {
          player.sendMessage(Text.HELP);
          return true;
        } 
        if (args.length == 1) {
          if (args[0].equalsIgnoreCase("open")) {
            Pagination pag = new Pagination(player, (MailStorage)InventoryManager.mailStorage.get(player.getUniqueId()));
            playerPagination.put(player, pag);
            pag.doesPlayerHaveMail();
            return true;
          } 
          if (args[0].equalsIgnoreCase("settings")) {
            SettingsInventory settings = new SettingsInventory(player);
            settings.openInventory(player);
            return true;
          } 
          if (args[0].equalsIgnoreCase("fav")) {
            player.sendMessage(Text.SPECIFY_PLAYER);
            return true;
          } 
          if (args[0].equalsIgnoreCase("send")) {
            player.sendMessage(Text.SPECIFY_PLAYER);
            return true;
          } 
          if (args[0].equalsIgnoreCase("unfav")) {
            player.sendMessage(Text.SPECIFY_PLAYER);
            return true;
          } 
          if (args[0].equalsIgnoreCase("fl")) {
            ConfigSettingSet config = new ConfigSettingSet(player);
            config.getFavList();
            return true;
          } 
          if (args[0].equalsIgnoreCase("help")) {
            Text.helpMessage(player);
            return true;
          } 
          player.sendMessage(Text.HELP);
          return true;
        } 
        if (args.length == 2) {
          if (args[0].equalsIgnoreCase("send")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target != player) {
              targetCheck(player, target);
              SendMailInventory mail = new SendMailInventory();
              mail.openInventory(player);
              return true;
            }
            else if (target == player) {
            	player.sendMessage(Text.SAMEPLAYER);
            	return true;
            }
            player.sendMessage(Text.PLAYER_NO_EXIST);
            return true;
          } 
          if (args[0].equalsIgnoreCase("fav")) {
            if (args[1] == null) {
              player.sendMessage(Text.SPECIFY_PLAYER);
              return true;
            } 
            Player target = Bukkit.getPlayer(args[1]);
            if (target != null) {
              ConfigSettingSet config = new ConfigSettingSet(player);
              config.addPlayerToFavorites(target);
              return true;
            } 
            player.sendMessage(Text.PLAYER_NO_EXIST);
            return true;
          } 
          if (args[0].equalsIgnoreCase("unfav")) {
            if (args[1] == null) {
              player.sendMessage(Text.SPECIFY_PLAYER);
              return true;
            } 
            ConfigSettingSet config = new ConfigSettingSet(player);
            config.removePlayerFromFavorites(args[1]);
            return true;
          } 
          player.sendMessage(Text.HELP);
          return true;
        } 
      } 
    } else {
      sender.sendMessage(ChatColor.RED + "This commands is only for players");
      return true;
    } 
    return false;
  }
  
  private void targetCheck(Player player, Player target) {
    if (targetMap.containsKey(player)) {
      targetMap.remove(player);
      targetMap.put(player, target);
    } else {
      targetMap.put(player, target);
    } 
  }
}
