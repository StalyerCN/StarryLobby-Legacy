package cn.starry.hub.listener;

import cn.starry.hub.listener.handler.LobbyHandler;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class LobbyListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        e.setJoinMessage(null);
        new LobbyHandler().drop(player);
        new LobbyHandler().loadMessages(player);
        new LobbyHandler().returnToLobby(player);
        new LobbyHandler().load(player);
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        new LobbyHandler().drop(player);
    }

    @EventHandler
    public void onMoveItem(InventoryClickEvent e) {
        if (e.getInventory() == null || e.getInventory().getName() == null) {
            return;
        }
        if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoinFly(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("lobby.fly")) {
            p.setAllowFlight(true);
            p.setFlying(true);
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

}
