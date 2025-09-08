package art.gatoartstudio.dayNightSync.domains.daily.infrastructure.repository;

import art.gatoartstudio.dayNightSync.domains.daily.domain.port.out.DayTimeRepository;
import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.repository.volatiles.InMemoryDayTImeRepository;

public class RepositoryFactory {
    private static volatile RepositoryFactory instance;

    private final DayTimeRepository dayTimeRepository;

    private RepositoryFactory() {
        dayTimeRepository = new InMemoryDayTImeRepository();
    }

    public static RepositoryFactory getInstance() {

        RepositoryFactory result = instance;
        if (result != null)
            return result;

        synchronized (RepositoryFactory.class) {
            if (instance == null)
                instance = new RepositoryFactory();

            return  instance;
        }
    }

    public DayTimeRepository getDayTimeRepository() {
        return dayTimeRepository;
    }
}
