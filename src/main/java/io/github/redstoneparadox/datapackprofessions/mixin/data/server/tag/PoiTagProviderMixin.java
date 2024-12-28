package io.github.redstoneparadox.datapackprofessions.mixin.data.server.tag;

import io.github.redstoneparadox.datapackprofessions.DatapackProfessions;
import io.github.redstoneparadox.datapackprofessions.config.PoiConfig;
import net.minecraft.data.DataPackOutput;
import net.minecraft.data.server.tag.AbstractTagProvider;
import net.minecraft.data.server.tag.PoiTagProvider;
import net.minecraft.registry.HolderLookup;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.PoiTags;
import net.minecraft.world.poi.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.CompletableFuture;

@Mixin(PoiTagProvider.class)
public abstract class PoiTagProviderMixin extends AbstractTagProvider<PointOfInterestType> {
	protected PoiTagProviderMixin(DataPackOutput output, RegistryKey<? extends Registry<PointOfInterestType>> key, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, key, lookupProvider);
	}

	@Inject(method = "configure", at = @At("TAIL"))
	private void configure(HolderLookup.Provider lookup, CallbackInfo ci) {
		var builder = this.getOrCreateTagBuilder(PoiTags.ACQUIRABLE_JOB_SITE);

		for (PoiConfig poi : DatapackProfessions.CONFIG.pois()) {
			var key = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, poi.id());
			builder.add(key);
		}
	}
}
