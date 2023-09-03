package io.github.redstoneparadox.datapackprofessions.mixin.village;

import io.github.redstoneparadox.datapackprofessions.tradeoffer.ExtendedTradeOfferFactory;
import io.github.redstoneparadox.datapackprofessions.tradeoffer.TradeOfferFactoryType;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TradeOffers.BuyForOneEmeraldFactory.class)
public abstract class BuyForOneEmeraldFactoryMixin implements ExtendedTradeOfferFactory {
	@Override
	public TradeOfferFactoryType getType() {
		return TradeOfferFactoryType.VanillaTypes.BUY_FOR_ONE_EMERALD_TYPE;
	}
}
