package cn.starry.hub.functions.menu;

import cn.starry.hub.functions.menu.button.MainButtons;
import cn.starry.hub.functions.menu.button.SelectorButtons;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

@AutoRegister
public class SelectorMenu extends Command implements Listener {

    private Inventory inv;

    public SelectorMenu() {
        super("menu");
    }

    String title = ColorUtil.color("                  &0菜单");

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
        //Game Buttons
        this.inv.setItem(12, new SelectorButtons().BedWarsButton());
        this.inv.setItem(13, new SelectorButtons().ThePitButton());
        this.inv.setItem(14, new SelectorButtons().DuelButton());
        this.inv.setItem(15, new SelectorButtons().SkyWarsButton());
        this.inv.setItem(39, new SelectorButtons().RPGButton());

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
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
            ServerUtil.sendServer(player,"起床大厅");
        }
        if (e.getSlot() == 13) {
            ServerUtil.sendServer(player,"天坑乱斗");
        }
        if (e.getSlot() == 14) {
            ServerUtil.sendServer(player,"竞技场");
        }
        if (e.getSlot() == 15) {
            ServerUtil.sendServer(player,"空岛战争");
        }
        if (e.getSlot() == 39) {
            ServerUtil.sendServer(player,"羁旅");
        }
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        this.openMenu((Player)commandSender);
        return false;
    }

}

