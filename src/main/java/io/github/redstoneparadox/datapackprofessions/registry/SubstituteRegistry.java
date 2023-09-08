package io.github.redstoneparadox.datapackprofessions.registry;

import com.mojang.serialization.Lifecycle;
import net.minecraft.registry.Holder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

/**
 * Used to substitute a vanilla registry
 *
 * @param <T> The registry type
 */
public class SubstituteRegistry<T> extends SimpleRegistry<T> {
	private final Registry<T> vanilla;

	public SubstituteRegistry(RegistryKey<? extends Registry<T>> registryKey, Registry<T> vanilla) {
		super(registryKey, vanilla.getLifecycle());
		this.vanilla = vanilla;
	}

	public SubstituteRegistry(RegistryKey<? extends Registry<T>> key, boolean useIntrusiveHolders, Registry<T> vanilla) {
		super(key, vanilla.getLifecycle(), useIntrusiveHolders);
		this.vanilla = vanilla;
	}

	@Nullable
	@Override
	public T get(@Nullable Identifier id) {
		if (!containsId(id)) {
			return vanilla.get(id);
		}

		return super.get(id);
	}

	@Nullable
	@Override
	public T get(@Nullable RegistryKey<T> key) {
		if (!contains(key)) {
			return vanilla.get(key);
		}

		return super.get(key);
	}

	@Override
	public Registry<T> freeze() {
		vanilla.freeze();
		return super.freeze();
	}
}
