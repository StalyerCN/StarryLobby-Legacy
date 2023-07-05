package cn.starry.hub.utils;

import cn.starry.hub.Main;
import cn.starry.hub.listener.handler.LobbyHandler;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @Author: Starry_Killer
 * @Date: 2023/06/23
 */
public class ServerUtil {

    public static void sendServer(final Player player, final String server) {
        if (player.getInventory() != null) {
            player.closeInventory();
        }
        player.getInventory().clear();
        player.sendTitle(ColorUtil.color("&b传送中"),ColorUtil.color("&7正在寻找服务器..."),20,100,0);
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spb connect " + server + " " + player.getDisplayName());
            if (player.isOnline()) {
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                player.sendTitle(ColorUtil.color("&c错误"),ColorUtil.color("&7无可用服务器"),20,40,20);
                new LobbyHandler().loadItem(player);
            }
        }, 60L);
    }

}
