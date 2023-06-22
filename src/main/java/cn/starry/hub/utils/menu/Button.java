package cn.starry.hub.utils.menu;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Button {

    public static Button placeholder(final Material material, final byte data, String... title) {
        return (new Button() {
            public ItemStack getButtonItem(Player player) {
                ItemStack it = new ItemStack(material, 1, data);
                ItemMeta meta = it.getItemMeta();

                meta.setDisplayName(StringUtils.join(title));
                it.setItemMeta(meta);

                return it;
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {

            }
        });
    }

    public static void playNeutral(Player player) {
        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 20F, 1F);
    }

    public abstract ItemStack getButtonItem(Player player);

    public abstract void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem);

    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return (true);
    }

    public boolean shouldUpdate(Player player, int slot, ClickType clickType) {
        return (false);
    }

}