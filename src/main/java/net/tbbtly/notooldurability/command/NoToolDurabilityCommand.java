package net.tbbtly.notooldurability.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.permissions.Permissions;
import net.tbbtly.notooldurability.config.NoToolDurabilityState;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public final class NoToolDurabilityCommand {

    private NoToolDurabilityCommand() {}

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(
                        Commands.literal("notooldurability")
                                .requires(src -> src.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))

                                // ── /nodurability enable ────────────────────────────────
                                .then(Commands.literal("enable")
                                        .executes(NoToolDurabilityCommand::executeEnable))

                                // ── /nodurability disable ───────────────────────────────
                                .then(Commands.literal("disable")
                                        .executes(NoToolDurabilityCommand::executeDisable))

                                // ── /nodurability status ────────────────────────────────
                                .then(Commands.literal("status")
                                        .executes(NoToolDurabilityCommand::executeStatus))
                )
        );
    }

    // ── Handlers ─────────────────────────────────────────────────────────────

    private static int executeEnable(CommandContext<CommandSourceStack> ctx) {
        NoToolDurabilityState.enable();
        broadcast(ctx, true);
        return 1;
    }

    private static int executeDisable(CommandContext<CommandSourceStack> ctx) {
        NoToolDurabilityState.disable();
        broadcast(ctx, false);
        return 1;
    }

    private static int executeStatus(CommandContext<CommandSourceStack> ctx) {
        boolean enabled = NoToolDurabilityState.isEnabled();
        ctx.getSource().sendSuccess(
                () -> statusMessage(enabled),
                /* broadcastToOps */ false
        );
        return enabled ? 1 : 0;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static void broadcast(CommandContext<CommandSourceStack> ctx, boolean enabled) {
        ctx.getSource().sendSuccess(
                () -> statusMessage(enabled),
                /* broadcastToOps */ true
        );
    }

    private static Component statusMessage(boolean enabled) {
        if (enabled) {
            return Component.literal(
                    "[NoToolDurability] \u00a7aItems are now Unbreakable."
            );
        } else {
            return Component.literal(
                    "[NoToolDurability] \u00a7cItems are now Breakable."
            );
        }
    }
}
