package cn.starry.hub.functions.menu;

import cn.starry.hub.functions.menu.button.game.*;
import cn.starry.hub.functions.menu.button.other.NewsButton;
import cn.starry.hub.functions.menu.button.other.PlayerProfileButton;
import cn.starry.hub.functions.menu.button.other.RewardsButton;
import cn.starry.hub.functions.menu.button.other.ShopButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Starry_Killer
 * @Date: 2023/06/21
 */
public class GameSelectorMenu extends Menu {
    @Override
    public String getTitle(Player player) {
        return "菜单";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> button = new HashMap<>();
        button.put(10,new PlayerProfileButton());
        button.put(19,new RewardsButton());
        button.put(28,new ShopButton());
        button.put(37,new NewsButton());
        //Games
        button.put(12,new BedWarsButton());
        button.put(13,new ThePitButton());
        button.put(14,new DuelButton());
        button.put(15,new SkyWarsButton());
        button.put(39,new RPGButton());
        return button;
    }

    @Override
    public int getSize() {
        return 6 * 9;
    }
}
