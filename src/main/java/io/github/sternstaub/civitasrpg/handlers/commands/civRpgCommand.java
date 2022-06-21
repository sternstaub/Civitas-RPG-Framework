package io.github.sternstaub.civitasrpg.handlers.commands;

import io.github.sternstaub.civitasrpg.CivitasRPG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class civRpgCommand implements CommandExecutor {
    CivitasRPG plugin;
    civRpgCommand() {
        plugin = CivitasRPG.PLUGIN;
    }

    /**
     * Command for managing the CivitasRPG-Plugin (admin command)
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        return false;
    }
}
