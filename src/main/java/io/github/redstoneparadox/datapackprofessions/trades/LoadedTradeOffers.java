package io.github.redstoneparadox.datapackprofessions.trades;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadedTradeOffers {
	private static final Map<Identifier, List<ExtendedTradeOfferFactory>> OFFERS = new HashMap<>();

	public static void addVanillaOffers() {
		for (Map.Entry<VillagerProfession, Int2ObjectMap<TradeOffers.Factory[]>> entry : TradeOffers.PROFESSION_TO_LEVELED_TRADE.entrySet()) {
			Identifier professionID = Registries.VILLAGER_PROFESSION.getId(entry.getKey());
			String namespace = professionID.getNamespace();
			String basePath = professionID.getPath();
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
				Identifier tableID = new Identifier(namespace, basePath + "_" + suffix);

				OFFERS.put(tableID, offers);
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

	public static List<ExtendedTradeOfferFactory> getOffers(Identifier identifier) {
		return OFFERS.get(identifier);
	}

	public static Int2ObjectMap<TradeOffers.Factory[]> getOffersForProfession(VillagerProfession profession) {
		Identifier professionID = Registries.VILLAGER_PROFESSION.getId(profession);
		String namespace = professionID.getNamespace();
		String basePath = professionID.getPath();

		// Why Mojang coded these two to try and retrieve trades is beyond me, since they're not supposed to have any
		if (basePath.equals("none") || basePath.equals("nitwit")) {
			return new Int2ObjectOpenHashMap<>();
		}

		return copyToFastUtilMap(
			ImmutableMap.of(
				1, OFFERS.get(new Identifier(namespace, basePath + "_novice")).toArray(new TradeOffers.Factory[]{}),
				2, OFFERS.get(new Identifier(namespace, basePath + "_apprentice")).toArray(new TradeOffers.Factory[]{}),
				3, OFFERS.get(new Identifier(namespace, basePath + "_journeyman")).toArray(new TradeOffers.Factory[]{}),
				4, OFFERS.get(new Identifier(namespace, basePath + "_master")).toArray(new TradeOffers.Factory[]{}),
				5, OFFERS.get(new Identifier(namespace, basePath + "_expert")).toArray(new TradeOffers.Factory[]{})
			)
		);
	}

	public static Collection<Identifier> getIdentifiers() {
		return OFFERS.keySet();
	}

	private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
		return new Int2ObjectOpenHashMap<>(map);
	}
}
