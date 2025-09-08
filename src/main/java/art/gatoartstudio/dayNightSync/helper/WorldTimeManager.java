package art.gatoartstudio.dayNightSync.helper;

import art.gatoartstudio.dayNightSync.domains.daily.application.useCase.LoadDayTimeService;
import art.gatoartstudio.dayNightSync.domains.daily.domain.port.out.DayTimeRepository;
import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.model.DayTimeModel;
import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.repository.RepositoryFactory;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Calendar;
import java.util.Optional;

public class WorldTimeManager {
    private static BukkitTask task;

    public static void setupWorldTime(JavaPlugin plugin) {
        DayTimeRepository repository = RepositoryFactory.getInstance().getDayTimeRepository();
        Optional<DayTimeModel> dayTimeModel = new LoadDayTimeService(repository).execute();

        if (dayTimeModel.isPresent()) {
            DayTimeModel tempDayTimeModel = dayTimeModel.get();

            if (tempDayTimeModel.timeEnable()) {
                // Enabled
                if (task != null && !task.isCancelled())
                    task.cancel();

                task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance(tempDayTimeModel.timeZone());

                        // If it is a single world set up
                        if (tempDayTimeModel.timeWorldName().isPresent()) {
                            World world = plugin.getServer().getWorld(tempDayTimeModel.timeWorldName().get());

                            if (world == null) {
                                return;
                            }

                            if (world.getEnvironment().equals(World.Environment.NORMAL)) {

                                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                                setTimeToWorld(calendar, world);
                                return;
                            }
                        }

                        // Sets the time for all worlds
                        plugin.getServer().getWorlds().stream()
                                .filter(world -> world.getEnvironment() == World.Environment.NORMAL)
                                .forEach(world -> {
                                    world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                                    setTimeToWorld(calendar, world);
                        });
                    }
                }.runTaskTimer(plugin, 0L, tempDayTimeModel.timeUpdateInterval() * 20L);
                Log.success("The time of all the worlds was set, time zone: " + tempDayTimeModel.timeZone().getID());
            } else {
                // Disabled
                if (task != null && !task.isCancelled())
                    task.cancel();

                plugin.getServer().getWorlds().stream()
                        .filter(world -> world.getEnvironment() == World.Environment.NORMAL)
                        .forEach(world -> world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true));

                Log.warning("The plugin has not been enabled from the plugin settings.");
            }
        }
    }

    private static void setTimeToWorld(Calendar calendar, World world) {
        long time = (1000L * calendar.get(Calendar.HOUR_OF_DAY)) + (16 * (calendar.get(Calendar.MINUTE) + 1)) - 6000;
        Log.info("Tiempo ctual en el juego: " + time + ", tiempo: " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        world.setTime(time);
    }
}
