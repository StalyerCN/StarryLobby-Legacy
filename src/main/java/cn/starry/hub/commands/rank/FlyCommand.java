package cn.starry.hub.commands.rank;

import cn.starry.hub.utils.ColorUtil;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final org.bukkit.command.Command cmd, final String lable, final String[] args) {
        final Player p = (Player)sender;
        if (sender instanceof Player) {
            if (args.length == 0 && cmd.getName().equalsIgnoreCase("fly")) {
                if (p.hasPermission("lobby.fly") && !p.getAllowFlight()) {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.setFlySpeed(0.1f);
                    p.sendMessage(ColorUtil.color("&a已启用飞行"));
                }
                else if (p.hasPermission("lobby.fly") && p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.sendMessage(ColorUtil.color("&c已关闭飞行"));
                }
                else {
                    p.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
                }
            }
            else {
                p.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            }
        }
        return false;
    }

}
