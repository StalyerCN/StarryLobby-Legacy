package cn.starry.hub.utils;

import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.StoreMenu;
import cn.starry.hub.listener.handler.LobbyHandler;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @Author: Starry_Killer
 * @Date: 2023/06/28
 */
public class StoreUtil {

    public static void buy(final Player player, final int points, final int require, final String command) {
        if (player.getInventory() != null) {
            player.closeInventory();
        }
        player.getInventory().clear();
        if (points < require) {
            player.sendTitle(ColorUtil.color("&c错误"), ColorUtil.color("&f没有足够的星尘"), 20, 20, 20);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), () -> {
                new LobbyHandler().loadItem(player);
                new StoreMenu().openMenu(player);
            }, 40L);
        } else {
            player.sendTitle(ColorUtil.color("&b获取成功"), ColorUtil.color("&f闪烁的星尘将护佑你"), 20, 20, 20);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
            new LobbyHandler().loadItem(player);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "p take " + player.getName() + " " + require);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }

}
