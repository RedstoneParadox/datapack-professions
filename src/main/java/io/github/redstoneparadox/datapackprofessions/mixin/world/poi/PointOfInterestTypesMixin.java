package io.github.redstoneparadox.datapackprofessions.mixin.world.poi;

import io.github.redstoneparadox.datapackprofessions.DatapackProfessions;
import io.github.redstoneparadox.datapackprofessions.config.PoiConfig;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Mixin(PointOfInterestTypes.class)
public abstract class PointOfInterestTypesMixin {
	@Shadow
	private static PointOfInterestType register(Registry<PointOfInterestType> registry, RegistryKey<PointOfInterestType> key, Set<BlockState> states, int ticketCount, int searchDistance) {
		throw new IllegalStateException();
	}

	@Inject(method = "initialize", at = @At("TAIL"))
	private static void initialize(Registry<PointOfInterestType> registry, CallbackInfoReturnable<PointOfInterestType> cir) {
		var config = DatapackProfessions.CONFIG;

		for (PoiConfig poi : config.pois()) {
			var key = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, poi.id());
			register(registry, key, Set.copyOf(poi.states()), poi.maxTickets(), poi.searchDistance());
		}
	}
}
