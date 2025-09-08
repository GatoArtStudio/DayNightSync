package art.gatoartstudio.dayNightSync.domains.daily.application.port.in;

public interface ChangeStatusDayTimeUseCase {
    boolean execute(boolean status);
}
