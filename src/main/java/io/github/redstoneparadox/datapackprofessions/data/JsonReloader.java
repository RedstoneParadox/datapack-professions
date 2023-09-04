package io.github.redstoneparadox.datapackprofessions.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.github.redstoneparadox.datapackprofessions.DatapackProfessions;
import net.minecraft.registry.ResourceFileNamespace;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.qsl.resource.loader.api.reloader.SimpleResourceReloader;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public abstract class JsonReloader implements SimpleResourceReloader<Map<Identifier, JsonElement>> {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private final String dataType;

	public JsonReloader(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public @NotNull Identifier getQuiltId() {
		return DatapackProfessions.id(dataType);
	}

	@Override
	public CompletableFuture<Map<Identifier, JsonElement>> load(ResourceManager manager, Profiler profiler, Executor executor) {
		return CompletableFuture.supplyAsync(() -> {
			Map<Identifier, JsonElement> map = new HashMap<>();
			prepare(manager, this.dataType, GSON, map);
			return map;
		}, executor);
	}

	public static void prepare(ResourceManager resourceManager, String dataType, Gson gson, Map<Identifier, JsonElement> resources) {
		ResourceFileNamespace resourceFileNamespace = ResourceFileNamespace.json(dataType);

		for(Map.Entry<Identifier, Resource> entry : resourceFileNamespace.findMatchingResources(resourceManager).entrySet()) {
			Identifier identifier = entry.getKey();
			Identifier identifier2 = resourceFileNamespace.unwrapFilePath(identifier);

			try {
				Reader reader = entry.getValue().openBufferedReader();

				try {
					JsonElement jsonElement = JsonHelper.deserialize(gson, reader, JsonElement.class);
					JsonElement jsonElement2 = resources.put(identifier2, jsonElement);
					if (jsonElement2 != null) {
						throw new IllegalStateException("Duplicate data file ignored with ID " + identifier2);
					}
				} catch (Throwable var13) {
					if (reader != null) {
						try {
							reader.close();
						} catch (Throwable var12) {
							var13.addSuppressed(var12);
						}
					}

					throw var13;
				}

				reader.close();
			} catch (IllegalArgumentException | IOException | JsonParseException var14) {
				DatapackProfessions.LOGGER.error("Couldn't parse data file {} from {}", identifier2, identifier, var14);
			}
		}
	}

	@Override
	public CompletableFuture<Void> apply(Map<Identifier, JsonElement> data, ResourceManager manager, Profiler profiler, Executor executor) {
		return CompletableFuture.runAsync(() -> {
			apply(data, manager, profiler);
		}, executor);
	}

	protected abstract void apply(Map<Identifier, JsonElement> cache, ResourceManager manager, Profiler profiler);
}
