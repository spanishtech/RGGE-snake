package me.soxey6.engine.managers.cheat;

import java.util.ArrayList;

import me.soxey6.engine.managers.cheat.cheats.IDKFA;
import me.soxey6.engine.managers.cheat.cheats.Konami;
import me.soxey6.engine.managers.event.EventCallback;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.objects.Cheat;
import me.soxey6.engine.objects.Scene;
import me.soxey6.utils.Logger;

import org.lwjgl.input.Keyboard;

/**
 * Manage your cheats with this amazing manager that allows the creation and management of cheats.
 * @author pchilds
 *
 */
public class CheatManager implements EventCallback{
	private Cheat match;
	private static CheatManager cheatManager;
	private String curCheat = "";
	private ArrayList<Cheat> cheatList = new ArrayList<Cheat>();
	
	public CheatManager()
	{
		cheatManager=this;
		cheatList.add(new IDKFA());
		cheatList.add(new Konami());
		EventManager.getEventManager().registerHook("KEY_DOWN", this);
	}
	
	/**
	 * Updates the hooks that is required to log the keys for each cheat.
	 */
	public void updateHooks()
	{
		// Go through every cheat
		for(Cheat cheat: cheatList)
		{
			// Check to make sure the lengths are compatable and won't throw and IndexOutOfBounds when used
			if(curCheat.length()>=cheat.getKeyCombination().length())
				continue;
			// check to see if the cheat matches the current typed character
			if(cheat.getKeyCombination().toUpperCase().contains(curCheat.toUpperCase()))
				// Register a new hook with the expected character
				EventManager.getEventManager().registerHook(Keyboard.getKeyIndex(String.valueOf(cheat.getKeyCombination().charAt(this.curCheat.length())))+"_DOWN", this);
		}
	}
	
	/**
	 * Processes the cheat
	 * @param scene The scene to send the information to
	 */
	public void processCheat(Scene scene)
	{
		if(match!=null)
		match.triggered(scene);
		match=null;
			
	}

	public static CheatManager getCheatManager() {
		return cheatManager;
	}

	public static void setCheatManager(CheatManager cheatManager) {
		CheatManager.cheatManager = cheatManager;
	}

	public ArrayList<Cheat> getCheatList() {
		return cheatList;
	}

	public void setCheatList(ArrayList<Cheat> cheatList) {
		this.cheatList = cheatList;
	}

	@Override
	/**
	 * The callback for events
	 */
	public void callback(String eventName) {
		if(eventName=="KEY_DOWN")
		{
			updateHooks();
		}
		for(Cheat cheat:cheatList)
			if(!(curCheat.length()>=cheat.getKeyCombination().length())&&eventName.contains(Keyboard.getKeyIndex(String.valueOf(cheat.getKeyCombination().charAt(this.curCheat.length())))+"_DOWN"))
				{
					EventManager.getEventManager().removeHook(Keyboard.getKeyIndex(String.valueOf(cheat.getKeyCombination().charAt(this.curCheat.length())))+"_DOWN", this);
					curCheat=curCheat+(cheat.getKeyCombination().charAt(curCheat.length()));
					break;
				}
		
		for(Cheat cheat:cheatList)
			if(cheat.getKeyCombination().equalsIgnoreCase(curCheat))
			{
				Logger.getLogger().log(Logger.getLogger().DEBUG, "Cheat entered: "+cheat.getName());
				match=cheat;
				curCheat="";
				return;
			}
	}

	

}
