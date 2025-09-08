package art.gatoartstudio.dayNightSync.domains.daily.application.port.in;

import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.model.DayTimeModel;

import java.util.Optional;

public interface LoadDayTImeUseCase {
    Optional<DayTimeModel> execute();
}
