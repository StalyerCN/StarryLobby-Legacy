package cn.starry.hub.listener;

import cn.starry.hub.functions.menu.GameSelectorMenu;
import cn.starry.hub.listener.handler.ItemHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack Item = player.getEquipment().getItemInMainHand();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Item.equals(ItemHandler.SELECTOR)) {
                new GameSelectorMenu().openMenu(player);
            }
        }
    }
}
