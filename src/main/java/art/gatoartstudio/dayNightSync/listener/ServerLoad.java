package art.gatoartstudio.dayNightSync.listener;

import art.gatoartstudio.dayNightSync.helper.WorldTimeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerLoad implements Listener {
    private final JavaPlugin plugin;

    public ServerLoad(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        WorldTimeManager.setupWorldTime(plugin);
    }
}
