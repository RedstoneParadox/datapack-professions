package io.github.redstoneparadox.datapackprofessions.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DatapackProfessionsCodecs {
	public static final Codec<PointOfInterestType> POINT_OF_INTEREST_TYPE_CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			createSetCodec(BlockState.CODEC).fieldOf("block_states").forGetter(PointOfInterestType::blockStates),
			Codec.INT.fieldOf("max_tickets").forGetter(PointOfInterestType::maxTickets),
			Codec.INT.fieldOf("search_distance").forGetter(PointOfInterestType::searchDistance)
		).apply(instance, PointOfInterestType::new)
	);

	private static <E> Codec<Set<E>> createSetCodec(Codec<E> elementCodec) {
		return Codec.list(elementCodec).flatXmap(list -> DataResult.success(Set.copyOf(list)), set -> DataResult.success(List.copyOf(set)));
	}
}
