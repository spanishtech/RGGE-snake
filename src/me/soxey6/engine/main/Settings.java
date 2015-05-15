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
	private HashMap<String, Setting> settingsList = new HashMap<String, Setting>();
	private static Settings settings;
	
	public Settings()
	{
		settings=this;
	}
	
	public void addSetting(String name,Setting setting)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Adding setting "+name);
		settingsList.put(name, setting);
	}
	
	public void addSetting(Setting setting)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Adding setting "+setting.getName());
		settingsList.put(setting.getName(), setting);
	}
	
	public Setting getSetting(String name)
	{
		return settingsList.get(name);
	}
	
	public Setting getSetting(Setting setting)
	{
		return settingsList.get(setting.getName());
	}
	
	
	public void updateSetting(String name, String value)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Updating setting "+name+" to "+value);
		EventManager.getEventManager().trigger("SETTING_UPDATE");
		EventManager.getEventManager().trigger(name.toUpperCase()+"_UPDATED");
		settingsList.get(name).setValue(value);
	}

	
	public void updateSetting(Setting setting, String value)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Updating setting "+setting.getName()+" to "+value);
		EventManager.getEventManager().trigger("SETTING_UPDATE");
		EventManager.getEventManager().trigger(setting.getName().toUpperCase()+"_UPDATED");
		settingsList.get(setting.getName()).setValue(value);
	}
	
	public void updateSetting(String name, int value)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Updating setting "+name+" to "+value);
		EventManager.getEventManager().trigger("SETTING_UPDATE");
		EventManager.getEventManager().trigger(name.toUpperCase()+"_UPDATED");
		settingsList.get(name).setValue(value);
	}

	
	public void updateSetting(Setting setting, int value)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Updating setting "+setting.getName()+" to "+value);
		EventManager.getEventManager().trigger("SETTING_UPDATE");
		EventManager.getEventManager().trigger(setting.getName().toUpperCase()+"_UPDATED");
		settingsList.get(setting.getName()).setValue(value);
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
