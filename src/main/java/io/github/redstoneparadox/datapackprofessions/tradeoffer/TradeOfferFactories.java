package io.github.redstoneparadox.datapackprofessions.tradeoffer;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class TradeOfferFactories {
	private static final BiMap<Identifier, TradeOfferFactoryType> TYPES = HashBiMap.create();
	public static Codec<TradeOfferFactoryType> TYPE_CODEC = Identifier.CODEC.flatXmap(
		id -> {
			TradeOfferFactoryType type = TYPES.get(id);
			return type != null ? DataResult.success(type) : DataResult.error(() -> "Unknown trade offer factory type: " + id);
			},
		type -> {
			Identifier identifier = TYPES.inverse().get(type);
			return identifier != null ? DataResult.success(identifier) : DataResult.error(() -> "Unknown trade offer factory type");
			}
		);
	public static Codec<ExtendedTradeOfferFactory> CODEC = TYPE_CODEC.dispatch(ExtendedTradeOfferFactory::getType, TradeOfferFactoryType::codec);
}
