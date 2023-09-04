package io.github.redstoneparadox.datapackprofessions.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import io.github.redstoneparadox.datapackprofessions.DatapackProfessions;
import io.github.redstoneparadox.datapackprofessions.trades.TradeTable;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.Map;

public final class TradeTableReloadListener extends JsonDataLoader {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	public TradeTableReloadListener() {
		super(GSON, "datapack_professions/trade_tables");
	}

	@Override
	protected void apply(Map<Identifier, JsonElement> cache, ResourceManager manager, Profiler profiler) {
		cache.forEach((id, element) -> {
			try {
				var result = TradeTable.CODEC.parse(JsonOps.INSTANCE, element);
				var table = result.resultOrPartial(s -> {});

				if (result.error().isPresent()) {
					if (table.isPresent()) {
						DatapackProfessions.LOGGER.error("Error loading Trade Table '{}'. Trade Table will only be partially loaded. {}", id, result.error().get().message());
					} else {
						DatapackProfessions.LOGGER.error("Error loading Trade Table  '{}'. (Skipping). {}", id, result.error().get().message());
					}
				}

				// Temporary
				if (table.isPresent()) DatapackProfessions.LOGGER.info("Successfully loaded trade table '{}'", id);

			} catch (Exception e) {
				DatapackProfessions.LOGGER.error("Could not load Trade Table '{}'. (Skipping). {}", id, e);
			}
		});

		DatapackProfessions.LOGGER.info("Finished loading Trade Tables.");
	}
}
