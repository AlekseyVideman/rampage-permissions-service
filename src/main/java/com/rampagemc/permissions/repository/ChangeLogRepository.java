package com.rampagemc.permissions.repository;

import com.rampagemc.permissions.domain.entity.LuckPermsChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeLogRepository extends JpaRepository<LuckPermsChangeLog, Long> {
}
