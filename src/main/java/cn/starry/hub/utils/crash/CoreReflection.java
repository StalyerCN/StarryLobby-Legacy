package cn.starry.hub.utils.crash;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CoreReflection {
    public static Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        return CoreReflection.getClass("net.minecraft.server." + version + "." + name);
    }

    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke((Object)player, new Object[0]);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", CoreReflection.getNMSClass("Packet")).invoke(playerConnection, packet);
        }
        catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        }
        catch (ClassNotFoundException var2) {
            var2.printStackTrace();
            return null;
        }
    }
}
