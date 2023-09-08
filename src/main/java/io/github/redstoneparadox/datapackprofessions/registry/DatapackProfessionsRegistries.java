package io.github.redstoneparadox.datapackprofessions.registry;

import io.github.redstoneparadox.datapackprofessions.DatapackProfessions;
import io.github.redstoneparadox.datapackprofessions.util.DatapackProfessionsCodecs;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;
import org.quiltmc.qsl.registry.api.dynamic.DynamicMetaRegistry;
import org.quiltmc.qsl.registry.api.event.RegistryEvents;

import java.security.Key;

public class DatapackProfessionsRegistries {
	public static Registry<PointOfInterestType> DATAPACK_POI_TYPES;

	public static void init() {
		DynamicMetaRegistry.registerSynced(Keys.DATAPACK_POI_TYPES, DatapackProfessionsCodecs.POINT_OF_INTEREST_TYPE_CODEC);
		RegistryEvents.DYNAMIC_REGISTRY_LOADED.register(registryManager -> {
			DATAPACK_POI_TYPES = registryManager.get(Keys.DATAPACK_POI_TYPES);
		});
	}

	public static class Keys {
		public static final RegistryKey<Registry<PointOfInterestType>> DATAPACK_POI_TYPES = RegistryKey.ofRegistry(
			DatapackProfessions.id("point_of_interest_type")
		);
	}
}
