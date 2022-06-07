package net.hat.gt.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import net.hat.gt.spawning.GoblinTraderData;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import java.util.Iterator;

public final class PurgeGoblinData {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("goblintraders:purge_goblin_data").requires((source) -> {
            return source.hasPermissionLevel(4);
        }).executes((context -> {
            GoblinTraderData data = GoblinTraderData.get(context.getSource().getServer());
            context.getSource().getServer().execute(() -> {
                data.clear();
                context.getSource().sendFeedback(new TranslatableText("goblintraders.commands.purge_goblin_data.success"), true);
                context.getSource().sendFeedback(new LiteralText("Restart the server to make changes take effect."), true);
            });
            return 1;
        })));
    }
}
