package io.github.redstoneparadox.datapackprofessions.trades;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record TradeTable(boolean replace, List<ExtendedTradeOfferFactory> offers) {
	public static final Codec<TradeTable> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codec.BOOL.optionalFieldOf("replace", false).forGetter(tradeTable -> tradeTable.replace),
			Codec.list(TradeOfferFactories.CODEC).fieldOf("offers").forGetter(tradeTable -> tradeTable.offers)
		).apply(instance, TradeTable::new)
	);
}
