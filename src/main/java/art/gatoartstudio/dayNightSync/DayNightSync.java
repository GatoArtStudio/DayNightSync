package art.gatoartstudio.dayNightSync;

import art.gatoartstudio.dayNightSync.command.DayNightCommand;
import art.gatoartstudio.dayNightSync.config.ConfigManager;
import art.gatoartstudio.dayNightSync.helper.Log;
import art.gatoartstudio.dayNightSync.listener.ServerLoad;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DayNightSync extends JavaPlugin {
    private ConfigManager configManager;

    @Override
    public void onLoad() {
        // Plugin load logic
        Log.info("Initializing DayNightSync...");
        configManager = new ConfigManager(this, "config.yml");
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Log.info("DayNightSync has been enabled!");
        configManager.init();

        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Log.info("DayNightSync has been disabled.");
        configManager.save();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ServerLoad(this), this);
    }

    private void registerCommands() {
        DayNightCommand dayNightCommand = new DayNightCommand(this);
        Objects.requireNonNull(getCommand("daynight")).setExecutor(dayNightCommand);
        Objects.requireNonNull(getCommand("daynight")).setTabCompleter(dayNightCommand);
    }

    public static JavaPlugin getInstance() {
        return JavaPlugin.getPlugin(DayNightSync.class);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
