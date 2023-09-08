package io.github.redstoneparadox.datapackprofessions.mixin.server.command;

import io.github.redstoneparadox.datapackprofessions.registry.DatapackProfessionsRegistries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.LocateCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LocateCommand.class)
public abstract class LocateCommandMixin {
	@ModifyArgs(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/command/argument/RegistryEntryOrTagArgument;create(Lnet/minecraft/command/CommandBuildContext;Lnet/minecraft/registry/RegistryKey;)Lnet/minecraft/command/argument/RegistryEntryOrTagArgument;"))
	private static void changePoiKey(Args args) {
		if (args.get(1) == RegistryKeys.POINT_OF_INTEREST_TYPE) {
			args.set(1, DatapackProfessionsRegistries.Keys.DYNAMIC_POINT_OF_INTEREST_TYPE);
		}
	}
}
