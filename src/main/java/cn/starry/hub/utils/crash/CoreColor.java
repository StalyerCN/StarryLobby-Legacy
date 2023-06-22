package cn.starry.hub.utils.crash;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoreColor {
    public static String colorCodes(String nonColoredText) {
        if (nonColoredText == null) {
            return nonColoredText;
        }
        if (nonColoredText.isEmpty()) {
            return nonColoredText;
        }
        if (nonColoredText.length() <= 0) {
            return nonColoredText;
        }
        if (nonColoredText == "") {
            return nonColoredText;
        }
        if (nonColoredText == " ") {
            return nonColoredText;
        }
        String coloredText = ChatColor.translateAlternateColorCodes('&', nonColoredText);
        return coloredText;
    }

    public static void line(Player player, String color) {
        player.sendMessage(color.length() > 0 ? CoreColor.colorCodes(color + "---------------------------------------------------") : color);
    }

    public static void message(Player player, String paramString) {
        player.sendMessage(CoreColor.colorCodes(paramString));
    }

    public static void message(CommandSender player, String paramString) {
        player.sendMessage(CoreColor.colorCodes(paramString));
    }

    public static String coloriseTextComponentString(String string) {
        if (string != null && string.length() != 0) {
            String localString = CoreColor.colorCodes(string.trim());
            String newString = "";
            String last = "�7";
            if (!localString.contains(" ")) {
                return localString;
            }
            String[] frases = localString.split(" ");
            for (int i = 0; i < frases.length; ++i) {
                String frase = frases[i].trim();
                newString = frase.startsWith("�") ? newString + " " + frase : newString + " " + last + frase;
                for (int j = 0; j < frase.length(); ++j) {
                    int m;
                    char c = frase.charAt(j);
                    int n = m = frase.length() > 2 ? (int)frase.charAt(j + 2) : 32;
                    if (c == '�' && m == 65533) {
                        last = "�" + frase.charAt(j + 1) + "�" + frase.charAt(j + 3);
                        j = 3;
                        continue;
                    }
                    if (c != '�') continue;
                    last = "�" + frase.charAt(j + 1);
                    j = 1;
                }
            }
            return newString.trim();
        }
        return " ";
    }

    public static List<String> rColorList(List<String> paramList) {
        ArrayList<String> s = new ArrayList<String>();
        s.addAll(paramList);
        for (int i = 0; i < s.size(); ++i) {
            String p = CoreColor.colorCodes((String)s.get(i));
            s.set(i, p);
        }
        return s;
    }

    public static List<String> getAlternateColorList() {
        ArrayList<String> c = new ArrayList<String>();
        c.add("&0");
        c.add("&1");
        c.add("&2");
        c.add("&3");
        c.add("&4");
        c.add("&5");
        c.add("&6");
        c.add("&7");
        c.add("&8");
        c.add("&9");
        c.add("&a");
        c.add("&b");
        c.add("&c");
        c.add("&d");
        c.add("&e");
        c.add("&f");
        c.add("&k");
        c.add("&l");
        c.add("&m");
        c.add("&n");
        c.add("&o");
        c.add("&r");
        c.add("&A");
        c.add("&B");
        c.add("&C");
        c.add("&D");
        c.add("&E");
        c.add("&F");
        c.add("&K");
        c.add("&L");
        c.add("&M");
        c.add("&N");
        c.add("&O");
        c.add("&R");
        return c;
    }

    public static List<String> getNativeColorList() {
        ArrayList<String> c = new ArrayList<String>();
        c.add("§0");
        c.add("§1");
        c.add("§2");
        c.add("§3");
        c.add("§4");
        c.add("§5");
        c.add("§6");
        c.add("§7");
        c.add("§8");
        c.add("§9");
        c.add("§a");
        c.add("§b");
        c.add("§c");
        c.add("§d");
        c.add("§e");
        c.add("§f");
        c.add("§k");
        c.add("§l");
        c.add("§m");
        c.add("§n");
        c.add("§o");
        c.add("§r");
        c.add("§A");
        c.add("§B");
        c.add("§C");
        c.add("§D");
        c.add("§E");
        c.add("§F");
        c.add("§K");
        c.add("§L");
        c.add("§M");
        c.add("§N");
        c.add("§O");
        c.add("§R");
        return c;
    }

    public static String clearColor(String coloredText) {
        String nonColoredText = coloredText;
        for (String color : CoreColor.getAlternateColorList()) {
            nonColoredText = nonColoredText.replaceAll(color, "");
        }
        for (String color : CoreColor.getNativeColorList()) {
            nonColoredText = nonColoredText.replaceAll(color, "");
        }
        return nonColoredText.trim();
    }
}
