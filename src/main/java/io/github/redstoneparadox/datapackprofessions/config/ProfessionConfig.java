package io.github.redstoneparadox.datapackprofessions.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

public record ProfessionConfig(String id, Identifier poi) {
	public static Codec<ProfessionConfig> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codec.STRING.fieldOf("id").forGetter(config -> config.id),
			Identifier.CODEC.fieldOf("poi").forGetter(config -> config.poi)
		).apply(instance, ProfessionConfig::new)
	);
}
