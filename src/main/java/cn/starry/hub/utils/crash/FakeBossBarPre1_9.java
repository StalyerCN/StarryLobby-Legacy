package cn.starry.hub.utils.crash;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class FakeBossBarPre1_9 implements FakeBossBar {
    private int ID = new Random().nextInt();
    private String name;
    private float health = 0.0f;
    private final float maxHealth = 300.0f;
    private Object PacketPlayOutSpawnEntityLivin;
    private Player player;
    private boolean isProgress = false;
    private Class<?> EntityClass = CoreReflection.getNMSClass("Entity");
    private Class<?> DataWatcherClass = CoreReflection.getNMSClass("DataWatcher");
    private Class<?> PacketPlayOutSpawnEntityLivinClass = CoreReflection.getNMSClass("PacketPlayOutSpawnEntityLiving");
    private boolean send = false;

    public FakeBossBarPre1_9(Player player, String mensaje, float pro) {
        this.name = mensaje;
        this.player = player;
        if (pro > 0.0f) {
            this.isProgress = true;
            this.health = pro;
        } else {
            this.isProgress = false;
            this.health = 300.0f;
        }
        try {
            this.createDragon();
        }
        catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    private void createDragon() throws Exception {
        this.PacketPlayOutSpawnEntityLivin = this.PacketPlayOutSpawnEntityLivinClass.newInstance();
        Location loc = this.makeLocation(this.player.getLocation());
        Field aField = this.PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("a");
        aField.setAccessible(true);
        aField.set(this.PacketPlayOutSpawnEntityLivin, this.ID);
        Field bField = this.PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("b");
        bField.setAccessible(true);
        bField.set(this.PacketPlayOutSpawnEntityLivin, 64);
        Field cField = this.PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("c");
        cField.setAccessible(true);
        cField.set(this.PacketPlayOutSpawnEntityLivin, loc.getBlockX() * 32);
        Field dField = this.PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("d");
        dField.setAccessible(true);
        dField.set(this.PacketPlayOutSpawnEntityLivin, loc.getBlockY() * 32);
        Field eField = this.PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("e");
        eField.setAccessible(true);
        eField.set(this.PacketPlayOutSpawnEntityLivin, loc.getBlockZ() * 32);
        Field iField = this.PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("i");
        iField.setAccessible(true);
        iField.set(this.PacketPlayOutSpawnEntityLivin, (byte)((int)loc.getYaw() * 256 / 360));
        Field jField = this.PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("j");
        jField.setAccessible(true);
        jField.set(this.PacketPlayOutSpawnEntityLivin, (byte)((int)loc.getPitch() * 256 / 360));
        Field kField = this.PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("k");
        kField.setAccessible(true);
        kField.set(this.PacketPlayOutSpawnEntityLivin, (byte)((int)loc.getPitch() * 256 / 360));
        Field lField = this.PacketPlayOutSpawnEntityLivin.getClass().getDeclaredField("l");
        lField.setAccessible(true);
        lField.set(this.PacketPlayOutSpawnEntityLivin, this.getWatcher());
    }

    @Override
    public void setName(String paramString) {
        try {
            this.name = paramString;
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            Class<?> PacketPlayOutEntityDestroy = CoreReflection.getNMSClass("PacketPlayOutEntityDestroy");
            Object packet = PacketPlayOutEntityDestroy.newInstance();
            Field a = PacketPlayOutEntityDestroy.getDeclaredField("a");
            a.setAccessible(true);
            a.set(packet, new int[]{this.ID});
            CoreReflection.sendPacket(this.player, packet);
            this.send = false;
        }
        catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    @Override
    public void send() {
        try {
            CoreReflection.sendPacket(this.player, this.PacketPlayOutSpawnEntityLivin);
            this.teleport();
            this.send = true;
        }
        catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    private void teleport() throws Exception {
        Location loc = this.makeLocation(this.player.getLocation());
        Constructor<?> constructorPacketPlayOutEntityTeleport = CoreReflection.getNMSClass("PacketPlayOutEntityTeleport").getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Byte.TYPE, Byte.TYPE, Boolean.TYPE);
        Object packet = constructorPacketPlayOutEntityTeleport.newInstance(this.ID, loc.getBlockX() * 32, loc.getBlockY() * 32, loc.getBlockZ() * 32, (byte)((int)loc.getYaw() * 256 / 360), (byte)((int)loc.getPitch() * 256 / 360), false);
        CoreReflection.sendPacket(this.player, packet);
    }

    private void sendMetaPacket() throws Exception {
        Constructor<?> constructorMeta = CoreReflection.getNMSClass("PacketPlayOutEntityMetadata").getConstructor(Integer.TYPE, this.DataWatcherClass, Boolean.TYPE);
        Object packet = constructorMeta.newInstance(this.ID, this.getWatcher(), true);
        CoreReflection.sendPacket(this.player, packet);
    }

    public Object getWatcher() {
        Object watcher = null;
        try {
            Object entyti = null;
            watcher = this.DataWatcherClass.getConstructor(this.EntityClass).newInstance(entyti);
            Method a = this.DataWatcherClass.getMethod("a", Integer.TYPE, Object.class);
            a.invoke(watcher, 0, 32);
            a.invoke(watcher, 6, Float.valueOf(this.isProgress ? this.health : 300.0f));
            a.invoke(watcher, 7, 0);
            a.invoke(watcher, 8, 0);
            a.invoke(watcher, 10, this.name);
            a.invoke(watcher, 2, this.name);
            a.invoke(watcher, 11, 1);
            a.invoke(watcher, 17, new Integer(0));
            a.invoke(watcher, 18, new Integer(0));
            a.invoke(watcher, 19, new Integer(0));
            a.invoke(watcher, 20, new Integer(1000));
        }
        catch (Exception var4) {
            var4.printStackTrace();
        }
        return watcher;
    }

    private static Location getDragonLocation(Location loc) {
        float pitch = loc.getPitch();
        if (pitch >= 55.0f) {
            loc.add(0.0, -300.0, 0.0);
        } else if (pitch <= -55.0f) {
            loc.add(0.0, 300.0, 0.0);
        } else {
            loc = loc.getBlock().getRelative(FakeBossBarPre1_9.getDirection(loc), Bukkit.getServer().getViewDistance() * 16).getLocation();
        }
        return loc;
    }

    protected Location makeLocation(Location base) {
        return base.getDirection().multiply(32).add(base.toVector()).toLocation(base.getWorld());
    }

    private static BlockFace getDirection(Location loc) {
        float dir = Math.round(loc.getYaw() / 90.0f);
        if (dir != -4.0f && dir != 0.0f && dir != 4.0f) {
            if (dir != -1.0f && dir != 3.0f) {
                if (dir != -2.0f && dir != 2.0f) {
                    return dir != -3.0f && dir != 1.0f ? null : BlockFace.WEST;
                }
                return BlockFace.NORTH;
            }
            return BlockFace.EAST;
        }
        return BlockFace.SOUTH;
    }

    private void update() {
        try {
            if (this.isProgress) {
                this.sendMetaPacket();
            }
            this.teleport();
        }
        catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public boolean isSend() {
        return this.send;
    }

    @Override
    public float getProgress() {
        return this.health;
    }

    @Override
    public void setProgress(float progress) {
        this.health = progress;
        this.update();
    }
}

