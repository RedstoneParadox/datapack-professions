package io.github.redstoneparadox.datapackprofessions.mixin.village;

import io.github.redstoneparadox.datapackprofessions.trades.ExtendedTradeOfferFactory;
import io.github.redstoneparadox.datapackprofessions.trades.TradeOfferFactoryType;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TradeOffers.TypeAwareBuyForOneEmeraldFactory.class)
public abstract class TypeAwareBuyForOneEmeraldFactoryMixin implements ExtendedTradeOfferFactory {
	@Override
	public TradeOfferFactoryType getType() {
		return TradeOfferFactoryType.VanillaTypes.TYPE_AWARE_BUY_FOR_ONE_EMERALD_FACTORY_TYPE;
	}
}
