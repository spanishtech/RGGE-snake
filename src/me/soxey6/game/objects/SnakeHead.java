package me.soxey6.game.objects;

import java.util.ArrayList;

import me.soxey6.engine.managers.event.EventCallback;
import me.soxey6.engine.managers.sound.SoundManager;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.game.scenes.MainGameScene;
import me.soxey6.utils.LogicUtils;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

/**
 * This is the snake head. A large chunk of the game is processed in here. Movement, collisiosns, etc.
 */
public class SnakeHead extends GameObject implements EventCallback{
	private int direction;
	private float speed;
	public MainGameScene mainGame;
	public ArrayList<GameObject> snake = new ArrayList<GameObject>();
	private int lastDirection=0;
	
	/**
	 * Creates a new snake head with the following data
	 * 
	 * @param name The name of the head
	 * @param scene The parent scene
	 * @param colour The colour
	 * @param posX The position X
	 * @param posY The position Y
	 * @param sizeX The size X
	 * @param sizeY The size Y
	 */
	public SnakeHead(String name, MainGameScene scene, Color colour, float posX, float posY, float sizeX, float sizeY) {
	
		super(name, scene, colour, posX, posY, sizeX, sizeY);
		// Register the hooks (Input hooks for keyboard presses)
		getEventManager().registerHook(Keyboard.KEY_W+"_DOWN", this);
		getEventManager().registerHook(Keyboard.KEY_S+"_DOWN", this);
		getEventManager().registerHook(Keyboard.KEY_A+"_DOWN", this);
		getEventManager().registerHook(Keyboard.KEY_D+"_DOWN", this);
		getEventManager().registerHook(Keyboard.KEY_SPACE+"_DOWN",this);

		this.mainGame=scene;
		//Init variables
		this.direction=0;
		this.speed=20;
		this.snake.add(this);
		this.mainGame = (MainGameScene) getScene();
		SoundManager.getSoundManager().newSoundEffect("collect.wav");
		SoundManager.getSoundManager().newSoundEffect("death.wav");
	}
	
	/**
	 * Does all logic functions, movement, detection of objects, dying, shooting, etc.
	 */
	@Override
	public void logic()
	{
		// This bulk of ugly looking logic simply allows it to loop around the screen. Nothing special here. Just long lines and some basic maths
		if(LogicUtils.calculateDirectionOffset(getPosX(), getPosY(), getSpeed(), getDirection()).x<0)
			setPosX(LogicUtils.calculateDirectionOffset(getPosX(), getPosY(), getSpeed(), getDirection()).x+800);
		else
			setPosX(LogicUtils.calculateDirectionOffset(getPosX(), getPosY(), getSpeed(), getDirection()).x%800);
		if(LogicUtils.calculateDirectionOffset(getPosX(), getPosY(), getSpeed(), getDirection()).y<0)
			setPosY(LogicUtils.calculateDirectionOffset(getPosX(), getPosY(), getSpeed(), getDirection()).y+600);
		else
			setPosY(LogicUtils.calculateDirectionOffset(getPosX(), getPosY(), getSpeed(), getDirection()).y%600);
		
		// Update it's last direction (This is needed to stop a glitch with changing inputs twice in a tick allowing you to turn back on yourself)
		lastDirection=getDirection();
		
		// Check for collisions
		for(int i=0; i<=getScene().getGameObjects().size()-1; i++)
		{
			// If it's collided AND the object is a pellet
			if(getScene().getGameObjects().get(i) instanceof Pellet&&getScene().getGameObjects().get(i).getPosX()-1==getPosX()&&getScene().getGameObjects().get(i).getPosY()-1==getPosY())
			{
				getSoundManager().playSoundEffect("collect.wav");
				// Add a new snake tail to our snake
				snake.add(new SnakeTail(String.valueOf(snake.size()-1), getScene(), new Color(0, 255, 0), -100, -100, 18, 18, this));
				
				try {
					// Try and kill the pellet
					getScene().getGameObjects().get(i).finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				// Increase the score
				mainGame.score();
			}
			else if(getScene().getGameObjects().get(i).getPosX()==getPosX()&&getScene().getGameObjects().get(i).getPosY()==getPosY()&&!(getScene().getGameObjects().get(i) instanceof SnakeHead))
			{
				getSoundManager().getMusic("main_theme.aiff").stop();
				getSoundManager().playSoundEffect("death.wav");
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Otherwise you've hit a obsticle and you're dead
				mainGame.gameOver();
			}
		}
	}
	

	@Override
	public void callback(String eventName)
	{

		// Change the direction based on keypress and make sure you can't turn back on yourself directly
		if(eventName.equalsIgnoreCase(Keyboard.KEY_D+"_DOWN")&&lastDirection!=3)
		{
			setDirection(1);
		}else if(eventName.equalsIgnoreCase(Keyboard.KEY_A+"_DOWN")&&lastDirection!=1)
		{
			setDirection(3);
		}else if(eventName.equalsIgnoreCase(Keyboard.KEY_W+"_DOWN")&&lastDirection!=0)
		{
			setDirection(2);
		}else if(eventName.equalsIgnoreCase(Keyboard.KEY_S+"_DOWN")&&lastDirection!=2)
		{
			setDirection(0);
		}
		
		// If they press space (Gun fire)
		else if(eventName.equalsIgnoreCase(Keyboard.KEY_SPACE+"_DOWN")&&snake.size()>0)
		{
			// Kill it and remove it from the array
			try {
				snake.get(snake.size()-1).finalize();
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
			snake.remove(snake.size()-1);
			
			// Creates new bullets in the direction
			switch(getDirection())
			{
				case 0:
					new Bullet("Bullet", getScene(), getColour(), getPosX(), getPosY()+getSpeed()*2, getSizeX(), getSizeY(), getDirection(), getSpeed());
					break;
				case 1:
					new Bullet("Bullet", getScene(), getColour(), getPosX()+getSpeed()*2, getPosY(), getSizeX(), getSizeY(), getDirection(), getSpeed());
					break;
				case 2:
					new Bullet("Bullet", getScene(), getColour(), getPosX(), getPosY()-getSpeed()*2, getSizeX(), getSizeY(), getDirection(), getSpeed());
					break;
				case 3:
					new Bullet("Bullet", getScene(), getColour(), getPosX()-getSpeed()*2, getPosY(), getSizeX(), getSizeY(), getDirection(), getSpeed());
					break;
			}
			// Decrease the score
			mainGame.score();
		}
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}


}
