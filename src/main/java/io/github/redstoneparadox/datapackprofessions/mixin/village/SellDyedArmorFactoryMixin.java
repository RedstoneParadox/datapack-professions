package io.github.redstoneparadox.datapackprofessions.mixin.village;

import io.github.redstoneparadox.datapackprofessions.trades.ExtendedTradeOfferFactory;
import io.github.redstoneparadox.datapackprofessions.trades.TradeOfferFactoryType;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TradeOffers.SellDyedArmorFactory.class)
public abstract class SellDyedArmorFactoryMixin implements ExtendedTradeOfferFactory {
	@Override
	public TradeOfferFactoryType getType() {
		return TradeOfferFactoryType.VanillaTypes.SELL_DYED_ARMOR_FACTORY_TYPE;
	}
}
