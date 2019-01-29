package com.xpgaming.PokedexRewards;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.command.CommandException;
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
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
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
                    EntityPlayerMP entity = (EntityPlayerMP) src;
                    Optional<PlayerPartyStorage> optstorage = Optional.ofNullable(Pixelmon.storageManager.getParty(entity));
                    if (optstorage.isPresent()) {
                        PlayerPartyStorage storage = optstorage.get();
                        Pokemon pokemon = storage.get(slot - 1);
                        if(pokemon == null) {
                            src.sendMessage(Text.of("\u00A7f[\u00A7cPokeDex\u00A7f] \u00A7cThere's not a valid Pokémon in slot "+slot+"!"));
                        } else {
                            if(pokemon.isShiny()) {
                                src.sendMessage(Text.of("\u00A7f[\u00A7cPokeDex\u00A7f] \u00A7cThat Pokémon is already shiny!"));
                            } else {
                                pokemon.setShiny(true);
                                if(pokemon.isEgg())
                                    src.sendMessage(Text.of("\u00A7f[\u00A7bPokeDex\u00A7f] \u00A7bSuccessfully converted! It will hatch as a shiny!"));
                                else src.sendMessage(Text.of("\u00A7f[\u00A7bPokeDex\u00A7f] \u00A7bSuccessfully converted that Pokémon to a shiny!"));
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
                    }
                } else {
                    src.sendMessage(Text.of("\u00A7f[\u00A7cPokeDex\u00A7f] \u00A7cYou need to be holding a shiny token!"));
                }
            } else {
                src.sendMessage(Text.of("\u00A7f[\u00A7cPokeDex\u00A7f] \u00A7cThat's not a valid slot! Try 1 - 6!"));
            }

        } else {
            src.sendMessage(Text.of("\u00A7f[\u00A7cPokeDex\u00A7f] \u00A7cYou need to be a player to run this command!"));
        }
        return CommandResult.success();
    }
}
