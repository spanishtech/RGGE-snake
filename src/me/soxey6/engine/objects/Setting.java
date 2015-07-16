package me.soxey6.engine.objects;

import me.soxey6.engine.main.Wrapper;

/**
 * This object is used for storing settings or data. The data is kept as a string so keep that in mind.
 * @author pchilds
 */
public class Setting extends Wrapper{

	private String name;
	private String value;
	private boolean enabled;
	
	/**
	 * Creates a new setting with a string value
	 * 
	 * @param name The name of the setting
	 * @param value The value to be used
	 */
	public Setting(String name, String value)
	{
		this.name=name;
		this.value=value;
		this.enabled=true;
	}
	
	/**
	 * Creates a new setting with an int value
	 * 
	 * @param name The name of the settings
	 * @param value The value to be used
	 */
	public Setting(String name, int value)
	{
		this.name=name;
		this.value=String.valueOf(value);
		this.enabled=true;
	}

	/**
	 * Updates the setting with the value given
	 * 
	 * @param value The value to update 
	 */
	public void update(String value)
	{
		this.value=value;
	}
	
	/**
	 * Toggles the state of the setting
	 */
	public void toggle()
	{
		enabled = !enabled;
	}
	
	/**
	 * Enables the setting
	 */
	public void enable()
	{
		enabled = true;
	}
	
	/**
	 * Disables the setting
	 */
	public void disable()
	{
		enabled = false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValueInt() {
		return Integer.valueOf(value);
	}

	public void setValue(int value) {
		this.value = String.valueOf(value);
	}

	public String getValueStr() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
