package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.domain.entity.LuckPermsCommand;
import org.springframework.lang.NonNull;

public interface LuckPermsService {

    /**
     * Sends the given command to the remote LuckPermsStandalone service.
     * @param luckPermsCommand
     * @return
     */
    boolean execute(@NonNull LuckPermsCommand luckPermsCommand);
}
