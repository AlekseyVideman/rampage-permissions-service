package com.rampagemc.permissions.service.migration;

import org.springframework.lang.NonNull;

import java.util.Map;

public interface LoadChangeLogService {

    /**
     * Loads txt changelog files from given classpath directory, and returns its data in map.
     * @param dir
     * @return data map
     */
    Map<Long, String> loadChangeLogsDataMap(@NonNull String dir);
}
