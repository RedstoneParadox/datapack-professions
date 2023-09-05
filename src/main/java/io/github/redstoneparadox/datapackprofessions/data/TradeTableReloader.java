package io.github.redstoneparadox.datapackprofessions.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import io.github.redstoneparadox.datapackprofessions.DatapackProfessions;
import io.github.redstoneparadox.datapackprofessions.trades.LoadedTradeOffers;
import io.github.redstoneparadox.datapackprofessions.trades.TradeTable;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.Map;

public final class TradeTableReloader extends JsonReloader {
	public TradeTableReloader() {
		super("datapack_professions/trade_tables");
	}

	@Override
	protected void apply(Map<Identifier, JsonElement> cache, ResourceManager manager, Profiler profiler) {
		LoadedTradeOffers.addVanillaOffers();

		cache.forEach((id, element) -> {
			try {
				var result = TradeTable.CODEC.parse(JsonOps.INSTANCE, element);
				var optional = result.resultOrPartial(s -> {});

				if (result.error().isPresent()) {
					if (optional.isPresent()) {
						DatapackProfessions.LOGGER.error("Error loading Trade Table '{}'. Trade Table will only be partially loaded. {}", id, result.error().get().message());
					} else {
						DatapackProfessions.LOGGER.error("Error loading Trade Table '{}'. (Skipping). {}", id, result.error().get().message());
					}
				}

				optional.ifPresent(tradeTable -> LoadedTradeOffers.addOffers(id, tradeTable.replace(), tradeTable.offers()));

			} catch (Exception e) {
				DatapackProfessions.LOGGER.error("Could not load Trade Table '{}'. (Skipping). {}", id, e);
			}
		});

		DatapackProfessions.LOGGER.info("Finished loading Trade Tables.");
	}
}
