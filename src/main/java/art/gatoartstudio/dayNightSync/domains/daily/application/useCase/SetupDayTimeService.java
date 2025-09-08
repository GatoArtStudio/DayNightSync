package art.gatoartstudio.dayNightSync.domains.daily.application.useCase;

import art.gatoartstudio.dayNightSync.domains.daily.application.port.in.SetupDayTimeUseCase;
import art.gatoartstudio.dayNightSync.domains.daily.domain.model.DayTime;
import art.gatoartstudio.dayNightSync.domains.daily.domain.port.out.DayTimeRepository;
import art.gatoartstudio.dayNightSync.domains.daily.domain.valueObject.TimeUpdateInterval;
import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.model.DayTimeModel;

public class SetupDayTimeService implements SetupDayTimeUseCase {
    private final DayTimeRepository repository;

    public SetupDayTimeService(DayTimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DayTimeModel dayTimeModel) {
        if (dayTimeModel == null)
            throw new IllegalArgumentException("The model cannot be null.");

        TimeUpdateInterval timeUpdateInterval = new TimeUpdateInterval(dayTimeModel.timeUpdateInterval());
        DayTime dayTime = new DayTime(
                dayTimeModel.timeZone(),
                dayTimeModel.timeEnable(),
                timeUpdateInterval,
                dayTimeModel.timeWorldName().isPresent() ? dayTimeModel.timeWorldName().get() : null
        );

        repository.save(dayTime);
    }
}
