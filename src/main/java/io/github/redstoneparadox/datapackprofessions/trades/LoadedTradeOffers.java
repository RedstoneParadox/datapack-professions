package io.github.redstoneparadox.datapackprofessions.trades;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadedTradeOffers {
	private static final Map<Identifier, List<ExtendedTradeOfferFactory>> OFFERS = new HashMap<>();

	public static void addVanillaOffers() {
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

				String suffix = switch (i) {
					case 2 -> "apprentice";
					case 3 -> "journeyman";
					case 4 -> "master";
					case 5 -> "expert";
					default -> "novice";
				};
				Identifier identifier = new Identifier(prefix + "_" + suffix);

				OFFERS.put(identifier, offers);
			}
		}
	}

	public static void addOffers(Identifier identifier, boolean replace, List<ExtendedTradeOfferFactory> newOffers) {
		if (!OFFERS.containsKey(identifier)) {
			OFFERS.put(identifier, newOffers);
			return;
		}

		List<ExtendedTradeOfferFactory> existingOffers = OFFERS.get(identifier);

		if (replace) {
			OFFERS.put(identifier, newOffers);
		} else {
			existingOffers.addAll(newOffers);
		}
	}
}
