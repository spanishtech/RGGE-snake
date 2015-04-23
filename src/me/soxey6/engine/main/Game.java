package me.soxey6.engine.main;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.render.Window;
import me.soxey6.game.objects.SnakeHead;
import me.soxey6.utils.ErrorHandling;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
/**
 * @author Pat Childs || Soxey6
 * @version Dev-0.0.2
 */
public class Game
{
	private final boolean LIMIT_LOGIC = true;
	private final long LOGIC_INCREMENT_MS = 100;
	private final int STARTING_TAIL_LENGTH = 100;
	private final boolean SHOW_SPLASH = true;
	private final int SPLASH_LENGTH_MS = 3000;
	
	
	private String gameName;
	private Window window;
	private ErrorHandling errorHandler;
	private long lastLogicTime = 0;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	/**
	 * The game object is the whole game itself. Creating a new one will create a new game.
	 * Note: This can have numerous instances at once.
	 * @param String gameName
	 */
	public Game(String gameName)
	{
		// Sets the game name from the constructor
		this.gameName=gameName;
		
		// Creates an instance of the error handler class and sets it for later use.
		this.errorHandler = new ErrorHandling();
		
		// Initializes the display and openGL
		initDisplay();
		
		// Engine splash
		if(SHOW_SPLASH)
		splash();
		
		//Creates the objects to be used in the game.
		initGame();
		
		//Starts the game loop
		gameLoop();
		
		// When game loop exits, it cleans up everything.
		cleanUp();
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
		
		Font awtFont = new Font("Times New Roman", Font.BOLD, 65);
		TrueTypeFont rgge = new TrueTypeFont(awtFont, true);
		
		 awtFont = new Font("Times New Roman", Font.BOLD, 24);
		TrueTypeFont made = new TrueTypeFont(awtFont, true);
		
		while(System.currentTimeMillis()<=startSplash+SPLASH_LENGTH_MS)
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			made.drawString(400-rgge.getWidth("RGGE")/2, 300- rgge.getHeight("RGGE")+made.getHeight("Made with")-5, "Made with",Color.white);
			rgge.drawString(400-rgge.getWidth("RGGE")/2, 300- rgge.getHeight("RGGE")/2, "RGGE",Color.white);
			
			this.getWindow().getDisplay().update();
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
		new SnakeHead("Head", this, new Color(255,255,255), 21, 21, 18, 18);
		for(int i=0; i<=STARTING_TAIL_LENGTH-1; i++)
		{
			new GameObject(Integer.toString(i), this, new Color(255,255,255), 21, 21, 18, 18){
				public void logic()
				{
					// This moves the tail to the object below it in the array (Next segment.)
					this.setPosX(this.getGame().getGameObjects().get(this.getGame().getGameObjects().indexOf(this)-1).getPosX());
					this.setPosY(this.getGame().getGameObjects().get(this.getGame().getGameObjects().indexOf(this)-1).getPosY());
				}
			};
		}
	}

	/**
	 * This is where the display is initialized.
	 */
	//TODO: Setup error reporting on this function
	private void initDisplay()
	{
		// This create a new window called whatever is passed through the constructor of this object.
		window = new Window(this.gameName,800,600);
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
		for(GameObject gameObject:this.gameObjects)
		{
			gameObject.input();
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
		if(!LIMIT_LOGIC||System.currentTimeMillis()>=lastLogicTime+LOGIC_INCREMENT_MS){
			for(int i=this.gameObjects.size()-1;i>=0;i--)
			{
				gameObjects.get(i).logic();
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
		for(GameObject gameObject:gameObjects)
		{
			gameObject.render();
		}
		this.window.update();
		//TODO: render
		return 0;
	}
	
	/**
	 * @return the gameName
	 */
	public String getGameName() {
		return gameName;
	}
	
	/**
	 * @return the window
	 */
	public Window getWindow() {
		return window;
	}
	
	/**
	 * @return the gameObjects
	 */
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	/**
	 * @param gameName the gameName to set
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	/**
	 * @param window the window to set
	 */
	public void setWindow(Window window) {
		this.window = window;
	}
	/**
	 * @param gameObjects the gameObjects to set
	 */
	
	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}
	public void gameOver() {
        JOptionPane.showMessageDialog(null, "Game Over, Stop hitting yourself.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	
}
