package cn.starry.hub.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class SoundListener implements Listener {

    @EventHandler
    public void onItemChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_HAT, 1, 2);
    }

    @EventHandler
    public void onPlayCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
    }
}
