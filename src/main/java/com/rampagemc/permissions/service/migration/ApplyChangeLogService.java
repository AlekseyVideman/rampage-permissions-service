package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.dto.LuckPermsChangeLogDto;
import org.springframework.lang.NonNull;

public interface ApplyChangeLogService {

    /**
     * Applies given {@link LuckPermsChangeLogDto} to a in-memory storage.
     * @param changeLog
     */
    void apply(@NonNull LuckPermsChangeLogDto changeLog);
}
