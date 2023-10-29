package net.ignaproo.cranks.Events;

import com.mojang.brigadier.CommandDispatcher;
import net.ignaproo.cranks.CRanksMod;
import net.ignaproo.cranks.Commands.ModCommands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = CRanksMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent e) {
        new ModCommands(e.getDispatcher());

        ConfigCommand.register(e.getDispatcher());
    }
}
