package com.xpgaming.PokedexRewards;

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
    @SuppressWarnings("NullableProblems")
    public CommandResult execute(CommandSource src, CommandContext args) {
        if(src instanceof Player) {
            Player player = (Player) src;
            Optional<PlayerPartyStorage> optstorage = Optional.ofNullable(Pixelmon.storageManager.getParty(player.getUniqueId()));
            if(optstorage.isPresent()) {
                int caught = (optstorage.get().pokedex.countCaught());
                int dexNum = EnumSpecies.values().length;
                double percent = (double) caught / (double) dexNum * 100.00;
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);

                player.sendMessage(Text.of("§f[§bPokédex§f] §bYou have caught §f"+caught+"§b/§f" +
                        dexNum + " §bPokémon! (§f"+df.format(percent)+"%§b)"));
            }

        } else {
            src.sendMessage(Text.of("§f[§cPokédex§f] §cYou need to be a player to run this command!"));
        }
        return CommandResult.success();
    }
}
