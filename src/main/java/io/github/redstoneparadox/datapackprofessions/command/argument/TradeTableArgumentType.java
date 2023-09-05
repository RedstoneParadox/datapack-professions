package io.github.redstoneparadox.datapackprofessions.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.github.redstoneparadox.datapackprofessions.trades.LoadedTradeOffers;
import io.github.redstoneparadox.datapackprofessions.trades.TradeTable;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TradeTableArgumentType implements ArgumentType<TradeTableArgument> {
	private static final Collection<String> EXAMPLES = Arrays.asList("foo", "foo:bar", "012");

	public static TradeTableArgumentType tradeTable() {
		return new TradeTableArgumentType();
	}
	@Override
	public TradeTableArgument parse(StringReader reader) throws CommandSyntaxException {
		Identifier identifier = Identifier.fromCommandInput(reader);
		TradeTable table = new TradeTable(false, LoadedTradeOffers.getOffers(identifier));

		return new TradeTableArgument(identifier, table);
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		List<Identifier> identifiers = LoadedTradeOffers.getIdentifiers().stream().sorted().toList();

		for (Identifier identifier : identifiers) {
			builder.suggest(identifier.toString());
		}

		return builder.buildFuture();
	}

	@Override
	public Collection<String> getExamples() {
		return EXAMPLES;
	}
}
