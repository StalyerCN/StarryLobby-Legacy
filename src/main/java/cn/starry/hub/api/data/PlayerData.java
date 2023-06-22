package cn.starry.hub.api.data;

import cn.starry.hub.api.enums.PlayerState;
import cn.starry.hub.api.enums.ProfileState;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerData {
    public static HashMap<Player, PlayerState> EDIT;
    public static HashMap<Player, PlayerState> VANSIH;
    public static HashMap<Player, ProfileState> PROFILE;

    static {
        PlayerData.EDIT = new HashMap<Player, PlayerState>();
        PlayerData.VANSIH = new HashMap<Player, PlayerState>();
        PlayerData.PROFILE = new HashMap<Player, ProfileState>();
    }
}
