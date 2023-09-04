package io.github.redstoneparadox.datapackprofessions.tradeoffer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapIcon;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;

public record TradeOfferFactoryType(Codec<? extends ExtendedTradeOfferFactory> codec) {
	public static class VanillaTypes {
		private static final Codec<Item> ITEM_CODEC = Registries.ITEM.getCodec();
		public static final Codec<TradeOffers.BuyForOneEmeraldFactory> BUY_FOR_ONE_EMERALD_FACTORY_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				ITEM_CODEC.fieldOf("buy").forGetter(factory -> factory.buy),
				Codec.INT.fieldOf("price").forGetter(factory -> factory.price),
				Codec.INT.fieldOf("max_uses").forGetter(factory -> factory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience)
			).apply(instance, TradeOffers.BuyForOneEmeraldFactory::new)
		);
		public static final TradeOfferFactoryType BUY_FOR_ONE_EMERALD_FACTORY_TYPE = TradeOfferFactories.register(
			new Identifier("buy_for_one_emerald"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) BUY_FOR_ONE_EMERALD_FACTORY_CODEC)
		);
		public static final Codec<TradeOffers.EnchantBookFactory> ENCHANT_BOOK_FACTORY_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience)
			).apply(instance, TradeOffers.EnchantBookFactory::new)
		);
		public static final TradeOfferFactoryType ENCHANT_BOOK_FACTORY_TYPE = TradeOfferFactories.register(
			new Identifier("enchant_book"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) ENCHANT_BOOK_FACTORY_CODEC)
		);
		public static final Codec<TradeOffers.ProcessItemFactory> PROCESS_ITEM_FACTORY_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				ITEM_CODEC.fieldOf("second_buy").forGetter(factory -> factory.secondBuy.getItem()),
				Codec.INT.fieldOf("second_count").forGetter(factory -> factory.secondCount),
				Codec.INT.fieldOf("price").forGetter(factory -> factory.price),
				ITEM_CODEC.fieldOf("sell").forGetter(factory -> factory.sell.getItem()),
				Codec.INT.fieldOf("sell_count").forGetter(factory -> factory.sellCount),
				Codec.INT.fieldOf("max_uses").forGetter(factory -> factory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience)
			).apply(instance, TradeOffers.ProcessItemFactory::new)
		);
		public static final TradeOfferFactoryType PROCESS_ITEM_FACTORY_TYPE = TradeOfferFactories.register(
			new Identifier("process_item"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) PROCESS_ITEM_FACTORY_CODEC)
		);
		public static final Codec<TradeOffers.SellDyedArmorFactory> SELL_DYED_ARMOR_FACTORY_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				ITEM_CODEC.fieldOf("sell").forGetter(factory -> factory.sell),
				Codec.INT.fieldOf("price").forGetter(factory -> factory.price),
				Codec.INT.fieldOf("max_uses").forGetter(factory -> factory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience)
			).apply(instance, TradeOffers.SellDyedArmorFactory::new)
		);
		public static final TradeOfferFactoryType SELL_DYED_ARMOR_FACTORY_TYPE = TradeOfferFactories.register(
			new Identifier("sell_dyed_armor"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) SELL_DYED_ARMOR_FACTORY_CODEC)
		);
		public static final Codec<TradeOffers.SellEnchantedToolFactory> SELL_ENCHANTED_TOOL_FACTORY_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				ITEM_CODEC.fieldOf("tool").forGetter(factory -> factory.tool.getItem()),
				Codec.INT.fieldOf("base_price").forGetter(factory -> factory.basePrice),
				Codec.INT.fieldOf("max_uses").forGetter(factory -> factory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience),
				Codec.FLOAT.fieldOf("multiplier").forGetter(factory -> factory.multiplier)
			).apply(instance, TradeOffers.SellEnchantedToolFactory::new)
		);
		public static final TradeOfferFactoryType SELL_ENCHANTED_TOOL_FACTORY_TYPE = TradeOfferFactories.register(
			new Identifier("sell_enchanted_tool"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) SELL_ENCHANTED_TOOL_FACTORY_CODEC)
		);
		public static final Codec<TradeOffers.SellItemFactory> SELL_ITEM_FACTORY_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				ITEM_CODEC.xmap(ItemStack::new, ItemStack::getItem).fieldOf("sell").forGetter(sellItemFactory -> sellItemFactory.sell),
				Codec.INT.fieldOf("price").forGetter(factory -> factory.price),
				Codec.INT.fieldOf("count").forGetter(factory -> factory.count),
				Codec.INT.fieldOf("max_uses").forGetter(factory -> factory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience),
				Codec.FLOAT.fieldOf("multiplier").forGetter(factory -> factory.multiplier)
			).apply(instance, TradeOffers.SellItemFactory::new)
		);
		public static final TradeOfferFactoryType SELL_ITEM_FACTORY_TYPE = TradeOfferFactories.register(
			new Identifier("sell_item"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) SELL_ITEM_FACTORY_CODEC)
		);
		public static final Codec<TradeOffers.SellMapFactory> SELL_MAP_FACTORY_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Codec.INT.fieldOf("price").forGetter(factory -> factory.price),
				TagKey.createCodec(RegistryKeys.STRUCTURE_FEATURE).fieldOf("structures").forGetter(factory -> factory.structures),
				Codec.STRING.fieldOf("map_name").forGetter(factory -> factory.mapName),
				Codec.BYTE.xmap(MapIcon.Type::byId, MapIcon.Type::getId).fieldOf("icon_type").forGetter(factory -> factory.iconType),
				Codec.INT.fieldOf("max_uses").forGetter(factory -> factory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience)
			).apply(instance, TradeOffers.SellMapFactory::new)
		);
		public static final TradeOfferFactoryType SELL_MAP_FACTORY_TYPE = TradeOfferFactories.register(
			new Identifier("sell_map"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) SELL_MAP_FACTORY_CODEC)
		);
		public static final Codec<TradeOffers.SellPotionHoldingItemFactory> SELL_POTION_HOLDING_ITEM_FACTORY_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				ITEM_CODEC.fieldOf("second_buy").forGetter(factory -> factory.secondBuy),
				Codec.INT.fieldOf("second_count").forGetter(factory -> factory.secondCount),
				ITEM_CODEC.fieldOf("sell").forGetter(factory -> factory.sell.getItem()),
				Codec.INT.fieldOf("sell_count").forGetter(factory -> factory.sellCount),
				Codec.INT.fieldOf("price").forGetter(factory -> factory.price),
				Codec.INT.fieldOf("max_uses").forGetter(factory -> factory.maxUses),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience)
			).apply(instance, TradeOffers.SellPotionHoldingItemFactory::new)
		);
		public static final TradeOfferFactoryType SELL_POTION_HOLDING_ITEM_FACTORY_TYPE = TradeOfferFactories.register(
			new Identifier("sell_potion_holding_item"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) SELL_POTION_HOLDING_ITEM_FACTORY_CODEC)
		);
		public static final Codec<TradeOffers.SuspiciousStewForEmeraldFactory> SUSPICIOUS_STEW_FOR_EMERALD_FACTORY_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Registries.STATUS_EFFECT.getCodec().fieldOf("effect").forGetter(factory -> factory.effect),
				Codec.INT.fieldOf("duration").forGetter(factory -> factory.experience),
				Codec.INT.fieldOf("experience").forGetter(factory -> factory.experience)
			).apply(instance, TradeOffers.SuspiciousStewForEmeraldFactory::new)
		);
		public static final TradeOfferFactoryType SUSPICIOUS_STEW_FOR_EMERALD_FACTORY_TYPE = TradeOfferFactories.register(
			new Identifier("suspicious_stew_for_emerald"),
			(Codec<? extends ExtendedTradeOfferFactory>)((Object) SUSPICIOUS_STEW_FOR_EMERALD_FACTORY_CODEC)
		);
	}
}
