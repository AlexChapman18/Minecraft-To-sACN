package com.Alex.minecraftsacn.commands;

import com.Alex.minecraftsacn.MinecraftSACN;
import com.Alex.minecraftsacn.generalUtils.Utils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class SetSACNDebugCommand {

    public SetSACNDebugCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                        Commands.literal("SACN")
                        .then(Commands.literal("setDebug")
                        .then(Commands.argument("debugValue", StringArgumentType.string())
                        .executes(command -> toggleDebug(command.getSource(), StringArgumentType.getString(command, "debugValue"))))));
        }

        private int toggleDebug(CommandSource source, String booleanValue) {
            System.out.println(Utils.stringBoolean(booleanValue));
            MinecraftSACN.liveDebug = Utils.stringBoolean(booleanValue);
            source.sendFeedback(new StringTextComponent("SACN debug is now: " + MinecraftSACN.liveDebug), true);
            return 1;
        }

}
