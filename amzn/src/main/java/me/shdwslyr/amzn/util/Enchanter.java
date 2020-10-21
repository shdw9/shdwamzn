package me.shdwslyr.amzn.util;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Enchanter extends Enchantment {
  public Enchanter(NamespacedKey key) {
    super(key);
  }
  
  public boolean canEnchantItem(ItemStack arg0) {
    return false;
  }
  
  public boolean conflictsWith(Enchantment arg0) {
    return false;
  }
  
  public EnchantmentTarget getItemTarget() {
    return null;
  }
  
  public int getMaxLevel() {
    return 0;
  }
  
  public String getName() {
    return null;
  }
  
  public int getStartLevel() {
    return 1;
  }
  
  public boolean isCursed() {
    return false;
  }
  
  public boolean isTreasure() {
    return false;
  }
}
