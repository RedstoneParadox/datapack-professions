package io.github.redstoneparadox.datapackprofessions.tradeoffer;

import net.minecraft.village.TradeOffers;

public interface ExtendedTradeOfferFactory extends TradeOffers.Factory {
	TradeOfferFactoryType getType();
}
