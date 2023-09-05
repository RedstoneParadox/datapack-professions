package io.github.redstoneparadox.datapackprofessions.command.argument;

import io.github.redstoneparadox.datapackprofessions.trades.TradeTable;
import net.minecraft.util.Identifier;

public record TradeTableArgument(Identifier identifier, TradeTable table) {
}
