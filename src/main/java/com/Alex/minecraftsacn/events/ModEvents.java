package com.Alex.minecraftsacn.events;

import com.Alex.minecraftsacn.MinecraftSACN;
import com.Alex.minecraftsacn.commands.SendSACNAllCommand;
import com.Alex.minecraftsacn.commands.SendSACNSingleCommand;
import com.Alex.minecraftsacn.commands.SetSACNDebugCommand;
import com.Alex.minecraftsacn.commands.SetSACNUniverseCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = MinecraftSACN.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event){
        new SendSACNAllCommand(event.getDispatcher());
        new SendSACNSingleCommand(event.getDispatcher());
        new SetSACNUniverseCommand(event.getDispatcher());
        new SetSACNDebugCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public  static void onPlayerCloneEvent(PlayerEvent.Clone event){
        if (!event.getOriginal().getEntityWorld().isRemote){
            event.getPlayer().getPersistentData().putIntArray(MinecraftSACN.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(MinecraftSACN.MOD_ID + "homepos"));
        }
    }
}
