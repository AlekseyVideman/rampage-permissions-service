package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.dto.LuckPermsChangeLogDto;
import com.rampagemc.permissions.exception.LuckPermsMigrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateChangeLogServiceImpl implements ValidateChangeLogService {

    private final ChangeLogDataBaseService dataBaseChangeLogService;

    @Override
    public void validate(@NonNull LuckPermsChangeLogDto changeLogDto) {
        final var pastChangeLogOptional = dataBaseChangeLogService.load(changeLogDto);

        if (pastChangeLogOptional.isEmpty()) return;

        final var pastChangeLog = pastChangeLogOptional.get();
        final var newCommands = changeLogDto.getLuckPermsCommands();
        final var pastCommands = pastChangeLog.getLuckPermsCommands();

        if (newCommands.size() < pastCommands.size()) {
            throw new LuckPermsMigrationException(String.format("ChangeLog '%s' is less than it`s DB version",
                    changeLogDto.getId()));
        }

        for (int i = 0; i < pastCommands.size(); i++) {
            final var leftCommand = newCommands.get(i);
            final var rightCommand = pastCommands.get(i);

            if (!leftCommand.getCommand().equals(rightCommand.getCommand())) {
                throw new LuckPermsMigrationException(String.format("Commands in changelog '%s' aren't equal: '%s' != '%s'",
                        changeLogDto.getId(),
                        leftCommand.getCommand(),
                        rightCommand.getCommand()));
            }
        }
    }
}
