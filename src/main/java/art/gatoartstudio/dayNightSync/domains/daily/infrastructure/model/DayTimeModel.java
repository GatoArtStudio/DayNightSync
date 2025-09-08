package art.gatoartstudio.dayNightSync.domains.daily.infrastructure.model;

import java.util.Optional;
import java.util.TimeZone;

public record DayTimeModel(TimeZone timeZone, boolean timeEnable, int timeUpdateInterval, Optional<String> timeWorldName) {
    public DayTimeModel {
        if (timeZone == null)
            throw new IllegalArgumentException("TimeZone cannot be null.");

        if (timeUpdateInterval < 1)
            throw new IllegalArgumentException("TimeUpdateInterval must be at least 1 second.");
    }
}
