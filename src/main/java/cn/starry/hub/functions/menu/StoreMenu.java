package cn.starry.hub.functions.menu;

import cn.starry.hub.functions.menu.button.MainButtons;
import cn.starry.hub.functions.menu.button.StoreButtons;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.StoreUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

@AutoRegister
public class StoreMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("                  &0货摊");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 54, title);

        //Main Buttons
        this.inv.setItem(10, new MainButtons().PlayerProfileButton(player));
        this.inv.setItem(19, new MainButtons().RewardsButton(player));
        this.inv.setItem(28, new MainButtons().ShopButton(player));
        this.inv.setItem(37, new MainButtons().NewsButton(player));
        //Store
        this.inv.setItem(12, new StoreButtons().StarRank(player));

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        int conomy = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%"));
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (!e.getInventory().getName().equals(title)) {
            return;
        }
        if (e.getInventory().getName().equals(title)) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
        //Main Button Listener
        if (e.getSlot() == 10) {
            new PlayerProfileMenu().openMenu(player);
        }
        if (e.getSlot() == 19) {
            player.sendMessage(ColorUtil.color("&c该功能正在开发"));
        }
        if (e.getSlot() == 28) {
            new StoreMenu().openMenu(player);
        }
        if (e.getSlot() == 37) {
            player.sendMessage(ColorUtil.color("&c该功能正在开发"));
        }
        //
        if (e.getSlot() == 12) {
            if (!player.hasPermission("group.star")) {
                StoreUtil.buy(player, conomy, 150, "lp user " + player.getName() + " parent addtemp star 1mo");
            } else {
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                player.sendMessage(ColorUtil.color("&c你已经拥有此权限了"));
            }
        }
    }

}

