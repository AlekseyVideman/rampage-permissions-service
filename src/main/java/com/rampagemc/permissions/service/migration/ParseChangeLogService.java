package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.dto.LuckPermsChangeLogDto;
import org.springframework.lang.NonNull;

public interface ParseChangeLogService {

    /**
     * Returns a newly parsed {@link LuckPermsChangeLogDto}.
     * @param changeLogFileId
     * @param changeLogFileContent
     * @return the parsed changelog.
     */
    LuckPermsChangeLogDto parse(long changeLogFileId, @NonNull String changeLogFileContent);
}
