package cn.starry.hub.functions.menu.button.game;

import cn.starry.hub.api.enums.GameOwned;
import cn.starry.hub.api.enums.GameType;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.utils.ServerUtil;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SkyWarsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f0 &7人 ");
        lores.add(" ");
        lores.add(" &7跳入附近的浮岛 ");
        lores.add(" &7掠夺装备 击杀敌人 ");
        lores.add(" ");
        return new ItemBuilder(Material.EYE_OF_ENDER).name(ColorUtil.color(GameOwned.SKYWARS.getDisplayName()+ GameType.SPORTS.getDisplayName())).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.closeInventory();
        ServerUtil.sendServer(player,"空岛战争");
    }

}
