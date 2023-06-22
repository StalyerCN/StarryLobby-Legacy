package cn.starry.hub.listener.handler;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemHandler {
    public static ItemStack SELECTOR;
    public static ItemStack STORE;
    public static ItemStack SETTINGS;

    static {
        ItemHandler.SELECTOR = new ItemBuilder(Material.PAPER).name(ColorUtil.color("&e游戏菜单 &7(右键打开)")).build();
        ItemHandler.STORE = new ItemBuilder(Material.NAME_TAG).name(ColorUtil.color("&f货摊 &7(右键打开)")).build();
        ItemHandler.SETTINGS = new ItemBuilder(Material.FEATHER).name(ColorUtil.color("&f玩家设置 &7(右键打开)")).build();
    }
}
