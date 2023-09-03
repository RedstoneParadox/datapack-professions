package io.github.redstoneparadox.datapackprofessions;

import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatapackProfessions implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Datapack Professions");
	@Override
	public void onInitialize(ModContainer mod) {
	}

	public static Identifier id(String path) {
		return new Identifier("datapack_professions", path);
	}
}
