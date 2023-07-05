package cn.starry.hub.commands.admin;

import cn.starry.hub.Main;
import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.api.enums.PlayerState;
import cn.starry.hub.utils.ColorUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (command.getName().equalsIgnoreCase("vanish")) {
            if (commandSender instanceof Player) {
                final Player player = (Player)commandSender;
                if (!commandSender.hasPermission("*")) {
                    commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
                    return false;
                }
                if (PlayerData.VANSIH.get(player) == PlayerState.VANISH_OFF) {
                    PlayerData.VANSIH.put(player, PlayerState.VANISH_ON);
                    player.sendMessage(ColorUtil.color("&a你进入了隐身状态"));
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "你现在隐身了"));
                    for (Player p: Bukkit.getServer().getOnlinePlayers()) {
                        if (!p.equals(player)) {
                            p.hidePlayer(Main.getPlugin(Main.class), player);
                        }
                    }
                } else {
                    PlayerData.VANSIH.put(player, PlayerState.VANISH_OFF);
                    player.sendMessage(ColorUtil.color("&c你不再为隐身状态"));
                    for (Player p: Bukkit.getServer().getOnlinePlayers()) {
                        if (!p.equals(player)) {
                            p.showPlayer(Main.getPlugin(Main.class), player);
                        }
                    }
                }
            } else {
                commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            }
        }
        return true;
    }
}
