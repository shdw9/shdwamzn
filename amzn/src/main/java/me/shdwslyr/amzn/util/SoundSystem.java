package me.shdwslyr.amzn.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundSystem {
  Ssound sound;
  
  public SoundSystem(Ssound sound, Player player) {
    this.sound = sound;
    switch (sound) {
      case SENT:
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 7.0F, 1.5F);
        break;
      case RECIVED:
        player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 7.0F, 1.5F);
        break;
      case OPEN_MAILBOX:
        player.playSound(player.getLocation(), Sound.BLOCK_FENCE_GATE_OPEN, 1.0F, 1.5F);
        break;
    } 
  }
}
