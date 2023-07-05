package cn.starry.hub.functions.menu;

import cn.starry.hub.database.MongoDB;
import cn.starry.hub.functions.menu.button.SettingButtons;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

@AutoRegister
public class SettingsMenu implements Listener {

    private Inventory inv;

    String title = ColorUtil.color("                &0个人设置");

    public void openMenu(Player player) {
        this.init(player);
        player.openInventory(this.inv);
    }

    public void init(Player player) {
        this.inv = Bukkit.createInventory(null, 36, title);
        UUID uuid = player.getUniqueId();

        //Settings Item
        this.inv.setItem(11, new SettingButtons().SHOW_ITEM());
        this.inv.setItem(13, new SettingButtons().RECEIVE_ITEM());
        this.inv.setItem(15, new SettingButtons().TIME_ITEM());
        //Show Button
        if (new MongoDB().getPlayerData(uuid,"SHOW").equals("ENABLE")) {
            this.inv.setItem(20, new SettingButtons().SHOW_ENABLE());
        } else if (new MongoDB().getPlayerData(uuid,"SHOW").equals("DISABLE")) {
            this.inv.setItem(20, new SettingButtons().SHOW_DISABLE());
        } else if (new MongoDB().getPlayerData(uuid,"SHOW").equals("RANK")) {
            this.inv.setItem(20, new SettingButtons().SHOW_RANK());
        } else {
            player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:SHOW_TYPE"));
            player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
        }
        //Receive Button
        if (new MongoDB().getPlayerData(uuid,"RECEIVE").equals("ENABLE")) {
            this.inv.setItem(22, new SettingButtons().RECEIVE_ENABLE());
        } else if (new MongoDB().getPlayerData(uuid,"RECEIVE").equals("DISABLE")) {
            this.inv.setItem(22, new SettingButtons().RECEIVE_DISABLE());
        } else {
            player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:RECEIVE_TYPE"));
            player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
        }
        //Time Button
            if (new MongoDB().getPlayerData(uuid, "TIME").equals("DAY")) {
                    this.inv.setItem(24, new SettingButtons().TIME_DAY());
            } else if (new MongoDB().getPlayerData(uuid, "TIME").equals("SUNSET")) {
                    this.inv.setItem(24, new SettingButtons().TIME_SUNSET());
            } else if (new MongoDB().getPlayerData(uuid, "TIME").equals("NIGHT")) {
                    this.inv.setItem(24, new SettingButtons().TIME_NIGHT());
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:TIME_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }

        player.openInventory(this.inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        UUID uuid = player.getUniqueId();
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
        if (e.getSlot() == 20) {
            if (new MongoDB().getPlayerData(uuid,"SHOW").equals("ENABLE")) {
                new MongoDB().updatePlayerData(uuid,"SHOW","DISABLE");
                for (Player p : getServer().getOnlinePlayers()) {
                    player.hidePlayer(p);
                }
                new SettingsMenu().openMenu(player);
            } else if (new MongoDB().getPlayerData(uuid,"SHOW").equals("DISABLE")) {
                new MongoDB().updatePlayerData(uuid,"SHOW","RANK");
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (p.hasPermission("group.star")) {
                        player.showPlayer(p);
                    } else {
                        player.hidePlayer(p);
                    }
                }
                new SettingsMenu().openMenu(player);
            } else if (new MongoDB().getPlayerData(uuid,"SHOW").equals("RANK")) {
                new MongoDB().updatePlayerData(uuid,"SHOW","ENABLE");
                for (Player p : getServer().getOnlinePlayers()) {
                    player.showPlayer(p);
                }
                new SettingsMenu().openMenu(player);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:SHOW_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
        if (e.getSlot() == 22) {
            if (new MongoDB().getPlayerData(uuid,"RECEIVE").equals("ENABLE")) {
                new MongoDB().updatePlayerData(uuid,"RECEIVE","DISABLE");
                new SettingsMenu().openMenu(player);
            } else if (new MongoDB().getPlayerData(uuid,"RECEIVE").equals("DISABLE")) {
                new MongoDB().updatePlayerData(uuid,"RECEIVE","ENABLE");
                new SettingsMenu().openMenu(player);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:RECEIVE_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
        if (e.getSlot() == 24) {
            if (new MongoDB().getPlayerData(uuid,"TIME").equals("DAY")) {
                new MongoDB().updatePlayerData(uuid,"TIME","SUNSET");
                player.setPlayerTime(12650L,false);
                new SettingsMenu().openMenu(player);
            } else if (new MongoDB().getPlayerData(uuid,"TIME").equals("SUNSET")) {
                new MongoDB().updatePlayerData(uuid,"TIME","NIGHT");
                player.setPlayerTime(18000L,false);
                new SettingsMenu().openMenu(player);
            } else if (new MongoDB().getPlayerData(uuid,"TIME").equals("NIGHT")) {
                new MongoDB().updatePlayerData(uuid,"TIME","DAY");
                player.setPlayerTime(1200L,false);
                new SettingsMenu().openMenu(player);
            } else {
                player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:TIME_TYPE"));
                player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
            }
        }
    }

}

