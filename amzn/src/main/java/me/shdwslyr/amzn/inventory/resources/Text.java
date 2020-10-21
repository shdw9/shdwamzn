package me.shdwslyr.amzn.inventory.resources;

import java.util.ArrayList;
import java.util.List;
import me.shdwslyr.amzn.Main;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Text {
  public static final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "AMZN" + ChatColor.DARK_GRAY + "] ";
  
  public static final String MAILINVENTORY_NAME = ChatColor.RED + "Mailbox";
  
  public static final String SENDMAILINVENTORY_NAME = ChatColor.RED + "Send Package";
  
  public static final String SETTINGSNVENTORY_NAME = ChatColor.DARK_RED + "Settings";
  
  public static final String SOUNDINVENTORY_NAME = ChatColor.DARK_RED + "Sound";
  
  public static final String SOUND_SENT_ENABLED = ChatColor.GREEN + "SENT: " + ChatColor.AQUA + "YES";
  
  public static final String SOUND_SENT_DISABLED = ChatColor.GREEN + "SENT: " + ChatColor.RED + "NO";
  
  public static void helpMessage(Player player) {
	    player.sendMessage(ChatColor.DARK_GRAY + "-----------------" + ChatColor.GOLD + ChatColor.BOLD + "AMZN" + ChatColor.DARK_GRAY + "-----------------");
	    player.sendMessage(ChatColor.RED + "/amzn open " + ChatColor.DARK_GRAY + "-->" + ChatColor.GOLD + " Opens the mailbox");
	    player.sendMessage(ChatColor.RED + "/amzn settings " + ChatColor.DARK_GRAY + "-->" + ChatColor.GOLD + " Opens settings menu");
	    player.sendMessage(ChatColor.RED + "/amzn fav [player] " + ChatColor.DARK_GRAY + "-->" + ChatColor.GOLD + " Add player to favourites list");
	    player.sendMessage(ChatColor.RED + "/amzn unfav [player] " + ChatColor.DARK_GRAY + "-->" + ChatColor.GOLD + " Remove player from favourites");
	    player.sendMessage(ChatColor.RED + "/amzn fl " + ChatColor.DARK_GRAY + "-->" + ChatColor.GOLD + " Prints the favourites list");
	    player.sendMessage(ChatColor.RED + "/amzn send [player] " + ChatColor.DARK_GRAY + "-->" + ChatColor.GOLD + " Open the UI to send a mail");
	    player.sendMessage(ChatColor.DARK_GRAY + "updated by shdw9");
	    player.sendMessage(ChatColor.DARK_GRAY + "-------------------------------------");
	  }
  
  public static final String HELP = String.valueOf(String.valueOf(prefix)) + ChatColor.RED + "Wrong command try typing " + ChatColor.GOLD + "/amzn help";
  
  public static final String SPECIFY_PLAYER = String.valueOf(String.valueOf(prefix)) + ChatColor.RED + "Please specify a player";
  
  public static final String PLAYER_NO_EXIST = String.valueOf(String.valueOf(prefix)) + ChatColor.RED + "Player doesn't exist or is offline";
  
  public static final String SAMEPLAYER = String.valueOf(String.valueOf(prefix)) + ChatColor.DARK_RED + "You can't send a package to yourself!";
  
  public static final String SEND_MAIL_EMPTY = String.valueOf(String.valueOf(prefix)) + ChatColor.RED + "There is nothing to send!";
  
  public static final String MAIL_SENDED = String.valueOf(String.valueOf(prefix)) + ChatColor.GOLD + "Package has been sent!";
  
  public static final String NO_MAILS = String.valueOf(String.valueOf(prefix)) + ChatColor.RED + "You don't have any packages";
  
  public static final String NO_PAGES = String.valueOf(String.valueOf(prefix)) + ChatColor.RED + "This is the last page!";
  
  public static final NamespacedKey enchanter_namespace = new NamespacedKey((Plugin)Main.getMainInstance(), "smi");
  
  public static List<String> getCrateLore(List<ItemStack> itemList) {
    List<String> list = new ArrayList<>();
    for (ItemStack i : itemList) {
      if (i.hasItemMeta()) {
        list.add(String.valueOf(i.getItemMeta().getDisplayName().toString()) + ChatColor.LIGHT_PURPLE + " x " + i.getAmount() + ChatColor.GOLD + " [" + ChatColor.GREEN + i.getType() + ChatColor.GOLD + "]");
        continue;
      } 
      list.add(String.valueOf(i.getType().toString()) + ChatColor.LIGHT_PURPLE + " x " + i.getAmount());
    } 
    List<String> finalList = new ArrayList<>();
    for (String s : list) {
      if (s.contains("_")) {
        s = ChatColor.GREEN + s.replace("_", " ");
        finalList.add(s);
        continue;
      } 
      s = ChatColor.GREEN + s;
      finalList.add(s);
    } 
    return finalList;
  }
}
