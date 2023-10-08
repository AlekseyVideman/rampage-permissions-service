package com.rampagemc.permissions.dto;

import com.rampagemc.permissions.domain.entity.LuckPermsCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@SuppressWarnings("all")
public class LuckPermsChangeLogDto {

    private final long id;
    private List<LuckPermsCommand> luckPermsCommands;

    public LuckPermsChangeLogDto(long id) {
        this.id = id;
    }
}
