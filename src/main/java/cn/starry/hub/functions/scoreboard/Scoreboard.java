package cn.starry.hub.functions.scoreboard;

import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.api.enums.ProfileState;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.scoreboard.AssembleAdapter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scoreboard implements AssembleAdapter {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
    private final DecimalFormat numformatInt = new DecimalFormat("0");
    private final DecimalFormat numFormat = new DecimalFormat("0.0");
    private final DecimalFormat numFormatTwo = new DecimalFormat("0.00");
    private final DecimalFormat df = new DecimalFormat(",###,###,###,###");
    private final List<String> animationTitle =
            Arrays.asList(ColorUtil.color("&b&l主大厅"));
    private long lastAnimationTime = 0;
    private int animationTick = 0;

    @Override
    public String getTitle(Player player) {

        String text = animationTitle.get(animationTick);
        if (System.currentTimeMillis() - lastAnimationTime >= 125) {
            animationTick++;
            if (animationTick + 1 >= animationTitle.size()) {
                animationTick = 0;
            }
            lastAnimationTime = System.currentTimeMillis();
        }

        return text;

    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();
        boolean profile = PlayerData.PROFILE.get(player) == ProfileState.LOADED;
        if (!profile) {
            lines.add("");
            lines.add("  &f权限 &7读取中");
            lines.add("  &f等级 &3读取中");
            lines.add("");
            lines.add("  &f玩家数 &a读取中");
            lines.add("");
            lines.add("  &f星尘 &b读取中");
            lines.add("  &f神秘箱 &e读取中");
            lines.add("  &f神秘之尘 &d读取中");
            lines.add("");
            return lines;
        } else {
            lines.add("");
            lines.add(PlaceholderAPI.setPlaceholders(player,"  &f权限 %luckperms_primary_group_name%"));
            lines.add("  &f等级 &3NULL");
            lines.add("");
            lines.add(PlaceholderAPI.setPlaceholders(player,"  &f玩家数 &a%bungee_total%"));
            lines.add("");
            lines.add(PlaceholderAPI.setPlaceholders(player,"  &f星尘 &b%playerpoints_points%"));
            lines.add(PlaceholderAPI.setPlaceholders(player,"  &f神秘箱 &e%gadgetsmenu_mystery_boxes%"));
            lines.add(PlaceholderAPI.setPlaceholders(player,"  &f神秘之尘 &d%gadgetsmenu_mystery_dust%"));
            lines.add("");
            return lines;
        }
    }
}
