package art.gatoartstudio.dayNightSync.domains.daily.application.useCase;

import art.gatoartstudio.dayNightSync.domains.daily.application.port.in.LoadDayTImeUseCase;
import art.gatoartstudio.dayNightSync.domains.daily.domain.model.DayTime;
import art.gatoartstudio.dayNightSync.domains.daily.domain.port.out.DayTimeRepository;
import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.model.DayTimeModel;

import java.util.Optional;

public class LoadDayTimeService implements LoadDayTImeUseCase {
    private final DayTimeRepository repository;

    public LoadDayTimeService(DayTimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DayTimeModel> execute() {
        Optional<DayTime> dayTime = repository.load();

        if (dayTime.isPresent()) {
            DayTime tempDayTime = dayTime.get();
            DayTimeModel dayTimeModel = new DayTimeModel(
                    tempDayTime.getTimeZone(),
                    tempDayTime.isTimeEnable(),
                    tempDayTime.getTimeUpdateInterval().seconds(),
                    tempDayTime.getTimeWorldName()
            );
            return Optional.of(dayTimeModel);
        }

        return Optional.empty();
    }
}
