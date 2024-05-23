package io.github.redstoneparadox.datapackprofessions.mixin.village;

import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(VillagerProfession.class)
public interface VillagerProfessionAccessor {
	@Invoker("register")
	static VillagerProfession register(String id, RegistryKey<PointOfInterestType> key, @Nullable SoundEvent workSound) throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
