package cn.starry.hub.functions.menu.button.other;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.utils.menu.Button;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RewardsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7等级 读取中 ");
        lores.add(" ");
        lores.add(" &7在这领取你的奖励 ");
        lores.add(" ");
        lores.add(" &f包含 ");
        lores.add("  &7等级奖励 ");
        lores.add("  &7硬币增倍器 ");
        lores.add(" ");
        return new ItemBuilder(Material.STORAGE_MINECART).name(ColorUtil.color(" &f奖励")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
    }

}
