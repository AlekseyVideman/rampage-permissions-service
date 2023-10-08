package com.rampagemc.permissions;

import com.rampagemc.permissions.configuration.property.MigrationProperties;
import com.rampagemc.permissions.service.migration.MigrateChangeLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionsRunner implements ApplicationRunner {

    private final MigrationProperties migrationProperties;
    private final MigrateChangeLogService migrateChangeLogService;

    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("Starting migration...");

            migrateChangeLogService.migrateFrom(migrationProperties.getChangelogPath());

            log.info("Migration ran successfully!");
        } catch (RuntimeException e) {
            log.error("Disabling service due to failure...", e);
        }
    }
}
