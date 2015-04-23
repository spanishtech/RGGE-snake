package me.soxey6.engine.objects;

import java.util.ArrayList;

import me.soxey6.engine.main.Game;
@SuppressWarnings("static-access")
/**
 * Scene class:
 * 	This class is used to segment code into different levels or screens.
 * 	This allows things such as menus and multiple levels to be created
 * 	It is recomended to destroy these objects when not in use and recreate them when you need them
 * 	You can unfocus and focus them, when they are unfocused to logic, rendering, or input processing is done.
 * 	NOTICE:
 * 	Place your code here if it DOES NOT need to be executed at ANY time. These scenes are more efficient then running all the code from the main game class.
 * @author spanish
 *
 */
public class Scene {
	private String name;
	private String title;
	private Game game;
	private Gui	gui;
	private boolean focused;
	private EventManager eventManager;
	private ArrayList<GameObject> gameObjects;
	
	public Scene(String name, Game game)
	{
		// Setup variables
		this.name=name;
		this.title=name;
		this.game=game;
		
	}
	
	public void focusChange(boolean focused)
	{
		if(focused)
		// Set window title
		game.getWindow().getDisplay().setTitle(this.game.getGameName()+" - "+this.title);
	}
	
	/**
	 * Process input for this scene here.
	 */
	public void input()
	{
		
	}
	
	public void logic()
	{
		
	}
	
	public void render()
	{
		
	}
	
}
