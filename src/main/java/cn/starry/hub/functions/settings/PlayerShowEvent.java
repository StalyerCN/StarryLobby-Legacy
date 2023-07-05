package cn.starry.hub.functions.settings;

import cn.starry.hub.Main;
import cn.starry.hub.database.MongoDB;
import cn.starry.hub.functions.menu.SettingsMenu;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

@AutoRegister
public class PlayerShowEvent implements Listener{

    @EventHandler (priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
            if (new MongoDB().getPlayerData(uuid, "SHOW").equals("ENABLE")) {
                for (Player p : getServer().getOnlinePlayers()) {
                    player.hidePlayer(p);
                }
                new SettingsMenu().openMenu(player);
            } else if (new MongoDB().getPlayerData(uuid, "SHOW").equals("DISABLE")) {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (p.hasPermission("group.star")) {
                        player.showPlayer(p);
                    } else {
                        player.hidePlayer(p);
                    }
                }
                new SettingsMenu().openMenu(player);
            } else if (new MongoDB().getPlayerData(uuid, "SHOW").equals("RANK")) {
                for (Player p : getServer().getOnlinePlayers()) {
                    player.showPlayer(p);
                }
                new SettingsMenu().openMenu(player);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:SHOW_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
    }
}
