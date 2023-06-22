package cn.starry.hub.listener;

import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.api.enums.PlayerState;
import cn.starry.hub.listener.handler.LobbyHandler;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Date: 2023/06/14
 */
public class PlayerListener implements Listener {

    private List<Material> blockTypes = new ArrayList<>();

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        PlayerData.EDIT.put(player, PlayerState.EDIT_OFF);
        PlayerData.VANSIH.put(player, PlayerState.VANISH_OFF);
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        PlayerData.EDIT.put(player, PlayerState.EDIT_OFF);
        PlayerData.VANSIH.put(player, PlayerState.VANISH_OFF);
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (PlayerData.EDIT.get(player) == PlayerState.EDIT_OFF) {
            player.sendMessage(ColorUtil.color("&c你不被允许破坏方块"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (PlayerData.EDIT.get(player) == PlayerState.EDIT_OFF) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.sendMessage(ColorUtil.color("&c你不被允许丢弃物品"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickUpItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        blockTypes.add(Material.WORKBENCH);
        blockTypes.add(Material.FURNACE);
        blockTypes.add(Material.ENCHANTMENT_TABLE);
        blockTypes.add(Material.ANVIL);
        blockTypes.add(Material.CHEST);
        blockTypes.add(Material.ENDER_CHEST);
        blockTypes.add(Material.SOIL);
        blockTypes.add(Material.ARMOR_STAND);
        if (clickedBlock != null && blockTypes.contains(clickedBlock.getType()) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.PHYSICAL)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerVoid(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getY() <= 1) {
            new LobbyHandler().returnToLobby(player);
        }
    }

}
