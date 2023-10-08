package com.rampagemc.permissions.service.migration;

public interface CommitChangeLogService {

    /**
     * Saves in a database successfully applied changelog.
     */
    void commit(long changeLogId, long commandLine, String command);
}
