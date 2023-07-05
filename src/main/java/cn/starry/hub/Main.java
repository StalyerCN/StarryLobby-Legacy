package cn.starry.hub;

import cn.starry.hub.commands.admin.EditCommand;
import cn.starry.hub.commands.admin.VanishCommand;
import cn.starry.hub.commands.player.SendCommand;
import cn.starry.hub.commands.player.TimeCommand;
import cn.starry.hub.commands.rank.FlyCommand;
import cn.starry.hub.database.MongoDB;
import cn.starry.hub.functions.menu.SelectorMenu;
import cn.starry.hub.functions.scoreboard.Scoreboard;
import cn.starry.hub.parm.RegisterListener;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.scoreboard.Assemble;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * @Author: Starry_Killer
 * @Date: 2023/06/14
 */
public final class Main extends JavaPlugin {

    private static Main instance;

    private static JavaPlugin plugin;

    private MongoDB database;
    String prefix = "&f[StarryLobby] ";

    String type;

    @Override
    public void onEnable() {
        instance = this;
        database = new MongoDB();
        saveDefaultConfig();
        type = this.getConfig().getString("type");
        registerCommands();
        loadListeners();
        if (!type.equalsIgnoreCase("Skywars")) {
            loadScoreBoard();
        }
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bPlugin Author - Starry_Killer"));
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bPlugin Enable"));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bPlugin Disable"));
    }

    private void registerCommands() {
        this.getCommand("edit").setExecutor(new EditCommand());
        this.getCommand("vanish").setExecutor(new VanishCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("sendto").setExecutor(new SendCommand());
        this.getCommand("day").setExecutor(new TimeCommand());
        this.getCommand("sunset").setExecutor(new TimeCommand());
        this.getCommand("night").setExecutor(new TimeCommand());
        Arrays.asList(new Command[]{new SelectorMenu()}).forEach(cmd -> MinecraftServer.getServer().server.getCommandMap().register(cmd.getName(), this.getName(), cmd));
    }

    private void loadScoreBoard() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bTry to Load ScoreBoard..."));
        Assemble assemble = new Assemble(this, new Scoreboard());
        assemble.setTicks(4);
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bScoreBoard Load Successfully!"));
    }

    private void loadListeners() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bTry to Load Listeners..."));
        RegisterListener.registerListeners(this, "cn.starry.hub");
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bListeners Load Successfully!"));
    }


    public static Main getInstance() {
        return instance;
    }

    public MongoDB getPlayerData() {
        return database;
    }

}
