package cn.starry.hub.parm;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;

public class RegisterListener {

    public static void registerListeners(Plugin plugin, String packageName) {
        try {
            Reflections reflections = new Reflections(packageName);
            Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(AutoRegister.class);

            for (Class<?> annotatedClass : annotatedClasses) {
                if (Listener.class.isAssignableFrom(annotatedClass)) {
                    Constructor<?> constructor = annotatedClass.getConstructor();
                    Listener listener = (Listener) constructor.newInstance();
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
