package com.rampagemc.permissions.service.migration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrateChangeLogServiceImpl implements MigrateChangeLogService {

    private final LoadChangeLogService loadChangeLogService;
    private final ParseChangeLogService parseChangeLogService;
    private final ApplyChangeLogService applyChangeLogService;
    private final CommitChangeLogService commitChangeLogService;
    private final ValidateChangeLogService validateChangeLogService;

    @Override
    public void migrateFrom(@NonNull String dir) {
        final var changeLogsDataMap = loadChangeLogService.loadChangeLogsDataMap(dir);

        for (final var changeLogData : changeLogsDataMap.entrySet()) {
            final var changeLog = parseChangeLogService.parse(changeLogData.getKey(), changeLogData.getValue());

            log.info("Processing changelog #{}", changeLog.getId());

            validateChangeLogService.validate(changeLog);
            applyChangeLogService.apply(changeLog);
        }
    }
}
