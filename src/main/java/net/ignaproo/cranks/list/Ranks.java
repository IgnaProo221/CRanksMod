package net.ignaproo.cranks.list;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class Ranks {
    public static void setPlayerDisplayName(ServerPlayer player, String displayName) {
        if (player != null) {
            player.setCustomName(Component.nullToEmpty(displayName));
            player.setCustomNameVisible(true);
        }
    }
    public enum Rangos {

        CONDE("\uFA01 ", "150"),
        CABALLERO("\uFA02 ", "40"),
        NOBLE("\uFA03 ", "30"),
        MEMBER("\uFA03", "1"),
        ADMIN("\uFA03 ", "99");

        private final String prefix;
        private final String priority;

        Rangos(String prefix, String priority) {
            this.prefix = prefix;
            this.priority = priority;
        }
    }

}
