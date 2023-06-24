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

public class RPGButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &fRPG ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.12.2 ");
        lores.add(" &7在线 &f0 &7人 ");
        lores.add(" ");
        lores.add(" &7编写唯美剧情 ");
        lores.add(" &7创造全新玩法 ");
        lores.add(" ");
        return new ItemBuilder(Material.FEATHER).name(ColorUtil.color(GameOwned.RPG.getDisplayName()+ GameType.RPG.getDisplayName())).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.closeInventory();
        ServerUtil.sendServer(player,"羁旅");
    }

}
