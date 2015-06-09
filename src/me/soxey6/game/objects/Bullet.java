package me.soxey6.game.objects;

import me.soxey6.engine.managers.event.EventCallback;
import me.soxey6.engine.managers.time.Time;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;

import org.newdawn.slick.Color;

public class Bullet extends GameObject implements EventCallback
{
	private int direction;
	private float speed;
	
	public Bullet(String name, Scene scene, Color colour, float posX,
			float posY, float sizeX, float sizeY, int direction, float speed)
	{
		super(name, scene, colour, posX, posY, sizeX, sizeY);
		this.direction=direction;
		this.speed = speed;
		getTimer().newTimer(new Time(50), this);
	}

	@Override
	public void callback(String eventName)
	{
		if(eventName.equalsIgnoreCase("TIMER_50"))
		{
			if(direction==0)
				setPosY(getPosY()+getSpeed());
			else if(direction==1)
				setPosX(getPosX()+getSpeed());
			else if(direction==2)
				setPosY(getPosY()-getSpeed());
			else if(direction==3)
				setPosX(getPosX()-getSpeed());
		}
	}	
	
	/**
	 * @return the direction
	 */
	public int getDirection()
	{
		return direction;
	}

	/**
	 * @return the speed
	 */
	public float getSpeed()
	{
		return speed;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

}
