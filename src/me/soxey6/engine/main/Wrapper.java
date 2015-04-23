package me.soxey6.engine.main;

import me.soxey6.engine.managers.EventManager;
import me.soxey6.engine.managers.SceneManager;

public class Wrapper {
	private EventManager eventManager;
	private SceneManager sceneManager;
	private Game game;
	
	public Wrapper()
	{
		this.sceneManager = SceneManager.getSceneManager();
		this.game=Game.getGame();
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
