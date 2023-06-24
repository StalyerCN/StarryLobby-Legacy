package cn.starry.hub.functions;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpPad implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        double mult = 1;
        double y = 1;
        if (p.getLocation().getBlock().getType() == Material.STONE_PLATE) {
            Vector v = p.getLocation().getDirection().multiply(mult).setY(y);
            p.setVelocity(v);
            p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 3.0f, 2.0f);
        }
    }

}
