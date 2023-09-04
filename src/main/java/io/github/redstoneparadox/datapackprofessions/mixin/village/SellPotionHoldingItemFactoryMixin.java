package io.github.redstoneparadox.datapackprofessions.mixin.village;

import io.github.redstoneparadox.datapackprofessions.trades.ExtendedTradeOfferFactory;
import io.github.redstoneparadox.datapackprofessions.trades.TradeOfferFactoryType;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TradeOffers.SellPotionHoldingItemFactory.class)
public abstract class SellPotionHoldingItemFactoryMixin implements ExtendedTradeOfferFactory {
	@Override
	public TradeOfferFactoryType getType() {
		return TradeOfferFactoryType.VanillaTypes.SELL_POTION_HOLDING_ITEM_FACTORY_TYPE;
	}
}
