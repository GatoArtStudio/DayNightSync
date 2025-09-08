package art.gatoartstudio.dayNightSync.domains.daily.infrastructure.repository.volatiles;

import art.gatoartstudio.dayNightSync.domains.daily.domain.model.DayTime;
import art.gatoartstudio.dayNightSync.domains.daily.domain.port.out.DayTimeRepository;

import java.util.Optional;

public class InMemoryDayTImeRepository implements DayTimeRepository {
    private Optional<DayTime> data = Optional.empty();

    @Override
    public Optional<DayTime> load() {
        return data;
    }

    @Override
    public void save(DayTime dayTime) {
        data = Optional.of(dayTime);
    }
}
