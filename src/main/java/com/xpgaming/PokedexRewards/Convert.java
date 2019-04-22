package com.xpgaming.PokedexRewards;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackComparators;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class Convert implements CommandExecutor {
    @SuppressWarnings("NullableProblems")
    public CommandResult execute(CommandSource src, CommandContext args) {
        if(src instanceof Player) {
            int slot = args.<Integer>getOne("slot").get();
            boolean holdingAShinyToken = false;
            if(slot <= 6 && slot >= 1) {
                Player player = (Player) src;
                Optional<ItemStack> itemInHand = player.getItemInHand(HandTypes.MAIN_HAND);
                if(itemInHand.isPresent()) {
                    ItemStack theItem = itemInHand.get();
                    if(ItemStackComparators.ITEM_DATA.compare(theItem, Utils.getInstance().shinyToken()) == 0) {
                        holdingAShinyToken = true;
                    } else if(ItemStackComparators.ITEM_DATA.compare(theItem, Utils.getInstance().oldShinyToken()) == 0) {
                        holdingAShinyToken = true;
                    }
                }
                if(holdingAShinyToken) {
                    Pokemon pokemon = Pixelmon.storageManager.getParty((EntityPlayerMP) src).get(slot - 1);
                    if(pokemon == null) {
                        src.sendMessage(Text.of("§f[§cPokédex§f] §cThere's not a valid Pokémon in slot "+slot+"!"));
                    } else {
                        if(pokemon.isShiny()) {
                            src.sendMessage(Text.of("§f[§cPokédex§f] §cThat Pokémon is already shiny!"));
                        } else {
                            pokemon.setShiny(true);
                            if(pokemon.isEgg())
                                src.sendMessage(Text.of("§f[§bPokédex§f] §bSuccessfully converted! It will hatch as a shiny!"));
                            else src.sendMessage(Text.of("§f[§bPokédex§f] §bSuccessfully converted that Pokémon to a shiny!"));
                            //storage.sendUpdatedList();
                            if(itemInHand.isPresent()) {
                                int amount = itemInHand.get().getQuantity();
                                if(amount == 1) {
                                    player.setItemInHand(HandTypes.MAIN_HAND, null);
                                } else {
                                    itemInHand.get().setQuantity(amount-1);
                                    player.setItemInHand(HandTypes.MAIN_HAND, itemInHand.get());
                                }
                            }
                        }
                    }
                } else {
                    src.sendMessage(Text.of("§f[§cPokédex§f] §cYou need to be holding a shiny token!"));
                }
            } else {
                src.sendMessage(Text.of("§f[§cPokédex§f] §cThat's not a valid slot! Try 1 - 6!"));
            }

        } else {
            src.sendMessage(Text.of("§f[§cPokédex§f] §cYou need to be a player to run this command!"));
        }
        return CommandResult.success();
    }
}
