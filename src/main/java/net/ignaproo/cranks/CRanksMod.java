package net.ignaproo.cranks;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.logging.LogUtils;
import net.ignaproo.cranks.Commands.ModCommands;
import net.ignaproo.cranks.Ranks.PlayerRankData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(CRanksMod.MOD_ID)
public class CRanksMod {
    public static final String MOD_ID = "cranks";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CRanksMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

    }
    private static List<PlayerRankData> playerRanks = new ArrayList<>();

    public static List<PlayerRankData> getPlayerRanks() {
        return playerRanks;
    }

    public static PlayerRankData getPlayerRankData(String playerName) {
        return playerRanks.stream()
                .filter(data -> data.getPlayerName().equals(playerName))
                .findFirst()
                .orElse(null);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}