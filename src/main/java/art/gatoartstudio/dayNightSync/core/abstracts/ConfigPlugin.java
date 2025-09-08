package art.gatoartstudio.dayNightSync.core.abstracts;

import art.gatoartstudio.dayNightSync.helper.Log;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class ConfigPlugin {
    protected JavaPlugin plugin;
    protected File configFile;
    protected FileConfiguration config;
    protected String fileName;

    public ConfigPlugin(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.configFile = new File(plugin.getDataFolder(), fileName);
        this.config = plugin.getConfig();
    }

    /**
     * Saves the default configuration to the configuration file if it does not already exist.
     * Logs success or error messages based on the outcome of the save operation.
     * @return true if the configuration was created, false if it already existed
     */
    public boolean saveDefaults() {
        if (configFile.exists()) return false;

        plugin.saveResource(configFile.getName(), false);

        return true;
    }

    /**
     * Returns the configuration object.
     * @return the configuration object
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Saves the current configuration to the configuration file.
     * Logs success or error messages based on the outcome of the save operation.
     */
    public void save() {
        try {
            config.save(configFile);
        } catch (Exception e) {
            Log.error(e);
        }
    }

    /**
     * This method is called when the plugin is loaded and it is necessary to load the configuration.
     * It is called after the plugin has been enabled and before the plugin is fully initialized.
     */
    public abstract void load();

    /**
     * This method is called when the plugin is fully initialized and it is necessary to initialize
     * the configuration. It is called after the plugin has been enabled and after the plugin has been
     * loaded.
     */
    public abstract void init();
}
