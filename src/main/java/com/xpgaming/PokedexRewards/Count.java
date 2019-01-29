package com.xpgaming.PokedexRewards;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;

import java.text.DecimalFormat;
import java.util.Optional;

public class Count implements CommandExecutor {
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(src instanceof Player) {
            Player player = (Player) src;
            Optional<PlayerPartyStorage> optstorage = Optional.ofNullable(Pixelmon.storageManager.getParty(player.getUniqueId()));
            if(optstorage.isPresent()) {
                int caught = (optstorage.get().pokedex.countCaught());
                double percent = (double) caught / (double) EnumSpecies.values().length * 100.00;
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                player.sendMessage(Text.of("\u00A7f[\u00A7bPokeDex\u00A7f] \u00A7bYou have caught \u00A7f"+caught+"\u00A7b/\u00A7f"+EnumSpecies.values().length+" \u00A7bPokémon! (\u00A7f"+df.format(percent)+"%\u00A7b)"));
            }

        } else {
            src.sendMessage(Text.of("\u00A7f[\u00A7cPokeDex\u00A7f] \u00A7cYou need to be a player to run this command!"));
        }
        return CommandResult.success();
    }
}
