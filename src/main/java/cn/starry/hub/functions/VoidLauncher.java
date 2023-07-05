package cn.starry.hub.functions;

import cn.starry.hub.listener.handler.LobbyHandler;
import cn.starry.hub.parm.AutoRegister;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

@AutoRegister
public class VoidLauncher implements Listener {

    @EventHandler
    public void onPlayerVoid(PlayerMoveEvent event) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            Location loc = player.getLocation();
            double playerY = player.getLocation().getY();

            if (playerY <= 30) {
                loc.getWorld().createExplosion(loc, 0.5F);
                player.setVelocity(new Vector(0, 250, 0));
            }
        }
    }

}
