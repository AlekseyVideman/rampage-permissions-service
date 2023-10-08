package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.domain.entity.LuckPermsChangeLog;
import com.rampagemc.permissions.domain.entity.LuckPermsCommand;
import com.rampagemc.permissions.dto.LuckPermsChangeLogDto;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ChangeLogDataBaseService {

    /**
     * Scans classpath`s lp directory for a txt changelog files, and loads its content to map.
     * @param changeLog
     */
    Optional<LuckPermsChangeLog> load(@NonNull LuckPermsChangeLogDto changeLog);

    /**
     * Returns all saved in a database {@link LuckPermsChangeLogDto#getLuckPermsCommands()}.
     * @param changeLog
     */
    List<LuckPermsCommand> getAllPastCommands(@NonNull LuckPermsChangeLogDto changeLog);
}
