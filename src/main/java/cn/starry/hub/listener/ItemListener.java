package cn.starry.hub.listener;

import cn.starry.hub.Main;
import cn.starry.hub.functions.menu.PlayerProfileMenu;
import cn.starry.hub.functions.menu.SelectorMenu;
import cn.starry.hub.functions.menu.SettingsMenu;
import cn.starry.hub.functions.menu.StoreMenu;
import cn.starry.hub.listener.handler.ItemHandler;
import cn.starry.hub.parm.AutoRegister;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import tk.kanaostore.losteddev.skywars.menu.ShopMenu;

@AutoRegister
public class ItemListener implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack Item = player.getEquipment().getItemInHand();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Item.equals(ItemHandler.SELECTOR)) {
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN,1,1);
                new SelectorMenu().openMenu(player);
            }
            else if (Item.getType() == Material.SKULL_ITEM) {
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN,1,1);
                new PlayerProfileMenu().openMenu(player);
            }
            else if (Item.equals(ItemHandler.STORE)) {
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN,1,1);
                new StoreMenu().openMenu(player);
            }
            else if (Item.equals(ItemHandler.GADGETSMENU)) {
                Bukkit.dispatchCommand(player,"gmenu main");
            }
            else if (Item.equals(ItemHandler.SETTINGS)) {
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN,1,1);
                new SettingsMenu().openMenu(player);
            }
            else if (Item.equals(ItemHandler.COSMETICS)) {
                if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("SkyWars")) {
                    new ShopMenu(player);
                } else {
                    Bukkit.dispatchCommand(player, "shop");
                }
            }
        }
    }
}
