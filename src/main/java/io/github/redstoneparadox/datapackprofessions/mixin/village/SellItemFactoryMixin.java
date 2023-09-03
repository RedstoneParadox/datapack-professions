package io.github.redstoneparadox.datapackprofessions.mixin.village;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.datapackprofessions.tradeoffer.ExtendedTradeOfferFactory;
import io.github.redstoneparadox.datapackprofessions.tradeoffer.TradeOfferFactories;
import io.github.redstoneparadox.datapackprofessions.tradeoffer.TradeOfferFactoryType;
import net.minecraft.entity.Entity;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TradeOffers.SellItemFactory.class)
public abstract class SellItemFactoryMixin implements ExtendedTradeOfferFactory {
	@Override
	public TradeOfferFactoryType getType() {
		return null;
	}
}
