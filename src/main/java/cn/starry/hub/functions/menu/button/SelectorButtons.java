package cn.starry.hub.functions.menu.button;

import cn.starry.hub.api.enums.GameOwned;
import cn.starry.hub.api.enums.GameType;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SelectorButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack BedWarsButton() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f0 &7人 ");
        lores.add(" ");
        lores.add(" &7经典的竞技游戏 ");
        lores.add(" &7击杀你的敌人 ");
        lores.add(" &7破坏床来获得胜利 ");
        lores.add(" ");

        item = new ItemBuilder(Material.BED).name(ColorUtil.color(GameOwned.BEDWARS.getDisplayName()+ GameType.SPORTS.getDisplayName())).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack ThePitButton() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f0 &7人 ");
        lores.add(" ");
        lores.add(" &7跳入坑中 ");
        lores.add(" &7拿起你的剑 ");
        lores.add(" &7击杀敌人 不断升级 ");
        lores.add(" ");

        item = new ItemBuilder(Material.BOW).name(ColorUtil.color(GameOwned.THEPIT.getDisplayName()+ GameType.SPORTS.getDisplayName())).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack DuelButton() {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7类型 &f竞技类小游戏 ");
        lores.add(" ");
        lores.add(" &7推荐版本 &f1.8.9 ");
        lores.add(" &7在线 &f0 &7人 ");
        lores.add(" ");
        lores.add(" &7在残酷的竞技场内 ");
        lores.add(" &7将剑锋指向敌人 ");
        lores.add(" ");

        item = new ItemBuilder(Material.FISHING_ROD).name(ColorUtil.color(GameOwned.DUEL.getDisplayName()+ GameType.SPORTS.getDisplayName())).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SkyWarsButton() {
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

        item = new ItemBuilder(Material.EYE_OF_ENDER).name(ColorUtil.color(GameOwned.SKYWARS.getDisplayName()+ GameType.SPORTS.getDisplayName())).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RPGButton() {
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

        item = new ItemBuilder(Material.FEATHER).name(ColorUtil.color(GameOwned.RPG.getDisplayName()+ GameType.RPG.getDisplayName())).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
