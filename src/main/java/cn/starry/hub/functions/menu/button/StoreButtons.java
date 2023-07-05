package cn.starry.hub.functions.menu.button;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StoreButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack StarRank(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &f样式 &7『&b星烁&7』 &f" + player.getName() + " ");
        lores.add(" ");
        lores.add(" &f大厅飞行 &a&l✓ ");
        lores.add(" &f喊话所需时间缩短 &a&l✓ ");
        lores.add(" &f部分玩家设置解锁 &a&l✓ ");
        lores.add(" ");
        lores.add(" &f售价 &b150 &f星尘 ");
        lores.add(" ");
        if (player.hasPermission("group.star")) {
            lores.add(" &7剩余时长");
            lores.add(PlaceholderAPI.setPlaceholders(player, " &7%luckperms_group_expiry_time_star% "));
            lores.add(" ");
        } else {
            lores.add(" &7点击获取星烁 ");
            lores.add(" &7&n有效期 30 天 ");
            lores.add(" ");
        }

        item = new ItemBuilder(Material.NAME_TAG).name(ColorUtil.color("&b* &f星烁 &7(月付)")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
