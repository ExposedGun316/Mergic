package no.runsafe.mergic.magic;

import no.runsafe.framework.minecraft.player.RunsafePlayer;

public interface Spell
{
	public int getCooldown();
	public String getName();
	public MagicSchool getSchool();
	public SpellType getType();
	public String getDescription();
	public void onCast(RunsafePlayer player);
}
