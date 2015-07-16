package me.soxey6.engine.main;

import me.soxey6.engine.managers.SceneManager;
import me.soxey6.engine.managers.cheat.CheatManager;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.managers.input.InputManager;
import me.soxey6.engine.managers.sound.SoundManager;
import me.soxey6.engine.managers.time.Timer;
import me.soxey6.utils.ErrorHandler;
import me.soxey6.utils.Logger;
import me.soxey6.utils.RenderingUtils;

/**
 * This wrapper is used for calling other classes that are semi-static. Managers and such. This should be extended to most classes in the game as it allows access to them easily.
 * @author pchilds
 *
 */
public class Wrapper{
	// All the classes we need.
	private Game game;
	private EventManager eventManager;
	private SceneManager sceneManager;
	private CheatManager cheatManager;
	private InputManager inputManager;
	private SoundManager soundManager;
	private ErrorHandler errorHandler;
	private RenderingUtils renderingUtils;

	private Timer timer;
	private Logger logger;
	private Settings settings;
	
	public Wrapper()
	{
		// Make sure none of the variables are null when created.
		this.game=Game.getGame();
		this.eventManager = EventManager.getEventManager();
		this.sceneManager = SceneManager.getSceneManager();
		this.cheatManager = CheatManager.getCheatManager();
		this.errorHandler = ErrorHandler.getErrorHandler();
		this.inputManager = InputManager.getInputManager();
		this.soundManager = SoundManager.getSoundManager();
		this.renderingUtils = RenderingUtils.getRenderingUtils();
		this.timer = Timer.getTimer();
		this.logger = Logger.getLogger();
		this.settings = Settings.getSettings();
	}

	
	//TODO: Setup null checks on EACH of these functions
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

	public InputManager getInputManager() {
		return inputManager;
	}


	public void setInputManager(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}


	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
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

	/**
	 * @return the timer
	 */
	public Timer getTimer()
	{
		return timer;
	}


	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer)
	{
		this.timer = timer;
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
