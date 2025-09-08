package art.gatoartstudio.dayNightSync.domains.daily.domain.valueObject;

public record TimeUpdateInterval(int seconds) {
    public TimeUpdateInterval {
        if (seconds < 1) {
            throw new IllegalArgumentException("TimeUpdateInterval must be at least 1 second.");
        }
    }
}
