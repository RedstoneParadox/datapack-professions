package io.github.redstoneparadox.datapackprofessions.trades;

import net.minecraft.village.TradeOffers;

public interface ExtendedTradeOfferFactory extends TradeOffers.Factory {
	TradeOfferFactoryType getType();
}
