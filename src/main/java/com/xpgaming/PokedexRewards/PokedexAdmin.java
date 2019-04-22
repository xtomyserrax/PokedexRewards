package com.xpgaming.PokedexRewards;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class PokedexAdmin implements CommandExecutor {
    @SuppressWarnings("NullableProblems")
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(src instanceof Player) {
            Player player = (Player) src;
            player.sendMessage(Text.of("§f[§bPokédex Admin§f] §b§l-- COMMANDS --"));
            if(player.hasPermission("xpgaming.pokedex.admin")) {
                player.sendMessage(Text.of("  §7> §b/pokedex reload§f|§brl §7- Reload config!"));
                player.sendMessage(Text.of("  §7> §b/pokedex getshinytoken§f|§bgst"));
            }
        } else {
            src.sendMessage(Text.of("§f[§bPokédex Admin§f] §b§l-- COMMANDS --"));
            src.sendMessage(Text.of("  §7> §b/pokedex reload§f|§brl §7- Reload config!"));
            src.sendMessage(Text.of("  §7> §b/pokedex getshinytoken§f|§bgst"));
        }
        return CommandResult.success();
    }

}
