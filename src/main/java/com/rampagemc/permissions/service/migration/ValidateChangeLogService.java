package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.dto.LuckPermsChangeLogDto;
import org.springframework.lang.NonNull;

public interface ValidateChangeLogService {

    /**
     * Validates given {@link LuckPermsChangeLogDto} before applying it.
     * @param changeLog
     */
    void validate(@NonNull LuckPermsChangeLogDto changeLog);
}
