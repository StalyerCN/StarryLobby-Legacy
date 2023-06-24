package cn.starry.hub.functions.menu.button.other;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NewsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7在这查看 ");
        lores.add(" &7服务器的资讯...");
        lores.add(" ");
        lores.add(" &f@Stellarmc.cn ");
        lores.add(" ");
        return new ItemBuilder(Material.ITEM_FRAME).name(ColorUtil.color(" &f新闻")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
    }

}
