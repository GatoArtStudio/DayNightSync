package art.gatoartstudio.dayNightSync.domains.daily.domain.model;

import art.gatoartstudio.dayNightSync.domains.daily.domain.valueObject.TimeUpdateInterval;

import java.util.Optional;
import java.util.TimeZone;

public class DayTime {
    private TimeZone timeZone;
    private boolean timeEnable;
    private TimeUpdateInterval timeUpdateInterval;
    private Optional<String> timeWorldName;

    public DayTime(TimeZone timeZone, boolean timeEnable, TimeUpdateInterval timeUpdateInterval, String timeWorldName) {
        if (timeZone == null)
            throw new IllegalArgumentException("TimeZone cannot be null.");

        if (timeUpdateInterval == null)
            throw new IllegalArgumentException("TimeUpdateInterval cannot be null.");

        this.timeZone = timeZone;
        this.timeEnable = timeEnable;
        this.timeUpdateInterval = timeUpdateInterval;
        this.timeWorldName = Optional.ofNullable(timeWorldName);
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public boolean isTimeEnable() {
        return timeEnable;
    }

    public TimeUpdateInterval getTimeUpdateInterval() {
        return timeUpdateInterval;
    }

    public Optional<String> getTimeWorldName() {
        return timeWorldName;
    }

    public void setTimeEnable(boolean timeEnable) {
        this.timeEnable = timeEnable;
    }
}
