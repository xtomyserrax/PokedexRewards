package com.xpgaming.PokedexRewards;

import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.entity.Hotbar;
import org.spongepowered.api.item.inventory.type.GridInventory;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

import java.util.List;

public class Utils {
    private static Utils instance = new Utils();
    public static Utils getInstance() {
        return instance;
    }

    public void runConsoleCommand(String cmd) {
        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), cmd);
    }

    public void giveItemStack(ItemStack i, Player player) {
        if(player.getInventory().query(Hotbar.class, GridInventory.class).size() < 36)
        {
            player.getInventory().offer(i);
        }
        else
        {
            World world = player.getLocation().getExtent();
            Entity it = world
                    .createEntity(EntityTypes.ITEM, player.getLocation().getPosition());
                    it.offer(Keys.REPRESENTED_ITEM, i.createSnapshot());
            world.spawnEntity(it);
        }

    }

    public ItemStack shinyToken() {
        ItemStack token = ItemStack.builder()
                .itemType(ItemTypes.PAPER)
                .quantity(1)
                .build();
        token.offer(Keys.DISPLAY_NAME, Text.of(TextColors.GOLD, "Shiny Token"));
        List<Text> lore = Lists.newArrayList(Text.of(TextColors.YELLOW, "Use /pd convert <slot> to redeem!"));
        token.offer(Keys.ITEM_LORE, lore);
        return token;
    }

    public ItemStack oldShinyToken() {
        ItemStack token = ItemStack.builder()
                .itemType(ItemTypes.PAPER)
                .quantity(1)
                .build();
        token.offer(Keys.DISPLAY_NAME, Text.of(TextColors.GOLD, "Shiny Token"));
        List<Text> lore = Lists.newArrayList(Text.of(TextColors.YELLOW, "/pd convert <slot> to\ntransform your selected\nPokemon into a shiny!\n", TextColors.GOLD, "||Shiny Token||"));
        token.offer(Keys.ITEM_LORE, lore);
        return token;
    }

    public double calcPercent(EntityPlayerMP entity) {
        int caught = Pixelmon.storageManager.getParty(entity).pokedex.countCaught();
        return (double) caught / ((double) EnumSpecies.values().length) * 100.00;
    }

    public boolean hasClaimed(Player p, String pct) {
        return UserData.getInstance().getConfig().getNode("playerData", p.getUniqueId().toString(), pct).getBoolean();
    }
}
