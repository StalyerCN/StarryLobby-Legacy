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

public class PlayerProfileButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7名称 ");
        lores.add(" &f" + player.getName() + " ");
        lores.add(PlaceholderAPI.setPlaceholders(player, " &7权限 %luckperms_primary_group_name% "));
        lores.add(" &7等级 读取中 ");
        lores.add(" ");
        lores.add(" &7在这更改你的设置 ");
        lores.add(" &7或查询个人信息 ");
        lores.add(" ");
        return new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color(" &f个人设置")).setSkullOwner(player.getName()).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
    }

}
