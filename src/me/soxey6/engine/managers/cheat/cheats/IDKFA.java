package me.soxey6.engine.managers.cheat.cheats;

import me.soxey6.engine.objects.Cheat;
import me.soxey6.engine.objects.Scene;

/**
 * A small nod to doom with this cheat.
 * @author pchilds
 *
 */
public class IDKFA extends Cheat{

	public IDKFA() {
		super("IDFKA", "IDKFA");
	}
	
	@Override
	public void triggered(Scene scene)
	{
		getLogger().log(getLogger().INFO, "IDGAF about doom.");
	}
}
