package team.lodestar.lodestone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import team.lodestar.lodestone.helpers.OrtTestItem;
import team.lodestar.lodestone.setup.LodestoneParticles;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.random.RandomGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Vector3f;

public class LodestoneLib implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("LodestoneLib");
	public static final String MODID = "lodestone";
	public static final Vector3f VEC3F_ZERO = new Vector3f();

	public static final RandomGenerator RANDOM = RandomGenerator.createLegacy();
	public static final FabricLoader LOADER_INSTANCE = FabricLoader.getInstance();

	@Override
	public void onInitialize() {
		LOGGER.info("jesser where is the cocainer");
		LodestoneParticles.init();
		if(LOADER_INSTANCE.isDevelopmentEnvironment()) {
			Registry.register(Registries.ITEM, id("ort"), new OrtTestItem(new FabricItemSettings().rarity(Rarity.EPIC)));
		}
	}
	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}
}
