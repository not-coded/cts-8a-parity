package net.notcoded.packettest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class PacketTest implements ModInitializer {

    public static final ResourceLocation CONVENTIONAL_BRIDGING_UPDATE_ID = new ResourceLocation("c", "update_status");

    public static final boolean BEDROCK_BRIDGING = false; // Disables Bedrock Bridging Functionality

    @Override
    public void onInitialize() {
        ServerPlayConnectionEvents.JOIN.register(((handler, sender, server) -> {
            ServerPlayer player = handler.player;

            if (ServerPlayNetworking.canSend(player, CONVENTIONAL_BRIDGING_UPDATE_ID)) {
                FriendlyByteBuf buf = PacketByteBufs.create();
                buf.writeBoolean(BEDROCK_BRIDGING);

                ServerPlayNetworking.send(player, CONVENTIONAL_BRIDGING_UPDATE_ID, buf);
            }
        }));
    }
}
