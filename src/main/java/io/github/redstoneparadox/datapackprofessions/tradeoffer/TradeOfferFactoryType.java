package io.github.redstoneparadox.datapackprofessions.tradeoffer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;

public record TradeOfferFactoryType(Codec<? extends ExtendedTradeOfferFactory> codec) {

	public static class VanillaTypes {
		public static final Codec<TradeOffers.SellItemFactory> SELL_ITEM_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Registries.ITEM.getCodec().fieldOf("sell").forGetter(sellItemFactory -> sellItemFactory.sell.getItem()),
				Codec.INT.fieldOf("price").forGetter(sellItemFactory -> sellItemFactory.price),
				Codec.INT.fieldOf("count").forGetter(sellItemFactory -> sellItemFactory.count),
				Codec.INT.fieldOf("max_uses").forGetter(sellItemFactory -> sellItemFactory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(sellItemFactory -> sellItemFactory.experience),
				Codec.FLOAT.fieldOf("multiplier").forGetter(sellItemFactory -> sellItemFactory.multiplier)
			).apply(instance, (sell, price, count, maxUses, experience, multiplier) ->
				new TradeOffers.SellItemFactory(new ItemStack(sell), price, count, experience, maxUses, multiplier)
			)
		);
		public static final TradeOfferFactoryType SELL_ITEM_TYPE = TradeOfferFactories.register(
			new Identifier("sell_item"),
			(Codec<? extends ExtendedTradeOfferFactory>) SELL_ITEM_CODEC
		);
	}
}
