package com.Alex.minecraftsacn.commands;

import com.Alex.minecraftsacn.JavaSACN.sACNDataPacket;
import com.Alex.minecraftsacn.JavaSACN.sACNSocket;
import com.Alex.minecraftsacn.MinecraftSACN;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;

public class SetSACNUniverseCommand {


    public SetSACNUniverseCommand(CommandDispatcher<CommandSource> dispatcher) {

        dispatcher.register(
                        Commands.literal("SACN")
                        .then(Commands.literal("setUniverse")
                        .then(Commands.argument("universe", IntegerArgumentType.integer(0, 255))
                        .executes(command -> setUniverse(command.getSource(),
                                IntegerArgumentType.getInteger(command, "universe"))))));
        }



        private int setUniverse(CommandSource source, int _universe) {
            MinecraftSACN.liveUniverse = _universe;
            if (MinecraftSACN.liveDebug)
                source.sendFeedback(new StringTextComponent("Successfully set universe to: " + _universe), true);
            return 1;
        }

}
