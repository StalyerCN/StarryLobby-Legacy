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

public class ShopButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7在这获取 ");
        lores.add(" &7权益 道具等...");
        lores.add(" ");
        lores.add(" &f包含 ");
        lores.add("  &7星烁 神秘箱 ");
        lores.add("  &7喊话喇叭等... ");
        lores.add(" ");
        return new ItemBuilder(Material.NAME_TAG).name(ColorUtil.color(" &f货摊")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
    }

}
