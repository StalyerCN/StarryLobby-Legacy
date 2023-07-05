package cn.starry.hub.listener;

import cn.starry.hub.parm.AutoRegister;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;

@Deprecated
@AutoRegister
public class DebugListener implements Listener {

    @EventHandler
    public void onDebugMode(PlayerJoinEvent event) {
        Player player = event.getPlayer();
    }
}
