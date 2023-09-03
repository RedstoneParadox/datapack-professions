package io.github.redstoneparadox.datapackprofessions.tradeoffer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;

import java.util.Objects;

public record TradeOfferFactoryType(Codec<? extends ExtendedTradeOfferFactory> codec) {
	public static class VanillaTypes {
		public static final Codec<TradeOffers.BuyForOneEmeraldFactory> BUY_FOR_ONE_EMERALD_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Registries.ITEM.getCodec().fieldOf("buy").forGetter(factory -> factory.buy),
				Codec.INT.fieldOf("price").forGetter(factory -> factory.price),
				Codec.INT.fieldOf("max_uses").forGetter(factory -> factory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience)
			).apply(instance, TradeOffers.BuyForOneEmeraldFactory::new)
		);
		public static final TradeOfferFactoryType BUY_FOR_ONE_EMERALD_TYPE = TradeOfferFactories.register(
			new Identifier("buy_for_one_emerald"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) BUY_FOR_ONE_EMERALD_CODEC)
		);
		public static final Codec<TradeOffers.EnchantBookFactory> ENCHANT_BOOK_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience)
			).apply(instance, TradeOffers.EnchantBookFactory::new)
		);
		public static final TradeOfferFactoryType ENCHANT_BOOK_TYPE = TradeOfferFactories.register(
			new Identifier("enchant_book"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) ENCHANT_BOOK_CODEC)
		);
		public static final Codec<TradeOffers.SellItemFactory> SELL_ITEM_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Registries.ITEM.getCodec().fieldOf("sell").forGetter(sellItemFactory -> sellItemFactory.sell.getItem()),
				Codec.INT.fieldOf("price").forGetter(factory -> factory.price),
				Codec.INT.fieldOf("count").forGetter(factory -> factory.count),
				Codec.INT.fieldOf("max_uses").forGetter(factory -> factory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience),
				Codec.FLOAT.fieldOf("multiplier").forGetter(factory -> factory.multiplier)
			).apply(instance, (sell, price, count, maxUses, experience, multiplier) ->
				new TradeOffers.SellItemFactory(new ItemStack(sell), price, count, experience, maxUses, multiplier)
			)
		);
		public static final TradeOfferFactoryType SELL_ITEM_TYPE = TradeOfferFactories.register(
			new Identifier("sell_item"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) SELL_ITEM_CODEC)
		);
	}
}
