package io.github.redstoneparadox.datapackprofessions.mixin.entity.passive;

import io.github.redstoneparadox.datapackprofessions.trades.LoadedTradeOffers;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {
	@Redirect(method = "fillRecipes", at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
	private Object getLoadedTrades(Map instance, Object o) {
		VillagerEntity self = ((VillagerEntity)((Object)this));
		VillagerProfession profession = self.getVillagerData().getProfession();

		return LoadedTradeOffers.getOffersForProfession(profession);
	}
}
