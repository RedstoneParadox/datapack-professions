package io.github.redstoneparadox.datapackprofessions.mixin.village;

import io.github.redstoneparadox.datapackprofessions.trades.ExtendedTradeOfferFactory;
import io.github.redstoneparadox.datapackprofessions.trades.TradeOfferFactoryType;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TradeOffers.SellEnchantedToolFactory.class)
public abstract class SellEnchantedToolFactoryMixin implements ExtendedTradeOfferFactory {
	@Override
	public TradeOfferFactoryType getType() {
		return TradeOfferFactoryType.VanillaTypes.SELL_ENCHANTED_TOOL_FACTORY_TYPE;
	}
}
