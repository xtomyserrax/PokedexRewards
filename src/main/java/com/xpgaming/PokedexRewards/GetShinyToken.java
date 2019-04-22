package com.xpgaming.PokedexRewards;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class GetShinyToken implements CommandExecutor {
    @SuppressWarnings("NullableProblems")
    public CommandResult execute(CommandSource src, CommandContext args) {
        if(src instanceof Player) {
            Player player = (Player) src;
            Utils.getInstance().giveItemStack(Utils.getInstance().shinyToken(), player);
        } else {
            src.sendMessage(Text.of("§f[§cPokédex§f] §cYou need to be a player to run this command!"));
        }
        return CommandResult.success();
    }
}