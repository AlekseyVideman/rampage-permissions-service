package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.domain.entity.LuckPermsChangeLog;
import com.rampagemc.permissions.domain.entity.LuckPermsCommand;
import com.rampagemc.permissions.dto.LuckPermsChangeLogDto;
import com.rampagemc.permissions.repository.ChangeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangeLogDataBaseServiceImpl implements ChangeLogDataBaseService {

    private final ChangeLogRepository changeLogRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<LuckPermsChangeLog> load(@NonNull LuckPermsChangeLogDto changeLog) {
        return changeLogRepository.findById(changeLog.getId());
    }

    @Override
    @Transactional
    public List<LuckPermsCommand> getAllPastCommands(@NonNull LuckPermsChangeLogDto changeLog) {
        final var pastChangeLogOptional = this.load(changeLog);

        if (pastChangeLogOptional.isEmpty()) return new LinkedList<>();

        return pastChangeLogOptional.get().getLuckPermsCommands();
    }
}
