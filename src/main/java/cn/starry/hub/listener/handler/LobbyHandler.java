package cn.starry.hub.listener.handler;

import cn.starry.hub.Main;
import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.api.enums.ProfileState;
import cn.starry.hub.functions.settings.PlayerShowEvent;
import cn.starry.hub.functions.settings.PlayerTimeEvent;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

public class LobbyHandler {
    public void returnToLobby(final Player player) {
        final Location spawn = player.getWorld().getSpawnLocation();
        spawn.add(0.5, 0, 0.5);
        spawn.setYaw(90);
        spawn.setPitch(0);
        player.teleport(spawn);
    }

    public static void reset(final Player player) {
        player.getInventory().clear();
        player.setHealth(player.getMaxHealth());
        player.setFallDistance(0.0f);
        player.setFoodLevel(20);
        player.setSaturation(10.0f);
        player.setGameMode(GameMode.SURVIVAL);
        player.setLevel(0);
        player.setExp(0.0f);
        player.setFireTicks(0);
        for (final PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setArmorContents(null);
    }

    public void drop(final Player player) {
        player.getInventory().clear();
        PlayerData.PROFILE.put(player, ProfileState.NOT_LOADED);
    }

    public void load(final Player player) {
        player.sendMessage(ColorUtil.color("&7正在加载你的用户资料..."));
        new LobbyHandler().loadItem(player);
        PlayerData.PROFILE.put(player, ProfileState.LOADED);
        player.sendMessage(ColorUtil.color("&a加载成功"));
        if (player.hasPermission("group.star")) {
            String message = PlaceholderAPI.setPlaceholders(player, "&a+ %luckperms_prefix%&f%player_name% &7踏入了闪烁的星尘");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ColorUtil.color(message));
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            }
        }
    }

    public void loadMessages(final Player player) {
        if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Lobby")) {
            player.sendTitle(ColorUtil.color("&bStardust &7| &f主大厅"), ColorUtil.color("&7星尘将为你指引方向"), 10, 20, 10);
        } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("BedWars")) {
            player.sendTitle(ColorUtil.color("&b起床战争"), ColorUtil.color("&7拿起剑,向敌人发起进攻"), 10, 20, 10);
        } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("SkyWars")) {
                player.sendTitle(ColorUtil.color("&b空岛战争"),ColorUtil.color("&7胜者为王"),10,20,10);
        } else {
            player.sendTitle(ColorUtil.color("&c错误"),ColorUtil.color("&7请反馈此情况至开发"),10,20,10);
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&c未知的大厅类型"));
        }
        if (Main.getPlugin(Main.class).getConfig().getString("type").equals("Lobby")) {
            player.sendMessage(ColorUtil.color("&f&m                           "));
            player.sendMessage(ColorUtil.color(""));
            player.sendMessage(ColorUtil.color("&f  &bStardust &7Twinkling"));
            player.sendMessage(ColorUtil.color(""));
            player.sendMessage(ColorUtil.color("   &7群号 &f1090558569"));
            player.sendMessage(ColorUtil.color("   &7地址 &fStellarmc.cn"));
            player.sendMessage(ColorUtil.color(""));
            player.sendMessage(ColorUtil.color("&f&m                           "));
        }
    }

    public void loadItem(final Player player) {
        reset(player);
        player.getInventory().setItem(2, new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color("&f个人设置 &7(右键打开)")).setNewSkullOwner(player).build());
        player.getInventory().setItem(0, ItemHandler.SELECTOR);
        player.getInventory().setItem(3, ItemHandler.STORE);
        player.getInventory().setItem(7, ItemHandler.GADGETSMENU);
        player.getInventory().setItem(8, ItemHandler.SETTINGS);
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Lobby")) {
            player.getInventory().setItem(6, ItemHandler.COSMETICS);
        }
        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), player::updateInventory, 1L);
    }

}
