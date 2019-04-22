package com.xpgaming.PokedexRewards;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class PokedexBase implements CommandExecutor {
    @SuppressWarnings("NullableProblems")
    public CommandResult execute(CommandSource src, CommandContext args) {
        if(src instanceof Player) {
            Player player = (Player) src;
            player.sendMessage(Text.of("§f[§bPokédex§f] §b§l-- COMMANDS --"));
            if (player.hasPermission("xpgaming.pokedex.base")) {
                player.sendMessage(Text.of("  §7> §b/pokedex count§f|§bc §7- Count remaining Pokémon to catch!"));
                player.sendMessage(Text.of("  §7> §b/pokedex remaining§f|§br §7- List remaining Pokémon to catch!"));
                player.sendMessage(Text.of("  §7> §b/pokedex claim§f|§bcl §7- Claim rewards every 10%!"));
                player.sendMessage(Text.of("  §7> §b/pokedex convert <slot> §7- Use a shiny token!"));
            }
        } else {
            src.sendMessage(Text.of("§f[§bPokédex§f] §b§l-- COMMANDS --"));
            src.sendMessage(Text.of("  §7> §b/pokedex count§f|§bc §7- Count remaining Pokémon to catch!"));
            src.sendMessage(Text.of("  §7> §b/pokedex remaining§f|§br §7- List remaining Pokémon to catch!"));
            src.sendMessage(Text.of("  §7> §b/pokedex claim§f|§bcl §7- Claim rewards every 10%!"));
            src.sendMessage(Text.of("  §7> §b/pokedex convert <slot> §7- Use a shiny token!"));
        }
        return CommandResult.success();
    }

}
