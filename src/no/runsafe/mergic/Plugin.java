package no.runsafe.mergic;

import no.runsafe.framework.RunsafeConfigurablePlugin;
import no.runsafe.framework.api.command.Command;
import no.runsafe.mergic.commands.CreateSpellBook;
import no.runsafe.mergic.commands.StartGameCommand;
import no.runsafe.mergic.commands.StopGameCommand;
import no.runsafe.mergic.spells.Fireball;
import no.runsafe.mergic.spells.SpellHandler;
import no.runsafe.worldguardbridge.WorldGuardInterface;

public class Plugin extends RunsafeConfigurablePlugin
{
	@Override
	protected void PluginSetup()
	{
		addComponent(getFirstPluginAPI(WorldGuardInterface.class));

		// Arena related things.
		this.addComponent(Arena.class);
		this.addComponent(Lobby.class);
		this.addComponent(Graveyard.class);
		this.addComponent(Game.class);
		this.addComponent(PlayerMonitor.class);

		// Spell related things.
		this.addComponent(SpellHandler.class);

		// Spell list
		this.addComponent(Fireball.class);

		// Commands
		Command mergic = new Command("mergic", "A collection of commands to control Wizard PvP", null);
		mergic.addSubCommand(getInstance(StartGameCommand.class));
		mergic.addSubCommand(getInstance(StopGameCommand.class));
		mergic.addSubCommand(getInstance(CreateSpellBook.class));
		this.addComponent(mergic);
	}
}
