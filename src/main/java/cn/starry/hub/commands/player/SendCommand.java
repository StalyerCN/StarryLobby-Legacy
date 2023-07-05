package cn.starry.hub.commands.player;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ServerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player) commandSender;
        if (strings.length == 1) {
            ServerUtil.sendServer(player,strings[0]);
            return true;
        }
        player.sendMessage(ColorUtil.color("&c用法: /sendto <服务器>"));
        return false;
    }
}
