package me.soxey6.game.objects;

import me.soxey6.engine.managers.event.EventCallback;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.game.scenes.MainGameScene;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class SnakeHead extends GameObject implements EventCallback{
	private int direction;
	private float speed;
	private MainGameScene mainGame;
	
	/**
	 * This Class had to be jerry-rigged to work with the new scene engine. Keep that in mind.
	 */
	public SnakeHead(String name, MainGameScene scene, Color colour, float posX, float posY, float sizeX, float sizeY) {
		super(name, scene, colour, posX, posY, sizeX, sizeY);
		getEventManager().registerHook(Keyboard.KEY_W+"_DOWN", this);
		getEventManager().registerHook(Keyboard.KEY_S+"_DOWN", this);
		getEventManager().registerHook(Keyboard.KEY_A+"_DOWN", this);
		getEventManager().registerHook(Keyboard.KEY_D+"_DOWN", this);
		getEventManager().registerHook(Keyboard.KEY_SPACE+"_DOWN",this);

		this.mainGame=scene;
		this.direction=0;
		this.speed=20;
		this.mainGame.getSnake().add(this);
	}
	
	@Override
	public void logic()
	{
		if(this.getDirection()==0)
		{
			this.setPosY(this.getPosY()+this.getSpeed());
		}else if(this.getDirection()==2)
		{
			this.setPosY(this.getPosY()-this.getSpeed());
		}else if(this.getDirection()==1)
		{
			this.setPosX(this.getPosX()+this.getSpeed());
		}
		else if(this.getDirection()==3)
		{
			this.setPosX(this.getPosX()-this.getSpeed());
		}
	}
	

	@Override
	public void callback(String eventName)
	{
		// Controlls
		if(eventName.equalsIgnoreCase(Keyboard.KEY_D+"_DOWN")&&getDirection()!=3)
		{
			setDirection(1);
		}else if(eventName.equalsIgnoreCase(Keyboard.KEY_A+"_DOWN")&&getDirection()!=1)
		{
			setDirection(3);
		}else if(eventName.equalsIgnoreCase(Keyboard.KEY_W+"_DOWN")&&getDirection()!=0)
		{
			setDirection(2);
		}else if(eventName.equalsIgnoreCase(Keyboard.KEY_S+"_DOWN")&&getDirection()!=2)
		{
			setDirection(0);
		}else if(eventName.equalsIgnoreCase(Keyboard.KEY_SPACE+"_DOWN"))
		{
			switch(getDirection())
			{
				case 0:
					new Bullet("0", getScene(), getColour(), getPosX(), getPosY()+getSpeed(), getSizeX(), getSizeY(), getDirection(), getSpeed());
					break;
				case 1:
					new Bullet("0", getScene(), getColour(), getPosX()+getSpeed(), getPosY(), getSizeX(), getSizeY(), getDirection(), getSpeed());
					break;
				case 2:
					new Bullet("0", getScene(), getColour(), getPosX(), getPosY()-getSpeed(), getSizeX(), getSizeY(), getDirection(), getSpeed());
					break;
				case 3:
					new Bullet("0", getScene(), getColour(), getPosX()-getSpeed(), getPosY(), getSizeX(), getSizeY(), getDirection(), getSpeed());
					break;
			}
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
