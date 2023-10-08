package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.exception.LuckPermsMigrationException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

@Service
public class LoadChangeLogServiceImpl implements ApplicationContextAware, LoadChangeLogService {

    private ApplicationContext context;

    @Override
    public Map<Long, String> loadChangeLogsDataMap(@NonNull String dir) {
        try {
            final var changeLogResources = context.getResources(dir);
            final Map<Long, String> changeLogsMap = new TreeMap<>(Long::compareTo);

            for (var changeLogResource : changeLogResources) {
                final var numericId = getNumericPrefix(changeLogResource.getFilename());
                changeLogsMap.put(numericId, changeLogResource.getContentAsString(StandardCharsets.UTF_8));
            }

            return changeLogsMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Long getNumericPrefix(String full) {
        final var isInvalid = !Pattern.matches("^[0-9]+[_\\w]*\\.txt", full);
        if (isInvalid)  {
            throw new LuckPermsMigrationException(String.format(
                    "Changelog name must begin with numbers, but given '%s'", full));
        }

        final int underscore = full.indexOf('_');
        final var numericPart = full.substring(0, underscore).replaceFirst("^0*", "");
        return Long.parseLong(numericPart);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
