package com.rampagemc.permissions.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

/**
 * Represents a unique named changelog file with LuckPerms commands.
 */
@Getter
@Setter
@Entity
@Table(name = "lp_changelog")
@Accessors(chain = true)
public class LuckPermsChangeLog {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "lp_id")
    private List<LuckPermsCommand> luckPermsCommands;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuckPermsChangeLog luckPermsChangeLog = (LuckPermsChangeLog) o;
        return Objects.equals(id, luckPermsChangeLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
