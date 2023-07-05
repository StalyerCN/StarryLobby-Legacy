package cn.starry.hub.commands.player;

import cn.starry.hub.database.MongoDB;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TimeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        UUID uuid = player.getUniqueId();
        if (command.getName().equalsIgnoreCase("day")) {
            if (!new MongoDB().getPlayerData(uuid,"TIME").equals("DAY")) {
                player.setPlayerTime(1200L, false);
                new MongoDB().updatePlayerData(uuid, "TIME", "DAY");
                player.sendMessage(ColorUtil.color("&f已将你的时间设置为 &b白天"));
            } else {
                player.sendMessage(ColorUtil.color("&c当前时间已为白天!"));
            }
        } else if (command.getName().equalsIgnoreCase("sunset")) {
            if (!new MongoDB().getPlayerData(uuid,"TIME").equals("SUNSET")) {
                player.setPlayerTime(12650L, false);
                new MongoDB().updatePlayerData(uuid, "TIME", "SUNSET");
                player.sendMessage(ColorUtil.color("&f已将你的时间设置为 &b傍晚"));
            } else {
                player.sendMessage(ColorUtil.color("&c当前时间已为傍晚!"));
            }
        } else if (command.getName().equalsIgnoreCase("night")) {
            if (!new MongoDB().getPlayerData(uuid,"TIME").equals("NIGHT")) {
            player.setPlayerTime(18000L,false);
            new MongoDB().updatePlayerData(uuid,"TIME","NIGHT");
            player.sendMessage(ColorUtil.color("&f已将你的时间设置为 &b夜晚"));
            } else {
                player.sendMessage(ColorUtil.color("&c当前时间已为夜晚!"));
            }
        }
        return true;
    }
}
