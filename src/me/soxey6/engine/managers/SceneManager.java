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
	
	/**
	 * Returns a scene by the name
	 * @param String name - The name of the Scene
	 * @return Scene - The scene with the matching name (Null if none.)
	 */
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
	
	/**
	 * Switches a scene by changing focus
	 * @param String sceneName - The name of the same to change to.
	 */
	public void switchScene(String sceneName)
	{
		// Set the found boolean to flase
		boolean found = false;
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Switching to scene: "+sceneName);
		// Trigger the event
		EventManager.getEventManager().trigger("SCENE_SWITCH");
		// Iterate through each scene
		for(Scene scene : this.getScenes())
		{
			// Check if the scene is focused
			if(scene.isFocused())
			{
				Logger.getLogger().log(Logger.getLogger().DEBUG, "Unfocusing: "+scene.getName());
				
				EventManager.getEventManager().trigger("SCENE_UNFOCUS");
				EventManager.getEventManager().trigger(scene.getName().toUpperCase()+"_UNFOCUS");
				
				scene.focusChange(false);
				scene.setFocused(false);

			}
			if((scene.getName()==sceneName))
			{
				found = true;
				Logger.getLogger().log(Logger.getLogger().DEBUG, "Focusing: "+scene.getName());
				
				EventManager.getEventManager().trigger("SCENE_FOCUS");
				EventManager.getEventManager().trigger(scene.getName().toUpperCase()+"_FOCUS");
				
				scene.focusChange(true);
				scene.setFocused(true);
			}
		}
		// If the scene is not there
		if(!found)
		{
			// Catch event
			Logger.getLogger().log(Logger.getLogger().WARNING, "No scene by name: "+sceneName);
			EventManager.getEventManager().trigger("WARNING_SCENE_NOSCENE");
		}
			
	}
	
	/**
	 * Adds a scene to the array if it has not already been added.
	 * @param Scene scene - The scene to add
	 */
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
	
	public void removeScene(Scene scene)
	{
		getScenes().remove(scene);
	}
	
	public void removeScene(String sceneName)
	{
		for(int i=0; i<=getScenes().size()-1; i++)
			if(getScenes().get(i).getName().equalsIgnoreCase(sceneName))
				getScenes().remove(i);
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
