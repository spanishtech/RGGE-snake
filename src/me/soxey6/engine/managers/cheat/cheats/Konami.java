package me.soxey6.engine.managers.cheat.cheats;

import me.soxey6.engine.managers.sound.SoundManager;
import me.soxey6.engine.objects.Cheat;
import me.soxey6.engine.objects.Scene;

/**
 * This code is amazing, idea by: Jduncan
 * @author pchilds
 *
 */
public class Konami extends Cheat{

	public Konami() {
		super("Konami", "UUDDLRLRBASS");
	}

	@Override
	public void triggered(Scene scene)
	{
		// Create an instance of test.aiff
		SoundManager.getSoundManager().newMusic("test.aiff");
		
		// Stop the main theme if it's already been created
		if(SoundManager.getSoundManager().getMusic("main_theme.aiff")!=null)
			SoundManager.getSoundManager().getMusic("main_theme.aiff").stop();
		
		// Stop the death music if it's already been created.
		if(SoundManager.getSoundManager().getMusic("death.aiff")!=null)
			SoundManager.getSoundManager().getMusic("death.aiff").stop();
		
		// Play test.aiff
		SoundManager.getSoundManager().playMusicLooped("test.aiff");
	}
}
