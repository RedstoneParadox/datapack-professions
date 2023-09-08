package io.github.redstoneparadox.datapackprofessions.registry;

import io.github.redstoneparadox.datapackprofessions.DatapackProfessions;
import io.github.redstoneparadox.datapackprofessions.mixin.world.poi.PointOfInterestTypesAccessor;
import io.github.redstoneparadox.datapackprofessions.util.DatapackProfessionsCodecs;
import net.minecraft.registry.Holder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Util;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.quiltmc.qsl.registry.api.dynamic.DynamicMetaRegistry;
import org.quiltmc.qsl.registry.api.event.RegistryEvents;

import java.security.Key;

public class DatapackProfessionsRegistries {

	public static void init() {
		DynamicMetaRegistry.registerSynced(Keys.DATAPACK_POI_TYPES, DatapackProfessionsCodecs.POINT_OF_INTEREST_TYPE_CODEC);
		RegistryEvents.DYNAMIC_REGISTRY_LOADED.register(registryManager -> {
			var registry = registryManager.get(Keys.DATAPACK_POI_TYPES);

			registry.getEntries().forEach(entry -> {
				var key = entry.getKey();
				var type = entry.getValue();

				type.blockStates().forEach(state -> {
					Holder<PointOfInterestType> replaced;
					try {
						replaced = PointOfInterestTypesAccessor.getMap().put(state, Registries.POINT_OF_INTEREST_TYPE.getHolderOrThrow(key));
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
					if (replaced != null) {
						throw Util.throwOrPause(new IllegalStateException(String.format("%s is defined in too many tags", state)));
					}
				});
			});
		});
	}

	public static class Keys {
		public static final RegistryKey<Registry<PointOfInterestType>> DATAPACK_POI_TYPES = RegistryKey.ofRegistry(
			DatapackProfessions.id("point_of_interest_type")
		);
	}
}
