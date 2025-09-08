package art.gatoartstudio.dayNightSync.domains.daily.application.port.in;

import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.model.DayTimeModel;

public interface SetupDayTimeUseCase {

    /**
     * Initial domain configuration.
     * @param dayTimeModel DTO that will have the basic settings to configure the domain.
     */
    void execute(DayTimeModel dayTimeModel);
}
