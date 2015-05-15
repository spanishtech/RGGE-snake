package me.soxey6.engine.main;

import me.soxey6.engine.managers.SceneManager;
import me.soxey6.engine.managers.cheat.CheatManager;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.utils.ErrorHandler;
import me.soxey6.utils.Logger;
import me.soxey6.utils.RenderingUtils;

/**
 * This wrapper is used for calling other classes that are semi-static. Managers and such. This should be extended to most classes in the game as it allows access to them easily.
 * @author pchilds
 *
 */
public class Wrapper{
	private Game game;
	private EventManager eventManager;
	private SceneManager sceneManager;
	private CheatManager cheatManager;
	private ErrorHandler errorHandler;
	private RenderingUtils renderingUtils;
	private Logger logger;
	private Settings settings;
	
	public Wrapper()
	{
		this.game=Game.getGame();
		this.eventManager = EventManager.getEventManager();
		this.sceneManager = SceneManager.getSceneManager();
		this.cheatManager = CheatManager.getCheatManager();
		this.errorHandler = ErrorHandler.getErrorHandler();
		this.renderingUtils = RenderingUtils.getRenderingUtils();
		this.logger = Logger.getLogger();
		this.settings = Settings.getSettings();
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

	public CheatManager getCheatManager() {
		return cheatManager;
	}

	public void setCheatManager(CheatManager cheatManager) {
		this.cheatManager = cheatManager;
	}

	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public RenderingUtils getRenderingUtils() {
		return renderingUtils;
	}

	public void setRenderingUtils(RenderingUtils renderingUtils) {
		this.renderingUtils = renderingUtils;
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

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
}
