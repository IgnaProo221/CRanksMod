package net.ignaproo.cranks.Commands;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.ignaproo.cranks.CRanksMod;
import net.ignaproo.cranks.Ranks.PlayerRankData;
import net.ignaproo.cranks.list.Ranks;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Team;

import java.util.Collection;
import java.util.Collections;


public class ModCommands {
    public ModCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("setrank")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("rank", StringArgumentType.word())
                                .executes(context -> setRank(context.getSource(), EntityArgument.getPlayers(context, "target"), StringArgumentType.getString(context, "rank"))
                                )
                        )
                )
        );
    }

    public int setRank(CommandSourceStack source, Collection<ServerPlayer> target, String rank) throws CommandSyntaxException {
        MinecraftServer server = source.getServer();
        Scoreboard scoreboard = server.getScoreboard();
        PlayerTeam newTeam = new PlayerTeam(scoreboard, rank);

        for (ServerPlayer player : target) {
            scoreboard.addPlayerToTeam(player.getScoreboardName(), newTeam);
        }

        source.sendSuccess(new TextComponent("Se estableció el rango " + rank + " para los jugadores seleccionados"), true);
        return 1;
    }
}