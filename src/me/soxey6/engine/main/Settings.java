package me.soxey6.engine.main;

import java.util.HashMap;

import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.objects.Setting;
import me.soxey6.utils.Logger;

/**
 * This is where global variables should be stored and altered.
 * @author pchilds
 *
 */
public class Settings{
	// A hashmap of the settings for easy searching and retrieving of them
	private HashMap<String, Setting> settingsList = new HashMap<String, Setting>();
	private static Settings settings;
	
	public Settings()
	{
		settings=this;
	}
	
	/**
	 * Add a setting to the hashmap for easy retrieval 
	 * @param name - The name that the setting will be retrieved with
	 * @param setting - The setting to store
	 */
	public void addSetting(String name,Setting setting)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Adding setting "+name);
		settingsList.put(name, setting);
	}
	
	/**
	 * Add a setting to the hashmap for easy retrieval 
	 * @param setting - The setting to store
	 */
	public void addSetting(Setting setting)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Adding setting "+setting.getName());
		settingsList.put(setting.getName(), setting);
	}
	
	/**
	 * Retrieves a setting by name
	 * @param name - The name that the setting will be retrieved
	 */
	public Setting getSetting(String name)
	{
		return settingsList.get(name);
	}
	
	/**
	 * Change a settings value with a string
	 * @param name - The name of the setting to update
	 * @param value - The value to update the setting with
	 */
	public void updateSetting(String name, String value)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Updating setting "+name+" to "+value);
		EventManager.getEventManager().trigger("SETTING_UPDATE");
		EventManager.getEventManager().trigger(name.toUpperCase()+"_UPDATED");
		settingsList.get(name).setValue(value);
	}
	
	/**
	 * Change a settings value with a string
	 * @param name - The name of the setting to update
	 * @param value - The value to update the setting with
	 */
	public void updateSetting(String name, int value)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Updating setting "+name+" to "+value);
		EventManager.getEventManager().trigger("SETTING_UPDATE");
		EventManager.getEventManager().trigger(name.toUpperCase()+"_UPDATED");
		settingsList.get(name).setValue(value);
	}

	public HashMap<String, Setting> getSettingsList() {
		return settingsList;
	}

	public void setSettingsList(HashMap<String, Setting> settingsList) {
		this.settingsList = settingsList;
	}

	public static Settings getSettings() {
		return settings;
	}

	public static void setSettings(Settings settings) {
		Settings.settings = settings;
	}
	
}
