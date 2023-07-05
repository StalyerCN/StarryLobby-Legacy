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

public class SettingButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack SHOW_ITEM() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7更改选项来定制玩家");
        lores.add(" &7显示的类型");
        lores.add(" ");

        item = new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color(" &f玩家显示")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SHOW_ENABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&a➥ &f显示所有玩家 ");
        lores.add("   &7隐藏所有玩家 ");
        lores.add("   &7仅星烁会员及以上 ");
        lores.add("");
        lores.add("   &e点击切换 ");
        lores.add("");

        item = new ItemBuilder(Material.INK_SACK).durability(10).name(ColorUtil.color("&a显示所有玩家")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SHOW_DISABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("   &7显示所有玩家 ");
        lores.add("&a➥ &f隐藏所有玩家 ");
        lores.add("   &7仅星烁会员及以上 ");
        lores.add("");
        lores.add("   &e点击切换 ");
        lores.add("");

        item = new ItemBuilder(Material.INK_SACK).durability(8).name(ColorUtil.color("&a隐藏所有玩家")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SHOW_RANK() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("   &7显示所有玩家 ");
        lores.add("   &7隐藏所有玩家 ");
        lores.add("&a➥ &f仅星烁会员及以上 ");
        lores.add("");
        lores.add("   &e点击切换 ");
        lores.add("");

        item = new ItemBuilder(Material.INK_SACK).durability(12).name(ColorUtil.color("&a仅星烁会员及以上")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RECEIVE_ITEM() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7更改选项来定制是否可见");
        lores.add(" &7其他玩家所发送的消息");
        lores.add(" ");

        item = new ItemBuilder(Material.FIREWORK).name(ColorUtil.color(" &f消息可见")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RECEIVE_ENABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&a➥ &f显示玩家消息 ");
        lores.add("   &7隐藏玩家消息 ");
        lores.add("");
        lores.add("   &e点击切换 ");
        lores.add("");

        item = new ItemBuilder(Material.INK_SACK).durability(10).name(ColorUtil.color("&a显示玩家消息")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RECEIVE_DISABLE() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("   &7显示玩家消息 ");
        lores.add("&a➥ &f隐藏玩家消息 ");
        lores.add("");
        lores.add("   &e点击切换 ");
        lores.add("");

        item = new ItemBuilder(Material.INK_SACK).durability(8).name(ColorUtil.color("&a隐藏玩家消息")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack TIME_ITEM() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7更改选项来定制当前玩家");
        lores.add(" &7的时间状态");
        lores.add(" ");

        item = new ItemBuilder(Material.WATCH).name(ColorUtil.color(" &f更改时间")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack TIME_DAY() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("&a➥ &f白天 ");
        lores.add("   &7傍晚 ");
        lores.add("   &7夜晚 ");
        lores.add("");
        lores.add("   &e点击切换 ");
        lores.add("");

        item = new ItemBuilder(Material.INK_SACK).durability(1).name(ColorUtil.color("&a白天")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack TIME_SUNSET() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("   &7白天 ");
        lores.add("&a➥ &f傍晚 ");
        lores.add("   &7夜晚 ");
        lores.add("");
        lores.add("   &e点击切换 ");
        lores.add("");

        item = new ItemBuilder(Material.INK_SACK).durability(14).name(ColorUtil.color("&a傍晚")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack TIME_NIGHT() {
        List<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("   &7白天 ");
        lores.add("   &7傍晚 ");
        lores.add("&a➥ &f夜晚 ");
        lores.add("");
        lores.add("   &e点击切换 ");
        lores.add("");

        item = new ItemBuilder(Material.INK_SACK).durability(0).name(ColorUtil.color("&a夜晚")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
