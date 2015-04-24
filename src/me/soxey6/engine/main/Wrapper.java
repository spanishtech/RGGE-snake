package me.soxey6.engine.main;

import me.soxey6.engine.managers.SceneManager;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.utils.ErrorHandler;
import me.soxey6.utils.Logger;

public class Wrapper {
	private EventManager eventManager;
	private SceneManager sceneManager;
	private ErrorHandler errorHandler;
	private Logger logger;
	private Game game;
	
	public Wrapper()
	{
		this.sceneManager = SceneManager.getSceneManager();
		this.game=Game.getGame();
		this.errorHandler = ErrorHandler.getErrorHandler();
		this.logger = Logger.getLogger();
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

	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
