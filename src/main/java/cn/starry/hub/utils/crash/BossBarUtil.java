package cn.starry.hub.utils.crash;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BossBarUtil {
    public static float bossBarProgre = 0.5f;
    private static Map<UUID, FakeBossBar> fakeBossbar = new ConcurrentHashMap<UUID, FakeBossBar>();
    private static HashMap<UUID, Integer> timers = new HashMap();

    public static void sendBossBar(Player player, String mensaje, int seconds, Plugin plugin) {
        BossBarUtil.sendBossBar(player, CoreColor.colorCodes(mensaje), seconds, 3, plugin);
    }

    private static void sendBossBar(final Player player, String mensaje, int seconds, int interval, Plugin plugin) {
        if (BossBarUtil.hasSendBar(player)) {
            BossBarUtil.removeSendBar(player);
        }
        FakeBossBar bar = BossBarUtil.getBossBar(plugin, player, BossBarUtil.cleanMessage(mensaje), null, null, 0.0f);
        bar.send();
        final float progressMinus = bar.getProgress() / (float)(seconds * (20 / interval));
        BossBarUtil.cancelTimer(player);
        timers.put(player.getUniqueId(), Bukkit.getScheduler().runTaskTimer(plugin, new Runnable(){

            @Override
            public void run() {
                FakeBossBar bar = (FakeBossBar)fakeBossbar.get(player.getUniqueId());
                float newProgress = bar.getProgress() - progressMinus;
                if (newProgress <= 1.0f) {
                    BossBarUtil.canselBoss(player);
                    BossBarUtil.cancelTimer(player);
                } else {
                    bar.setProgress(newProgress);
                }
            }
        }, interval, interval).getTaskId());
    }

    private static void canselBoss(Player player) {
        if (fakeBossbar.containsKey(player.getUniqueId())) {
            FakeBossBar bar = fakeBossbar.get(player.getUniqueId());
            bar.destroy();
            fakeBossbar.remove(player.getUniqueId());
        }
    }

    public static void canselAll() {
        for (UUID uuid : fakeBossbar.keySet()) {
            FakeBossBar bar = fakeBossbar.get(uuid);
            bar.destroy();
            fakeBossbar.remove(uuid);
            Integer timerID = timers.remove(uuid);
            if (timerID == null) continue;
            Bukkit.getScheduler().cancelTask(timerID);
        }
    }

    private static FakeBossBar getBossBar(Plugin plugin, Player player, String name, Color color, Style style, float pro) {
        FakeBossBarPre1_9 bar = new FakeBossBarPre1_9(player, name, pro);
        fakeBossbar.put(player.getUniqueId(), bar);
        return bar;
    }

    private static String cleanMessage(String message) {
        if (message.length() > 64) {
            message = message.substring(0, 63);
        }
        return message;
    }

    private static void cancelTimer(Player player) {
        Integer timerID = timers.remove(player.getUniqueId());
        if (timerID != null) {
            Bukkit.getScheduler().cancelTask(timerID);
        }
    }

    private static void removeSendBar(Player player) {
        if (BossBarUtil.hasSendBar(player)) {
            BossBarUtil.canselBoss(player);
            BossBarUtil.cancelTimer(player);
        }
    }

    private static boolean hasSendBar(Player player) {
        return fakeBossbar.get(player.getUniqueId()) != null;
    }

    public static enum Style {
        PROGRESS,
        NOTCHED_6,
        NOTCHED_10,
        NOTCHED_12,
        NOTCHED_20;

    }

    public static enum Color {
        PINK,
        BLUE,
        RED,
        GREEN,
        YELLOW,
        PURPLE,
        WHITE;

    }
}
