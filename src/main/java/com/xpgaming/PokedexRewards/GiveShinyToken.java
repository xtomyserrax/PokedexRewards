package com.xpgaming.PokedexRewards;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class GiveShinyToken implements CommandExecutor {
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player p = args.<Player>getOne("player").get();
        Utils.getInstance().giveItemStack(Utils.getInstance().shinyToken(), p);
        src.sendMessage(Text.of("\u00A7f[\u00A7cPokeDex\u00A7f] \u00A7cGave " + p.getName() + " a shiny token!"));
        return CommandResult.success();
    }
}
