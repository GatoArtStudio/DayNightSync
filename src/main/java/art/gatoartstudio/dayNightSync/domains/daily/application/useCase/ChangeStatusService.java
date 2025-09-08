package art.gatoartstudio.dayNightSync.domains.daily.application.useCase;

import art.gatoartstudio.dayNightSync.domains.daily.application.port.in.ChangeStatusDayTimeUseCase;
import art.gatoartstudio.dayNightSync.domains.daily.domain.model.DayTime;
import art.gatoartstudio.dayNightSync.domains.daily.domain.port.out.DayTimeRepository;

import java.util.Optional;

public class ChangeStatusService implements ChangeStatusDayTimeUseCase {
    private final DayTimeRepository repository;

    public ChangeStatusService(DayTimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(boolean status) {
        Optional<DayTime> dayTime = repository.load();
        if (dayTime.isPresent()) {
            DayTime tempDayTime = dayTime.get();
            tempDayTime.setTimeEnable(status);

            repository.save(tempDayTime);
            return true;
        } else {
            return false;
        }
    }
}
