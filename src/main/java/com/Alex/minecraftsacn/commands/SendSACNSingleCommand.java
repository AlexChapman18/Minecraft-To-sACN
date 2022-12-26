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

public class SendSACNSingleCommand {

    private final sACNDataPacket sACNPacket = new sACNDataPacket("AlexPacket");
    private sACNSocket Socket = MinecraftSACN.liveSocket;

    public SendSACNSingleCommand(CommandDispatcher<CommandSource> dispatcher){

        sACNPacket.setUniverse(0);
        sACNPacket.setPacket();

        dispatcher.register(
                        Commands.literal("SACN")
                        .then(Commands.literal("sendSingle")
                        .then(Commands.argument("address", IntegerArgumentType.integer(0, 255))
                        .then(Commands.argument("value", IntegerArgumentType.integer(0, 255))
                        .executes(command -> sendSingleValue(command.getSource(),
                                IntegerArgumentType.getInteger(command, "address"),
                                IntegerArgumentType.getInteger(command, "value")))))));
        }
    private int sendSingleValue(CommandSource source, int _address, int _value) {
        try {
            setAndSend(_address, _value);
            if (MinecraftSACN.liveDebug)
                source.sendFeedback(new StringTextComponent("Successfully sent value: " + _value + ", to address: " + _address), true);
            return 1;
        } catch (IOException e){
            if (MinecraftSACN.liveDebug)
                source.sendFeedback(new StringTextComponent("Failed to send value: " + _value + ", to address: " + _address), true);
            return -1;
        }
    }


    public void setAndSend(int _address, int _value) throws IOException {
        MinecraftSACN.liveDMXValues[_address - 1] = (byte) _value;
        sACNDataPacket sACNPacket = new sACNDataPacket("MCSACNPacket");

        sACNPacket.setAllValues(MinecraftSACN.liveDMXValues);
        sACNPacket.setOutputUniverse(MinecraftSACN.liveUniverse);
        sACNPacket.setPacket();

        DatagramPacket
                datagramPacket = new DatagramPacket(sACNPacket.getPacket(), 0, 638, InetAddress.getByName("255.255.255.255"), 5568);

        Socket.sendPacket(datagramPacket);
    }
}
