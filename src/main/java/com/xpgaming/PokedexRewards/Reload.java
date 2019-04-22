package com.xpgaming.PokedexRewards;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

public class Reload implements CommandExecutor {
    public CommandResult execute(CommandSource src, CommandContext args) {
        Config.getInstance().configLoad();
        UserData.getInstance().dataLoad();
        src.sendMessage(Text.of("§f[§bPokédex§f] §b§lSuccessfully reloaded the config!"));
        return CommandResult.success();
    }

}
