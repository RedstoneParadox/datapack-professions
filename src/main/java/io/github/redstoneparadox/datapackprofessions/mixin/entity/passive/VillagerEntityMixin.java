package io.github.redstoneparadox.datapackprofessions.mixin.entity.passive;

import io.github.redstoneparadox.datapackprofessions.trades.LoadedTradeOffers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {
	@Redirect(method = "fillRecipes", at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;get(I)Ljava/lang/Object;"))
	private Object getLoadedTrades(Int2ObjectMap instance, int i) {
		VillagerEntity self = ((VillagerEntity)((Object)this));
		VillagerProfession profession = self.getVillagerData().getProfession();

		return LoadedTradeOffers.getOffersForProfession(profession);
	}
}
