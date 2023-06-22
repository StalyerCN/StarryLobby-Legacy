package cn.starry.hub.listener.handler;

import cn.starry.hub.Main;
import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.api.enums.PlayerState;
import cn.starry.hub.api.enums.ProfileState;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

public class LobbyHandler {
    public void returnToLobby(final Player player) {
        final Location spawn = player.getWorld().getSpawnLocation();
        spawn.add(0.5, 0, 0.5);
        spawn.setYaw(90);
        spawn.setPitch(0);
        player.teleport(spawn);
    }

    public static void reset(final Player player) {
        player.setHealth(player.getMaxHealth());
        player.setFallDistance(0.0f);
        player.setFoodLevel(20);
        player.setSaturation(10.0f);
        player.setGameMode(GameMode.SURVIVAL);
        player.setLevel(0);
        player.setExp(0.0f);
        player.setFireTicks(0);
        player.setWalkSpeed(0.2f);
        for (final PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().clear();
        player.getInventory().setArmorContents((ItemStack[])null);
    }

    public void drop(final Player player) {
        player.getInventory().clear();
        PlayerData.PROFILE.put(player, ProfileState.NOT_LOADED);
    }

    public void load(final Player player) {
        player.sendMessage(ColorUtil.color("&7正在加载你的用户资料..."));
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), () -> {
            PlayerData.PROFILE.put(player, ProfileState.LOADED);
            new LobbyHandler().loadItem(player);
            player.sendMessage(ColorUtil.color("&a加载成功"));
        }, 20L);
    }

    public void loadMessages(final Player player) {
        player.sendTitle(ColorUtil.color("&bStardust &7| &f主大厅"),ColorUtil.color("&7星尘将为你指引方向"),10,20,10);
        player.sendMessage(ColorUtil.color("&f&m                           "));
        player.sendMessage(ColorUtil.color(""));
        player.sendMessage(ColorUtil.color("&f  &bStardust &7Twinkling"));
        player.sendMessage(ColorUtil.color(""));
        player.sendMessage(ColorUtil.color("   &7群号 &f1090558569"));
        player.sendMessage(ColorUtil.color("   &7地址 &fStellarmc.cn"));
        player.sendMessage(ColorUtil.color(""));
        player.sendMessage(ColorUtil.color("&f&m                           "));
    }

    public void loadItem(final Player player) {
        reset(player);
        player.getInventory().setItem(2, new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color("&f个人设置 &7(右键打开)")).setSkullOwner(player.getName()).build());
        player.getInventory().setItem(0, ItemHandler.SELECTOR);
        player.getInventory().setItem(3, ItemHandler.STORE);
        player.getInventory().setItem(8, ItemHandler.SETTINGS);
        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), player::updateInventory, 1L);
    }

}
