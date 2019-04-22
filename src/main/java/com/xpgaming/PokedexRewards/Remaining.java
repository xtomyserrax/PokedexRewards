package com.xpgaming.PokedexRewards;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.pokedex.Pokedex;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.*;

public class Remaining implements CommandExecutor
{
    @SuppressWarnings("NullableProblems")
    public CommandResult execute(CommandSource src, CommandContext args)
    {
        if(src instanceof Player)
        {
            Player player = (Player) src;
            double percent = Utils.getInstance().calcPercent((EntityPlayerMP) player);
                if (percent < 100)
                {
                    List<Text> contents = new ArrayList<>();
                    Pokedex pokedex = Pixelmon.storageManager.getParty((EntityPlayerMP) src).pokedex;
                    String baseName, fixedName;

                    for (EnumSpecies e : EnumSpecies.values())
                    {
                        baseName = e.toString();
                        if (!baseName.matches("Meltan|Melmetal")) // Temp Meltan fix.
                        {
                            if (baseName.contentEquals("PorygonZ"))
                                fixedName = "Porygon-Z";
                            else if (baseName.contentEquals("Hooh"))
                                fixedName = "Ho-Oh";
                            else if (baseName.contains("_")) // Tapu fix.
                                fixedName = baseName.replace('_', ' ');
                            else
                                fixedName = baseName;

                            if (!pokedex.hasCaught(Pokedex.nameToID(baseName)))
                            {
                                if (EnumSpecies.legendaries.contains(baseName))
                                    contents.add(Text.of("§e" + fixedName));
                                else
                                    contents.add(Text.of("§6" + fixedName));
                            }
                        }
                    }

                    // Sort alphabetically. For old (0.8 and earlier) sorting, simple remove this or comment it out.
                    Collections.sort(contents);

                    PaginationList.builder()
                            .title(Text.builder("Pokémon Remaining").color(TextColors.GOLD).build())
                            .contents(contents)
                            .padding(Text.builder("-").color(TextColors.YELLOW).build())
                            .sendTo(player);
            }
            else
                src.sendMessage(Text.of("§f[§bPokédex§f] §bYou have no more Pokémon to catch, well done!"));

        }
        else
            src.sendMessage(Text.of("§f[§cPokédex§f] §cYou need to be a player to run this command!"));

        return CommandResult.success();
    }
}
