package com.rampagemc.permissions.service.migration;

import org.springframework.lang.NonNull;

public interface MigrateChangeLogService {

    /**
     * Passes the given dir path to the {@link LoadChangeLogService} and starts the migration process.
     * @param dir
     */
    void migrateFrom(@NonNull String dir);
}
