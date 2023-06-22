package cn.starry.hub;

import cn.starry.hub.commands.admin.EditCommand;
import cn.starry.hub.commands.admin.VanishCommand;
import cn.starry.hub.commands.rank.FlyCommand;
import cn.starry.hub.functions.JumpPad;
import cn.starry.hub.functions.scoreboard.Scoreboard;
import cn.starry.hub.listener.ItemListener;
import cn.starry.hub.listener.LobbyListener;
import cn.starry.hub.listener.PlayerListener;
import cn.starry.hub.listener.SoundListener;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.menu.ButtonListener;
import cn.starry.hub.utils.scoreboard.Assemble;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @Author: Starry_Killer
 * @Date: 2023/06/14
 */
public final class Main extends JavaPlugin {

    private static Main instance;

    private static JavaPlugin plugin;
    String prefix = "&f[StarryLobby] ";

    @Override
    public void onEnable() {
        registerEvents();
        registerCommands();
        loadScoreBoard();
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bPlugin Enable"));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bPlugin Disable"));
    }

    private void registerEvents() {
        final PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new LobbyListener(), this);
        manager.registerEvents(new PlayerListener(), this);
        manager.registerEvents(new ItemListener(), this);
        manager.registerEvents(new SoundListener(),this);
        manager.registerEvents(new JumpPad(),this);
        //Menu
        manager.registerEvents(new ButtonListener(), this);

    }

    private void registerCommands() {
        this.getCommand("edit").setExecutor(new EditCommand());
        this.getCommand("vanish").setExecutor(new VanishCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
    }

    private void loadScoreBoard() {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bTry to Load ScoreBoard..."));
        Assemble assemble = new Assemble(this, new Scoreboard());
        assemble.setTicks(4);
        Bukkit.getConsoleSender().sendMessage(ColorUtil.color(prefix + "&bScoreBoard Load Successfully!"));
    }


    public static Main getInstance() {
        return Main.instance;
    }


}
