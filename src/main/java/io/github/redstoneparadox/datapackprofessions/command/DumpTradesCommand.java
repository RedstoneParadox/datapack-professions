package io.github.redstoneparadox.datapackprofessions.command;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.JsonOps;
import io.github.redstoneparadox.datapackprofessions.trades.ExtendedTradeOfferFactory;
import io.github.redstoneparadox.datapackprofessions.trades.TradeTable;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.quiltmc.loader.api.QuiltLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DumpTradesCommand implements Command<ServerCommandSource> {
	@Override
	public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		for (Map.Entry<VillagerProfession, Int2ObjectMap<TradeOffers.Factory[]>> entry : TradeOffers.PROFESSION_TO_LEVELED_TRADE.entrySet()) {
			String name = entry.getKey().name();
			String prefix = name.substring(name.indexOf(':') + 1);
			Int2ObjectMap<TradeOffers.Factory[]> factoryMap = entry.getValue();

			for (int i = 1; i <= 5; i++) {
				TradeOffers.Factory[] factories = factoryMap.get(i);
				List<ExtendedTradeOfferFactory> offers = new ArrayList<>();

				for (TradeOffers.Factory factory : factories) {
					offers.add((ExtendedTradeOfferFactory) factory);
				}

				TradeTable table = new TradeTable(false, offers);
				String levelName = switch (i) {
					case 2 -> "apprentice";
					case 3 -> "journeyman";
					case 4 -> "master";
					case 5 -> "expert";
					default -> "novice";
				};
				var result = TradeTable.CODEC.encodeStart(JsonOps.INSTANCE, table).result();
				File directory = new File(QuiltLoader.getGameDir().toFile(), "dumped-trades");
				File file = new File(directory, prefix + "_" + levelName + ".json");

				result.ifPresent(json -> {
					try {
						var exists = true;

						if (!directory.exists()) {
							directory.mkdir();
						}

						if (!file.exists()) {
							exists = file.createNewFile();
						}

						if (exists) {
							var jsonWriter = new JsonWriter(new BufferedWriter(new FileWriter(file)));
							jsonWriter.setLenient(true);
							jsonWriter.setIndent("  ");
							Streams.write(json, jsonWriter);
							jsonWriter.close();
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			}
		}

		return 0;
	}
}
