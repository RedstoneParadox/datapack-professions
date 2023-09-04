package io.github.redstoneparadox.datapackprofessions;

import io.github.redstoneparadox.datapackprofessions.data.TradeTableReloader;
import io.github.redstoneparadox.datapackprofessions.trades.TradeOfferFactories;
import io.github.redstoneparadox.datapackprofessions.trades.TradeOfferFactoryType;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatapackProfessions implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Datapack Professions");
	public static final String NAMESPACE = "datapack_professions";
	@Override
	public void onInitialize(ModContainer mod) {
		// Static initialization shenanigans
		var foo = TradeOfferFactoryType.VanillaTypes.BUY_FOR_ONE_EMERALD_FACTORY_CODEC;

		var loader = ResourceLoader.get(ResourceType.SERVER_DATA);

		loader.registerReloader(new TradeTableReloader());
	}

	public static Identifier id(String path) {
		return new Identifier(NAMESPACE, path);
	}
}
