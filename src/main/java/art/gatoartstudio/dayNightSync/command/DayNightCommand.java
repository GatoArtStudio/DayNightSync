package art.gatoartstudio.dayNightSync.command;

import art.gatoartstudio.dayNightSync.DayNightSync;
import art.gatoartstudio.dayNightSync.domains.daily.application.port.in.ChangeStatusDayTimeUseCase;
import art.gatoartstudio.dayNightSync.domains.daily.application.useCase.ChangeStatusService;
import art.gatoartstudio.dayNightSync.domains.daily.domain.port.out.DayTimeRepository;
import art.gatoartstudio.dayNightSync.domains.daily.infrastructure.repository.RepositoryFactory;
import art.gatoartstudio.dayNightSync.helper.WorldTimeManager;
import com.sun.source.tree.BreakTree;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DayNightCommand implements TabExecutor {
    private final DayNightSync dayNightSync;

    public DayNightCommand(DayNightSync dayNightSync) {
        this.dayNightSync = dayNightSync;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender.hasPermission("daynightsync.admin"))) return false;

        if (strings.length == 0)
            return false;

        switch (strings[0]) {
            case "reload":
                dayNightSync.getConfigManager().reload();
                WorldTimeManager.setupWorldTime(dayNightSync);
                commandSender.sendMessage(
                        Component.text("Se recargo correctamente el sistema de tiempo en el juego.")
                                .color(TextColor.color(0, 255, 0))
                );
                return true;
            case "enable":
                boolean changeStatusEnable = changeStatusDayTime(true);
                if (changeStatusEnable) {
                    WorldTimeManager.setupWorldTime(dayNightSync);
                    dayNightSync.getConfigManager().save();
                    commandSender.sendMessage(
                            Component.text("Se habilito correctamente el sistema de tiempo en el juego.")
                                    .color(TextColor.color(0, 255, 0))
                    );
                } else {
                    commandSender.sendMessage(
                            Component.text("Error al cambiar el estado del sistema de tiempo en el juego.")
                                    .color(TextColor.color(255, 0, 0))
                    );
                }
                return true;
            case "disable":
                boolean changeStatusDisable = changeStatusDayTime(false);
                if (changeStatusDisable) {
                    WorldTimeManager.setupWorldTime(dayNightSync);
                    dayNightSync.getConfigManager().save();
                    commandSender.sendMessage(
                            Component.text("Se deshabilito correctamente el sistema de tiempo en el juego.")
                                    .color(TextColor.color(0, 255, 0))
                    );
                } else {
                    commandSender.sendMessage(
                            Component.text("Error al cambiar el estado del sistema de tiempo en el juego.")
                                    .color(TextColor.color(255, 0, 0))
                    );
                }
                return true;
            default:
                return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> args = List.of("reload", "enable", "disable");

        if (!(commandSender.hasPermission("daynightsync.admin"))) return Collections.emptyList();

        if (strings.length == 1)
            return new ArrayList<>(args).stream().sorted(Comparator.naturalOrder()).toList();

        return Collections.emptyList();
    }

    private boolean changeStatusDayTime(boolean status) {
        DayTimeRepository dayTimeRepository = RepositoryFactory.getInstance().getDayTimeRepository();
        ChangeStatusDayTimeUseCase changeStatusDayTimeUseCase = new ChangeStatusService(dayTimeRepository);
        return changeStatusDayTimeUseCase.execute(status);
    }
}
