package team.lodestar.lodestone;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import team.lodestar.lodestone.config.ClientConfig;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler;
import team.lodestar.lodestone.helpers.OrtEmitter;
import team.lodestar.lodestone.network.screenshake.PositionedScreenshakePacket;
import team.lodestar.lodestone.network.screenshake.ScreenshakePacket;
import team.lodestar.lodestone.setup.LodestoneRenderLayers;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.item.Items;

import static team.lodestar.lodestone.LodestoneLib.MODID;

public class LodestoneLibClient implements ClientModInitializer {

	public static final FabricLoader CLIENT_LOADER_INSTANCE = FabricLoader.getInstance();

	@Override
	public void onInitializeClient() {
		MidnightConfig.init(MODID, ClientConfig.class);

		Reflection.initialize(LodestoneRenderLayers.class);
		RenderHandler.init();
		ParticleEmitterHandler.registerParticleEmitters();
		if(CLIENT_LOADER_INSTANCE.isDevelopmentEnvironment()) {
			ParticleEmitterHandler.registerItemParticleEmitter(new OrtEmitter(), Items.DIAMOND);
		}

		ClientPlayNetworking.registerGlobalReceiver(ScreenshakePacket.ID, (client, handler, buf, responseSender) -> new ScreenshakePacket(buf).apply(client.getNetworkHandler()));
		ClientPlayNetworking.registerGlobalReceiver(PositionedScreenshakePacket.ID, (client, handler, buf, responseSender) -> PositionedScreenshakePacket.fromBuf(buf).apply(client.getNetworkHandler()));
	}
}
