package io.github.redstoneparadox.datapackprofessions.trades;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadedTradeOffers {
	private static final Map<Identifier, List<ExtendedTradeOfferFactory>> OFFERS = new HashMap<>();

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
