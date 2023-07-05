package cn.starry.hub.functions.settings;

import cn.starry.hub.Main;
import cn.starry.hub.database.MongoDB;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

@AutoRegister
public class PlayerTimeEvent implements Listener {

    @EventHandler
    public void TimeChange (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (new MongoDB().getPlayerData(uuid, "TIME") == null) {
            return;
        }
                if (new MongoDB().getPlayerData(uuid, "TIME").equals("DAY")) {
                    player.setPlayerTime(1200L, false);
                } else if (new MongoDB().getPlayerData(uuid, "TIME").equals("SUNSET")) {
                    player.setPlayerTime(12650L, false);
                } else if (new MongoDB().getPlayerData(uuid, "TIME").equals("NIGHT")) {
                    player.setPlayerTime(18000L, false);
                } else {
                    player.sendMessage(ColorUtil.color("&c发生了一个错误，类型:TIME_TYPE"));
                    player.sendMessage(ColorUtil.color("&c请将错误截图反馈至管理员"));
                }
    }
}
