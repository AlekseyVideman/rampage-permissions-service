package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.domain.entity.LuckPermsChangeLog;
import com.rampagemc.permissions.domain.entity.LuckPermsCommand;
import com.rampagemc.permissions.repository.ChangeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommitChangeLogServiceImpl implements CommitChangeLogService {

    private final ChangeLogRepository changeLogRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void commit(long changeLogId, long commandLine, String stringCommand) {
        final var managedChangeLog = this.changeLogRepository.findById(changeLogId).orElseGet(() -> {
            final var lpChangeLog = new LuckPermsChangeLog().setId(changeLogId);
            return changeLogRepository.save(lpChangeLog);
        });

        final var command = new LuckPermsCommand()
                .setChangelog(managedChangeLog)
                .setLineNumber(commandLine)
                .setCommand(stringCommand)
                .setExecutedTime(LocalDateTime.now());
        managedChangeLog.getLuckPermsCommands().add(command);
    }
}
