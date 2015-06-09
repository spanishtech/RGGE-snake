package me.soxey6.engine.main;
import java.util.ArrayList;

import me.soxey6.engine.managers.SceneManager;
import me.soxey6.engine.managers.cheat.CheatManager;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.managers.input.InputManager;
import me.soxey6.engine.managers.time.Timer;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Setting;
import me.soxey6.engine.render.Window;
import me.soxey6.game.scenes.MainMenuScene;
import me.soxey6.utils.ErrorHandler;
import me.soxey6.utils.FileHandler;
import me.soxey6.utils.Logger;
import me.soxey6.utils.RenderingUtils;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
/**
 * The game class is the entire game in a single class that can be created with an instance.
 * @author Pat Childs || Soxey6
 * @version Dev-0.0.2
 */
public class Game
{
	private final boolean GLOBAL_LIMIT_LOGIC = false;
	private final long GLOBAL_LOGIC_INCREMENT_MS = 100;
	private final boolean SHOW_SPLASH = true;
	private final int SPLASH_LENGTH_MS = 5000;
	
	private String gameName;
	
	private Window window;
	private static Game game;
	private EventManager eventManager;
	private ErrorHandler errorHandler;
	private SceneManager sceneManager;
	private FileHandler fileHandler;
	private CheatManager cheatManager;
	private Timer timer;
	private Logger logger;
	private RenderingUtils renderingUtils;
	private Settings settings;
	private InputManager inputManager;
	
	private boolean DEBUG = false;
	
	private long lastRenderTime = 0;
	private long lastLogicTime = 0;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	/**
	 * The game object is the whole game itself. Creating a new one will create a new game.
	 * Note: This can have numerous instances at once.
	 * @param String gameName
	 */
	public Game(String gameName)
	{
		game=this;
		
		this.logger = new Logger();
		this.errorHandler = new ErrorHandler();
		this.eventManager = new EventManager();
		getLogger().log(getLogger().INFO, "Respawned");
		
		getLogger().log(getLogger().DEBUG, "Creating file manager instance");
		this.fileHandler = new FileHandler();
		
		getLogger().log(getLogger().DEBUG, "Creating Scene manager instance");
		this.sceneManager=new SceneManager();
		
		getLogger().log(getLogger().DEBUG, "Creating Cheat manager instance");
		this.cheatManager=new CheatManager();
		
		getLogger().log(getLogger().DEBUG, "Creating Settings instance");
		this.settings = new Settings();
		
		getLogger().log(getLogger().DEBUG, "Creating Input Manager Instance");
		this.inputManager = new InputManager();
		
		getLogger().log(getLogger().DEBUG, "Creating Timer Instance");
		this.timer = new Timer();
		
		// Sets the game name from the constructor
		this.gameName=gameName;
		
		// Creates an instance of the error handler class and sets it for later use.
		
		// Initializes the display and openGL
		initDisplay();
		
		// Done after avoid issues
		getLogger().log(getLogger().DEBUG, "Creating Rendering utils instance");
		this.renderingUtils = new RenderingUtils();
		
		// Engine splash
		if(SHOW_SPLASH)
			// Magikarp
			splash();
		
		//Creates the objects to be used in the game.
		initGame();
		initSettings();
		//Starts the game loop
		this.getLogger().log(this.getLogger().DEBUG, "Starting game loop");
		gameLoop();
		this.getLogger().log(this.getLogger().DEBUG, "Game loop left, cleanup started");
		
		// When game loop exits, it cleans up everything.
		cleanUp();
		this.getLogger().log(this.getLogger().INFO, "RIP");
	}

	/**
	 * Cleans up by <b>destroying</b> the window and OpenGL setup
	 * @return Void
	 * @param None
	 */
	private void cleanUp()
	{
		
		getWindow().destroy();
	}

	@SuppressWarnings("static-access")
	/**
	 * All the game logic, rendering and input is processed here.
	 * This will continue to loop until the window is 
	 */
	private int gameLoop()
	{
		// Loops while the window has not attempted to be requested.
		while(!getWindow().getDisplay().isCloseRequested())
		{
			/*
			 * The following functions will break if there is a value returned that doesn't signify fine.
			 */
			// This updates the current input
			if((this.errorHandler.lastError=input())!=0) break;
			
			// This performs game logic
			if((this.errorHandler.lastError=logic())!=0) break;
			
			// This renders and/or changes the current frame.
			if((this.errorHandler.lastError=render())!=0) break;
			
		}
		// This then checks the last error and checks to see if its critical and cannot continue.
		if(this.errorHandler.checkCritError(this.errorHandler.lastError))
		{
			//TODO: Handle the error
			return this.errorHandler.lastError;
		}
		else
			//TODO: Handle the error
			return 0;
	}
	
	/**
	 * This will render the engine splash, because #YOLO
	 */
	@SuppressWarnings("static-access")
	private void splash() {
		long startSplash = System.currentTimeMillis();
		this.logger.log(this.logger.DEBUG, "Rendering splash screen for "+this.SPLASH_LENGTH_MS+"MS");
		while(System.currentTimeMillis()<=startSplash+SPLASH_LENGTH_MS)
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			getRenderingUtils().drawStringCentered(345, 260, "Made with",20 ,Color.white);
			getRenderingUtils().drawStringCentered(400, 300, "RGGE", 65,Color.white);
			this.window.update();
			if(this.getWindow().getDisplay().isCloseRequested())
				System.exit(0);
		}
		
	}
	
	/**
	 * This is where the game initializes (After setting up rendering)
	 * All code that needs to be run at the beginning and only once should be put here.
	 */
	//TODO: Setup error reporting on this function
	private void initGame()
	{
		this.logger.log(this.logger.DEBUG, "Initializing Game");
		new MainMenuScene();
		getSceneManager().switchScene("Main Menu");
	}

	/**
	 * This is where the display is initialized.
	 */
	//TODO: Setup error reporting on this function
	private void initDisplay()
	{
		this.logger.log(this.logger.DEBUG, "Initializing Display");
		// This create a new window called whatever is passed through the constructor of this object.
		window = new Window(this.gameName,800,600);
		
		this.logger.log(this.logger.DEBUG, "Setting up OpenGL instance");
		GL11.glEnable(GL11.GL_TEXTURE_2D);               
		 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
 
        	// enable alpha blending
        	GL11.glEnable(GL11.GL_BLEND);
        	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        	GL11.glViewport(0,0,800,600);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	/**
	 * This function initializes basic settings
	 */
	public void initSettings()
	{
		getLogger().log(getLogger().DEBUG, "Setting up settings");
		
		getSettings().addSetting(new Setting("FRAME_LIMIT", 0));
		getSettings().addSetting(new Setting("RESOLUTION_X", 600));
		getSettings().addSetting(new Setting("RESOLUTION_Y", 800));
	}
	
	/**
	 * This function when called will update the inputs.
	 * @return Error values
	 */
	public int input()
	{
		inputManager.input();
		return 0;
	}
	
	/**
	 * All logic code should be placed including calling logic() in objects. 
	 * @return Error values
	 */
	public int logic()
	{
		getTimer().tick();
		// Check to see if the logic needs to be limited and if so do so.
		/*if(!GLOBAL_LIMIT_LOGIC||System.currentTimeMillis()>=lastLogicTime+GLOBAL_LOGIC_INCREMENT_MS){
			for(int i = 0; i<=getSceneManager().getScenes().size()-1; i++)
			{
				if(getSceneManager().getScenes().get(i).isFocused()&&(!getSceneManager().getScenes().get(i).isLimitLogic()||System.currentTimeMillis()>=getSceneManager().getScenes().get(i).getLastLogicTime()+getSceneManager().getScenes().get(i).getLogicIncrementMS())){
					getSceneManager().getScenes().get(i).logic();
					getSceneManager().getScenes().get(i).setLastLogicTime(System.currentTimeMillis());
				}
				
			}
			//TODO: logic
			lastLogicTime=System.currentTimeMillis();
			return 0;
		}*/
		return 0;
		
	}
	
	/**
	 * All rendering code should be placed including calling render() in objects. 
	 * @return Error Values
	 */
	public int render()
	{
		// Limit the frames based on the settings.
		if(settings.getSetting("FRAME_LIMIT").getValueInt()!=0)
		{
			if(System.currentTimeMillis()<=lastRenderTime+(1000/settings.getSetting("FRAME_LIMIT").getValueInt()))
				return 0;
		}
		
		lastRenderTime=System.currentTimeMillis();
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		for(int i=0; i<=getSceneManager().getScenes().size()-1;i++)
		{
			if(getSceneManager().getScenes().get(i).isFocused())
				getSceneManager().getScenes().get(i).render();
		
		}
		this.window.update();
		//TODO: render
		return 0;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Game.game = game;
	}

	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public FileHandler getFileHandler() {
		return fileHandler;
	}

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}

	public CheatManager getCheatManager() {
		return cheatManager;
	}

	public void setCheatManager(CheatManager cheatManager) {
		this.cheatManager = cheatManager;
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

	public RenderingUtils getRenderingUtils() {
		return renderingUtils;
	}

	public void setRenderingUtils(RenderingUtils renderingUtils) {
		this.renderingUtils = renderingUtils;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public long getLastLogicTime() {
		return lastLogicTime;
	}

	public void setLastLogicTime(long lastLogicTime) {
		this.lastLogicTime = lastLogicTime;
	}

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public boolean isGLOBAL_LIMIT_LOGIC() {
		return GLOBAL_LIMIT_LOGIC;
	}

	public long getGLOBAL_LOGIC_INCREMENT_MS() {
		return GLOBAL_LOGIC_INCREMENT_MS;
	}

	public boolean isSHOW_SPLASH() {
		return SHOW_SPLASH;
	}

	public int getSPLASH_LENGTH_MS() {
		return SPLASH_LENGTH_MS;
	}

	public boolean isDEBUG() {
		return DEBUG;
	}

	public void setDEBUG(boolean dEBUG) {
		DEBUG = dEBUG;
	}

	/**
	 * @return the eventManager
	 */
	public EventManager getEventManager()
	{
		return eventManager;
	}

	/**
	 * @param eventManager the eventManager to set
	 */
	public void setEventManager(EventManager eventManager)
	{
		this.eventManager = eventManager;
	}
	
	
}
