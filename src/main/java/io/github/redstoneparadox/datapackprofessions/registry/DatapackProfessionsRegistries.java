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
import org.quiltmc.qsl.registry.api.dynamic.DynamicMetaRegistry;
import org.quiltmc.qsl.registry.api.event.RegistryEvents;

public class DatapackProfessionsRegistries {

	public static void init() {
		DynamicMetaRegistry.registerSynced(Keys.DYNAMIC_POINT_OF_INTEREST_TYPE, DatapackProfessionsCodecs.POINT_OF_INTEREST_TYPE_CODEC);
		RegistryEvents.DYNAMIC_REGISTRY_SETUP.register(context -> Registries.POINT_OF_INTEREST_TYPE.getEntries().forEach(entry -> {
			var key = entry.getKey();
			var type = entry.getValue();

			context.register(Keys.DYNAMIC_POINT_OF_INTEREST_TYPE, key.getValue(), () -> type);
		}));
		RegistryEvents.DYNAMIC_REGISTRY_LOADED.register(registryManager -> {
			var optional = registryManager.getOptional(Keys.DYNAMIC_POINT_OF_INTEREST_TYPE);

			optional.ifPresent(registry -> registry.getEntries().forEach(entry -> {
				var key = entry.getKey();
				var type = entry.getValue();

				type.blockStates().forEach(state -> {
					try {
						PointOfInterestTypesAccessor.getMap().put(state, registry.getHolderOrThrow(key));
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				});
			}));
		});
	}

	public static class Keys {
		public static final RegistryKey<Registry<PointOfInterestType>> DYNAMIC_POINT_OF_INTEREST_TYPE = RegistryKey.ofRegistry(
			DatapackProfessions.id("point_of_interest_type")
		);
	}
}
