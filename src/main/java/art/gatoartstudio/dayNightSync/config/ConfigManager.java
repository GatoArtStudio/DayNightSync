package art.gatoartstudio.dayNightSync.config;

import art.gatoartstudio.dayNightSync.core.abstracts.ConfigPlugin;
import art.gatoartstudio.dayNightSync.domains.daily.application.useCase.LoadDayTimeService;
import art.gatoartstudio.dayNightSync.domains.daily.application.useCase.SetupDayTimeService;
import art.gatoartstudio.dayNightSync.domains.daily.domain.port.out.DayTimeRepository;
import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.model.DayTimeModel;
import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.repository.RepositoryFactory;
import art.gatoartstudio.dayNightSync.helper.Log;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.TimeZone;

public class ConfigManager extends ConfigPlugin {
    public ConfigManager(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
    }

    public void reload() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();

        load();
    }

    @Override
    public void load() {
        String timeZone = getConfig().getString("time.zone", "America/Bogota");
        boolean timeEnable = getConfig().getBoolean("time.enable", false);
        int timeUpdateInterval = getConfig().getInt("time.update_interval", 5);
        String timeWorldName = getConfig().getString("time.world_name", null);

        DayTimeModel dayTimeModel = new DayTimeModel(
                TimeZone.getTimeZone(timeZone),
                timeEnable,
                timeUpdateInterval,
                Optional.ofNullable(timeWorldName)
        );

        DayTimeRepository repository = RepositoryFactory.getInstance().getDayTimeRepository();
        new SetupDayTimeService(repository).execute(dayTimeModel);
        Log.info("Plugin settings loaded.");
    }

    @Override
    public void init() {
        saveDefaults();
        load();
    }

    @Override
    public void save() {
        DayTimeRepository repository = RepositoryFactory.getInstance().getDayTimeRepository();
        Optional<DayTimeModel> dayTimeModel = new LoadDayTimeService(repository).execute();

        if (dayTimeModel.isPresent()) {
            DayTimeModel tempDayTimeModel = dayTimeModel.get();
            getConfig().set("time.zone", tempDayTimeModel.timeZone().getID());
            getConfig().set("time.enable", tempDayTimeModel.timeEnable());
            getConfig().set("time.update_interval", tempDayTimeModel.timeUpdateInterval());
            if (tempDayTimeModel.timeWorldName().isPresent()) {
                getConfig().set("time.world_name", tempDayTimeModel.timeWorldName().get());
            }
        }

        super.save();
        Log.info("The plugin settings were saved successfully.");
    }
}
