package com.xpgaming.PokedexRewards;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class GiveShinyToken implements CommandExecutor {
    @SuppressWarnings("NullableProblems")
    public CommandResult execute(CommandSource src, CommandContext args) {
        Player p = args.<Player>getOne("player").get();
        Utils.getInstance().giveItemStack(Utils.getInstance().shinyToken(), p);
        src.sendMessage(Text.of("§f[§cPokédex§f] §cGave " + p.getName() + " a shiny token!"));
        return CommandResult.success();
    }
}
