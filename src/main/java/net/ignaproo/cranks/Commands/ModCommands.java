package net.ignaproo.cranks.Commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.ignaproo.cranks.CRanksMod;
import net.ignaproo.cranks.Ranks.PlayerRankData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class ModCommands {
    public ModCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("setrank")
                .requires(source -> source.hasPermission(2)) // Cambia el nivel de permiso necesario
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("rank", StringArgumentType.word())
                                .executes(context -> setRank(context))
                        )
                )
        );
    }

    private static int setRank(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        Entity target = EntityArgument.getPlayer(context, "target");
        Entity entity = EntityArgument.getPlayer(context, "target");
        Player player = (Player) entity;
        String rank = StringArgumentType.getString(context, "rank");

        PlayerRankData playerData = CRanksMod.getPlayerRankData(String.valueOf(target.getName()));

        if (playerData != null) {
            if (player.level.isClientSide()) {
                player.displayClientMessage(Component.nullToEmpty("Se asignó el rango '" + rank + "' a tu personaje"), false);
            }
            playerData.setRank(rank);
            return 1;
        } else {
            source.sendFailure((Component) new LiteralMessage("Jugador no encontrado en la lista."));
            return 0;
        }
    }
}