package cn.starry.hub.functions.settings;

import cn.starry.hub.database.MongoDB;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static org.bukkit.Bukkit.getServer;

@AutoRegister
public class PlayerReceiveEvent implements Listener {

    @EventHandler
    public void ReceiveEvent(AsyncPlayerChatEvent event) {
        event.getRecipients().clear();
        for (Player player : getServer().getOnlinePlayers()) {
            if (new MongoDB().getPlayerData(player.getUniqueId(), "RECEIVE").equals("ENABLE")) {
                event.getRecipients().add(player);
            } else {
                event.getRecipients().remove(player);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                player.sendMessage(ColorUtil.color("&c在你开启屏蔽发言时,你的发言也将受到限制!"));
                event.setCancelled(true);
            }
        }
    }
}
