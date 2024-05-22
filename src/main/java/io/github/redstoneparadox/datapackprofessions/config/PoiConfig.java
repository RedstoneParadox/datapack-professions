package io.github.redstoneparadox.datapackprofessions.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

import java.util.List;

public record PoiConfig(Identifier id, List<BlockState> states, int maxTickets, int searchDistance) {
	public static final Codec<PoiConfig> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Identifier.CODEC.fieldOf("id").forGetter(config -> config.id),
			Codec.list(BlockState.CODEC).fieldOf("block_states").forGetter(config -> config.states),
			Codec.INT.fieldOf("max_tickets").forGetter(config -> config.maxTickets),
			Codec.INT.fieldOf("search_distance").forGetter(config -> config.searchDistance)
		).apply(instance, PoiConfig::new)
	);
}
