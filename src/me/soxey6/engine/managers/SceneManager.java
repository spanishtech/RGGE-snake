package me.soxey6.engine.managers;

import java.util.ArrayList;

import me.soxey6.engine.objects.Scene;

/**
 * This manager will manage different scenes and allow scene switching and force the logic upon them when needed. 
 * @author pchilds
 *
 */
public class SceneManager {
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
		for(Scene scene : this.getScenes())
		{
			if(scene.isFocused())
			{
				scene.focusChange(scene.getName()==name);
			}
			if(scene.getName()==name)
			{
				scene.focusChange(scene.getName()==name);
				scene.setFocused(scene.getName()==name);
			}
		}
	}
	
	public void addScene(Scene scene)
	{
		this.getScenes().add(scene);
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
