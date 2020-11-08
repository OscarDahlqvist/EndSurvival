package cu.wilux.endsurvival;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static Logger logger;

    // Fired when plugin is first enabled
    @Override
    public void onEnable() {
        logger = getLogger();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GrindstoneEvent(), this);

    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
