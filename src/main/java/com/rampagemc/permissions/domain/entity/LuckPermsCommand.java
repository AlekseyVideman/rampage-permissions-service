package com.rampagemc.permissions.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "lp_command")
@Accessors(chain = true)
public class LuckPermsCommand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "command_generator")
    @SequenceGenerator(name="command_generator", sequenceName = "cmd_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lp_id", referencedColumnName = "id", nullable = false)
    private LuckPermsChangeLog changelog;

    /**
     * Line number within a {@link LuckPermsChangeLog}.
     */
    @Column(name = "line_number", nullable = false)
    private Long lineNumber;

    @Column(name = "executed_time", nullable = false)
    private LocalDateTime executedTime;

    @Column(name = "command", nullable = false)
    private String command;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuckPermsCommand luckPermsCommand = (LuckPermsCommand) o;
        return Objects.equals(changelog, luckPermsCommand.changelog) && Objects.equals(command, luckPermsCommand.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(changelog, command);
    }
}
