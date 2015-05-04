package me.soxey6.engine.objects;

import me.soxey6.engine.main.Wrapper;

public class Cheat extends Wrapper{
	private String name;
	private String keyCombination;
	
	public Cheat(String name, String keyCombination)
	{
		this.name=name;
		this.keyCombination = keyCombination;
	}
	
	/**
	 * I'm a pansexual gender fluid non-binary mocha frappuccino with dual acting hydraulic cynlinder and leather grip swiss army knife
	 * #Triggered
	 * Override only pls
	 * @param scene
	 */
	public void triggered(Scene scene)
	{
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
