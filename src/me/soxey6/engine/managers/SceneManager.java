package me.soxey6.engine.managers;

import java.util.ArrayList;

import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.objects.Scene;
import me.soxey6.utils.Logger;

/**
 * This manager will manage different scenes and allow scene switching and force the logic upon them when needed. 
 * @author pchilds
 *
 */
public class SceneManager{
	private static SceneManager sceneManager;
	private ArrayList<Scene> scenes = new ArrayList<Scene>();
	
	public SceneManager()
	{
		sceneManager=this;
	}
	
	public Scene getScene(String name)
	{
		Scene toReturn = null;
		for(Scene scene : this.getScenes())
		{
			if(scene.getName()==name)
				toReturn=scene;
		}
		return toReturn;
	}
	
	public void switchScene(String name)
	{
		boolean found = false;
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Switching to scene: "+name);
		EventManager.getEventManager().trigger("SCENE_SWITCH");
		for(Scene scene : this.getScenes())
		{
			if(scene.isFocused())
			{
				Logger.getLogger().log(Logger.getLogger().DEBUG, "Unfocusing: "+scene.getName());
				
				EventManager.getEventManager().trigger("SCENE_UNFOCUS");
				EventManager.getEventManager().trigger(scene.getName().toUpperCase()+"_UNFOCUS");
				
				scene.focusChange(false);
				scene.setFocused(false);

			}
			if((scene.getName()==name))
			{
				found = true;
				Logger.getLogger().log(Logger.getLogger().DEBUG, "Focusing: "+scene.getName());
				
				EventManager.getEventManager().trigger("SCENE_FOCUS");
				EventManager.getEventManager().trigger(scene.getName().toUpperCase()+"_FOCUS");
				
				scene.focusChange(true);
				scene.setFocused(true);
			}
		}
		if(!found)
			Logger.getLogger().log(Logger.getLogger().WARNING, "No scene by name: "+name);
			EventManager.getEventManager().trigger("WARNING_SCENE_NOSCENE");
	}
	
	public void addScene(Scene scene)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Adding scene: "+scene.getName());
		EventManager.getEventManager().trigger("SCENE_ADD");
		boolean alreadyHere = false;
		for(Scene curScene:getScenes())
		{
			if(alreadyHere=(curScene.getName()==scene.getName()))
				break;
				
		}
		if(!alreadyHere)
		getScenes().add(scene);

		
	}

	public static SceneManager getSceneManager() {
		return sceneManager;
	}

	public static void setSceneManager(SceneManager sceneManager) {
		SceneManager.sceneManager = sceneManager;
	}

	public ArrayList<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(ArrayList<Scene> scenes) {
		this.scenes = scenes;
	}
}
