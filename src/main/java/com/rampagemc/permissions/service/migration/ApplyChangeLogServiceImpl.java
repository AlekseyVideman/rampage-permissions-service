package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.dto.LuckPermsChangeLogDto;
import com.rampagemc.permissions.exception.LuckPermsMigrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class ApplyChangeLogServiceImpl implements ApplyChangeLogService {

    private final LuckPermsService luckPermsService;
    private final ChangeLogDataBaseService changeLogDataBaseService;
    private final CommitChangeLogService commitChangeLogService;

    @Override
    public void apply(@NonNull LuckPermsChangeLogDto changeLogDto) {
        final var pastCommands = new LinkedList<>(changeLogDataBaseService.getAllPastCommands(changeLogDto));
        final var newCommands = changeLogDto.getLuckPermsCommands();

        changeLogDto.setLuckPermsCommands(pastCommands);

        final var index = pastCommands.size() < newCommands.size() ? pastCommands.size() : 0;
        for (int i = index; i < newCommands.size(); i++) {

            final var command = newCommands.get(i);
            final var isCommandFailed = !luckPermsService.execute(command);

            if (isCommandFailed) {
                throw new LuckPermsMigrationException(String.format("Command '%s' on line '%s' in '%s' cannot be executed.",
                        command.getCommand(),
                        command.getLineNumber(),
                        changeLogDto.getId()));
            }

            commitChangeLogService.commit(changeLogDto.getId(), command.getLineNumber(), command.getCommand());
        }
    }
}
