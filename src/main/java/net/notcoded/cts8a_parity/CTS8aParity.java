package net.notcoded.cts8a_parity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class CTS8aParity implements ClientModInitializer {
	public static boolean enabledBridging = true;
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation("c", "update_status"), (client, handler, buf, responseSender) -> enabledBridging = buf.readBoolean());
		ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> enabledBridging = true);
	}
}
