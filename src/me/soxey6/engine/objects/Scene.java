package me.soxey6.engine.objects;

import java.util.ArrayList;

import me.soxey6.engine.main.Game;
import me.soxey6.engine.main.Wrapper;
import me.soxey6.engine.managers.SceneManager;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.objects.gui.Gui;
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
public class Scene extends Wrapper{
	private final boolean LIMIT_LOGIC = true;
	private final long LOGIC_INCREMENT_MS = 100;
	
	private String name;
	private String title;
	private boolean focused;

	private Game game;
	private Gui	gui;

	private long lastLogicTime;
	private ArrayList<GameObject> gameObjects;
	
	public Scene(String name)
	{
		// Setup variables
		this.name=name;
		this.title=name;
		this.game=Game.getGame();
		
		this.gameObjects=new ArrayList<GameObject>();
		
		this.gui = new Gui(this);
		this.getSceneManager().addScene(this);
		
		// Log the creation
		this.getLogger().log(this.getLogger().DEBUG, "Creating Scene: "+this.getName());
		getEventManager().trigger(getName().toUpperCase()+"_CREATED");

	}
	
	public void focusChange(boolean focused)
	{
		getEventManager().trigger(getName().toUpperCase()+"_FOCUS_CHANGE");
		if(focused)
		// Set window title
		game.getWindow().getDisplay().setTitle(this.game.getGameName()+" - "+this.title);
		getGui().setOldInput(true);
	}
	
	/**
	 * Process input for this scene here.
	 */
	public void input()
	{
		if(focused)
			return;
		else
			return;
	}
	
	/**
	 * Process logic for this scene here.
	 */
	public void logic()
	{
		if(focused)
			return;
		else
			return;	
	}
	
	/**
	 * Process rendering for this scene here.
	 */
	public void render()
	{
		if(focused)
			return;
		else
			return;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isFocused() {
		return focused;
	}

	public void setFocused(boolean focused) {
		this.focused = focused;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
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

	public boolean isLimitLogic() {
		return LIMIT_LOGIC;
	}

	public long getLogicIncrementMS() {
		return LOGIC_INCREMENT_MS;
	}

}

	