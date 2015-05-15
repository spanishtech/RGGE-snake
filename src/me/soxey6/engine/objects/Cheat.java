package me.soxey6.engine.objects;

import me.soxey6.engine.main.Wrapper;

public class Cheat extends Wrapper{
	private String name;
	private String keyCombination;
	
	public Cheat(String name, String keyCombination)
	{
		this.name=name;
		this.keyCombination = keyCombination;
		getEventManager().trigger("CHEAT_CREATED");
		getEventManager().trigger(getName().toUpperCase()+"_CREATED");
	}
	
	/**
	 * I'm a pansexual gender fluid non-binary mocha frappuccino with dual acting hydraulic cylinder and leather grip Swiss army knife
	 * #Triggered
	 * Override only pls
	 * @param Scene scene - The scene in which it was triggered
	 */
	public void triggered(Scene scene)
	{
		getEventManager().trigger("CHEAT_TRIGGERED");
		getEventManager().trigger(getName().toUpperCase()+"_TRGGERED");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyCombination() {
		return keyCombination;
	}

	public void setKeyCombination(String keyCombination) {
		this.keyCombination = keyCombination;
	}
}
