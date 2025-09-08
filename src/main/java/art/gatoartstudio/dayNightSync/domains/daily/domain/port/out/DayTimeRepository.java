package art.gatoartstudio.dayNightSync.domains.daily.domain.port.out;

import art.gatoartstudio.dayNightSync.domains.daily.domain.model.DayTime;

import java.util.Optional;

public interface DayTimeRepository {
    Optional<DayTime> load();
    void save(DayTime dayTime);
}
