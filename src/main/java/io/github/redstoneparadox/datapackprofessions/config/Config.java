package io.github.redstoneparadox.datapackprofessions.config;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.quiltmc.loader.api.QuiltLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public record Config(List<PoiConfig> pois) {
	public static final Codec<Config> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codec.list(PoiConfig.CODEC).fieldOf("pois").forGetter(config -> config.pois)
		).apply(instance, Config::new)
	);

	public static Config load() {
		File file = new File(QuiltLoader.getConfigDir().toFile(), "datapack_professions.json");

		if (file.exists()) {
			try {
				JsonReader jsonReader = new JsonReader(new StringReader(Files.asCharSource(file, Charsets.UTF_8).read()));
				jsonReader.setLenient(true);
				Dynamic<JsonElement> dynamic = new Dynamic<>(JsonOps.INSTANCE, Streams.parse(jsonReader));
				var result = CODEC.decode(dynamic).result();

				jsonReader.close();

				if (result.isPresent()) {
					return result.get().getFirst();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		var pois = new ArrayList<PoiConfig>();
		var config = new Config(pois);

		var result = CODEC.encodeStart(JsonOps.INSTANCE, config).result();

		result.ifPresent(json -> {
			try {
				var exists = true;

				if (!file.exists()) {
					exists = file.createNewFile();
				}

				if (exists) {
					var jsonWriter = new JsonWriter(new BufferedWriter(new FileWriter(file)));
					jsonWriter.setLenient(true);
					Streams.write(json, jsonWriter);
					jsonWriter.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		return config;
	}
}
