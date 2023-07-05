package cn.starry.hub.functions.menu.button;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MainButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack PlayerProfileButton(Player player) {
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

        item = new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color(" &f个人档案")).setNewSkullOwner(player).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RewardsButton(Player player) {
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

        item = new ItemBuilder(Material.STORAGE_MINECART).name(ColorUtil.color(" &f奖励")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack ShopButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7在这获取 ");
        lores.add(" &7权益 道具等...");
        lores.add(" ");
        lores.add(" &f包含 ");
        lores.add("  &7星烁 神秘箱 ");
        lores.add("  &7喊话喇叭等... ");
        lores.add(" ");

        item = new ItemBuilder(Material.NAME_TAG).name(ColorUtil.color(" &f货摊")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack NewsButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7在这查看 ");
        lores.add(" &7服务器的资讯...");
        lores.add(" ");
        lores.add(" &f@Stellarmc.cn ");
        lores.add(" ");

        item = new ItemBuilder(Material.ITEM_FRAME).name(ColorUtil.color(" &f新闻")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
