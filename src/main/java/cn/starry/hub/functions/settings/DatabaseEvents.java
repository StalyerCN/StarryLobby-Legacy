package cn.starry.hub.functions.settings;

import cn.starry.hub.Main;
import cn.starry.hub.parm.AutoRegister;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

@AutoRegister
public class DatabaseEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Main.getInstance().getPlayerData().savePlayerData(playerUUID, player.getName(), "ENABLE", "ENABLE", "DAY");
    }
}
