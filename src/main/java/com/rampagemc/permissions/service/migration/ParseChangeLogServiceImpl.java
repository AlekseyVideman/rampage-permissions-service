package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.domain.entity.LuckPermsChangeLog;
import com.rampagemc.permissions.dto.LuckPermsChangeLogDto;
import com.rampagemc.permissions.domain.entity.LuckPermsCommand;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ParseChangeLogServiceImpl implements ParseChangeLogService {
    
    @Override
    public LuckPermsChangeLogDto parse(long changeLogFileId, @NonNull String changeLogFileContent) {
        final var changeLog = new LuckPermsChangeLogDto(changeLogFileId);
        final var stringCommands = changeLogFileContent.lines()
                .filter(s -> !s.startsWith("#"))
                .filter(s -> !s.isBlank())
                .toList();

        final var luckPermsChangeLog = new LuckPermsChangeLog().setId(changeLog.getId());
        final List<LuckPermsCommand> luckPermsCommands = new LinkedList<>();
        for (int i = 0; i < stringCommands.size(); i++) {
            final var command = new LuckPermsCommand()
                    .setChangelog(luckPermsChangeLog)
                    .setLineNumber((long) i)
                    .setCommand(stringCommands.get(i));
            luckPermsCommands.add(command);
        }

        return changeLog.setLuckPermsCommands(luckPermsCommands);
    }
}
