package me.soxey6.engine.main;
import java.awt.Font;
import java.util.ArrayList;

import me.soxey6.engine.managers.SceneManager;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
import me.soxey6.engine.render.Window;
import me.soxey6.game.scenes.MainGameScene;
import me.soxey6.utils.ErrorHandler;
import me.soxey6.utils.FileHandler;
import me.soxey6.utils.Logger;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
/**
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
	private ErrorHandler errorHandler;
	private SceneManager sceneManager;
	private FileHandler fileHandler;
	private Logger logger;
	
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
		this.errorHandler = new ErrorHandler();
		this.logger = new Logger();
		
		this.getLogger().log(this.getLogger().DEBUG, "Creating file manager instance");
		this.fileHandler = new FileHandler();
		
		this.getLogger().log(this.getLogger().DEBUG, "Creating Scene manager instance");
		this.sceneManager=new SceneManager();
		
		// Sets the game name from the constructor
		this.gameName=gameName;
		
		// Creates an instance of the error handler class and sets it for later use.
		
		// Initializes the display and openGL
		initDisplay();
		
		// Engine splash
		if(SHOW_SPLASH)
		splash();
		
		//Creates the objects to be used in the game.
		initGame();
		
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
		long fontTime = System.currentTimeMillis();
		
		this.logger.log(this.logger.DEBUG, "Initializing Fonts for Splash screen");
		
		Font awtFont = new Font("Times New Roman", Font.BOLD, 65);
		TrueTypeFont rgge = new TrueTypeFont(awtFont, true);
		
		awtFont = new Font("Times New Roman", Font.BOLD, 24);
		TrueTypeFont made = new TrueTypeFont(awtFont, true);
		
		this.logger.log(this.logger.INFO, "Time spent loading fonts: "+(System.currentTimeMillis()-fontTime)+"MS");
		
		long startSplash = System.currentTimeMillis();
		this.logger.log(this.logger.DEBUG, "Rendering splash screen for "+this.SPLASH_LENGTH_MS+"MS");
		while(System.currentTimeMillis()<=startSplash+SPLASH_LENGTH_MS)
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			made.drawString(400-rgge.getWidth("RGGE")/2, 300- rgge.getHeight("RGGE")+made.getHeight("Made with")-5, "Made with",Color.white);
			rgge.drawString(400-rgge.getWidth("RGGE")/2, 300- rgge.getHeight("RGGE")/2, "RGGE",Color.white);
			
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
		new MainGameScene("Game");
		this.sceneManager.switchScene("Game");
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
	 * This function when called will update the inputs.
	 * @return Error values
	 */
	public int input()
	{
		for(Scene scene:this.sceneManager.getScenes())
		{
			scene.input();
		}
		return 0;
	}
	
	/**
	 * All logic code should be placed including calling logic() in objects. 
	 * @return Error values
	 */
	public int logic()
	{
		// Check to see if the logic needs to be limited and if so do so.
		if(!GLOBAL_LIMIT_LOGIC||System.currentTimeMillis()>=lastLogicTime+GLOBAL_LOGIC_INCREMENT_MS){
			for(Scene scene:this.sceneManager.getScenes())
			{
				if(!scene.isLimitLogic()||System.currentTimeMillis()>=scene.getLastLogicTime()+scene.getLogicIncrementMS()){
					scene.logic();
					scene.setLastLogicTime(System.currentTimeMillis());
				}
				
			}
			//TODO: logic
			lastLogicTime=System.currentTimeMillis();
			return 0;
		}
		return 0;
		
	}
	
	/**
	 * All rendering code should be placed including calling render() in objects. 
	 * @return Error Values
	 */
	public int render()
	{
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		for(Scene scene:this.sceneManager.getScenes())
		{
			scene.render();
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

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
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
	
	
}
