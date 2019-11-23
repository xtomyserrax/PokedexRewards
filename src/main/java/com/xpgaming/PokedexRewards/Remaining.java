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
                    String baseName, shownName;

                    for (EnumSpecies e : EnumSpecies.values())
                    {
                        baseName = e.toString();

                        if (baseName.contains("Tapu")) // Tapu fix.
                        {
                            System.out.println("Name: " + baseName);

                            // Remove the underscore from the base. EnumSpecies' "legendaries" list uses names without it.
                            baseName = baseName.substring(0, 4) + "" + baseName.substring(5);

                            // Prettify the shown name by inserting a space where the underscore used to be.
                            shownName = baseName.substring(0, 4) + ' ' + baseName.substring(4);

                            System.out.println("Name: " + baseName);
                        }
                        else switch (baseName)
                        {
                            // Fix names that are different internally for technical reasons.
                            case "Nidoranfemale":
                                shownName = "Nidoran ♀"; break;
                            case "Nidoranmale":
                                shownName = "Nidoran ♂"; break;
                            case "Farfetchd":
                                shownName = "Farfetch'd"; break;
                            case "MrMime":
                                shownName = "Mr. Mime"; break;
                            case "Hooh":
                                shownName = "Ho-Oh"; break;
                            case "MimeJr":
                                shownName = "Mime Jr."; break;
                            case "PorygonZ":
                                shownName = "Porygon-Z"; break;
                            case "Flabebe":
                                shownName = "Flabébé"; break;
                            case "TypeNull":
                                shownName = "Type: Null"; break;
                            case "Jangmoo":
                                shownName = "Jangmo-o";

                            System.out.println("Name: " + baseName); break;
                            case "Hakamoo":
                                shownName = "Hakamo-o";

                            System.out.println("Name: " + baseName); break;
                            case "Kommoo":
                                shownName = "Kommo-o";

                            System.out.println("Name: " + baseName); break;
                            default:
                                shownName = baseName;
                        }

                        if (!pokedex.hasCaught(Pokedex.nameToID(baseName)))
                        {
                            if (EnumSpecies.legendaries.contains(baseName) || EnumSpecies.ultrabeasts.contains(baseName))
                                contents.add(Text.of("§e" + shownName));
                            else
                                contents.add(Text.of("§6" + shownName));
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
