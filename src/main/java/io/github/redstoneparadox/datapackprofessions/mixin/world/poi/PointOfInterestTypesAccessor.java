package io.github.redstoneparadox.datapackprofessions.mixin.world.poi;

import net.minecraft.block.BlockState;
import net.minecraft.registry.Holder;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(PointOfInterestTypes.class)
public interface PointOfInterestTypesAccessor {
	@Accessor("STATE_TO_TYPE")
	static Map<BlockState, Holder<PointOfInterestType>> getMap() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
