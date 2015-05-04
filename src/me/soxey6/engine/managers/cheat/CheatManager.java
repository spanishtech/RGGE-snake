package me.soxey6.engine.managers.cheat;

import java.util.ArrayList;

import me.soxey6.engine.managers.cheat.cheats.IDKFA;
import me.soxey6.engine.managers.cheat.cheats.Konami;
import me.soxey6.engine.objects.Cheat;
import me.soxey6.engine.objects.Scene;
import me.soxey6.utils.Logger;

import org.lwjgl.input.Keyboard;

/**
 * Manage your cheats with this amazing manager that allows the creation and management of cheats.
 * @author pchilds
 *
 */
public class CheatManager{
	private Cheat match;
	private static CheatManager cheatManager;
	private String curCheat = "";
	private ArrayList<Cheat> cheatList = new ArrayList<Cheat>();
	
	public CheatManager()
	{
		cheatManager=this;
		this.cheatList.add(new IDKFA());
		this.cheatList.add(new Konami());
	}
	
	public void cheatInputCheck()
	{
		for(Cheat cheat:cheatList)
		{
			if(cheat.getKeyCombination().equalsIgnoreCase(curCheat))
			{
				Logger.getLogger().log(Logger.getLogger().DEBUG, "Cheat entered: "+cheat.getName());
				match=cheat;
				curCheat="";
				return;
			}
			if(curCheat.length()>=cheat.getKeyCombination().length())
				continue;
			if(Keyboard.isKeyDown(Keyboard.getKeyIndex(String.valueOf(cheat.getKeyCombination().charAt(this.curCheat.length()))))&&cheat.getKeyCombination().contains(curCheat))
				this.curCheat = this.curCheat+(String.valueOf(cheat.getKeyCombination().charAt(this.curCheat.length())));
		}
		
	}
	
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
	

}
