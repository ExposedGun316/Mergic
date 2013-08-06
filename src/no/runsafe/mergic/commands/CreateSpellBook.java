package no.runsafe.mergic.commands;

import no.runsafe.framework.api.command.argument.TrailingArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import no.runsafe.mergic.spells.Spell;
import no.runsafe.mergic.spells.SpellHandler;

import java.util.Map;

public class CreateSpellBook extends PlayerCommand
{
	public CreateSpellBook(SpellHandler spellHandler)
	{
		super("createbook", "Creates a spell book", "runsafe.mergic.books.create", new TrailingArgument("spell"));
		this.spellHandler = spellHandler;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		Spell spell = this.spellHandler.getSpellByName(parameters.get("spell"));
		if (spell == null)
			return "&cUnable to find spell with that name.";

		this.spellHandler.givePlayerSpellBook(executor, spell);
		return "&2Created spell-book: " + spell.getName();
	}

	private SpellHandler spellHandler;
}
