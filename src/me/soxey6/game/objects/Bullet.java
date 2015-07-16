package me.soxey6.game.objects;

import me.soxey6.engine.managers.event.EventCallback;
import me.soxey6.engine.managers.time.Time;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
import me.soxey6.game.scenes.MainGameScene;
import me.soxey6.utils.LogicUtils;

import org.newdawn.slick.Color;

/**
 * This is the bullet the player shoots, it contains logic for moving itself, checking for collisions with blocks, and destroying blocks.
 * 
 * @author pchilds
 *
 */
public class Bullet extends GameObject implements EventCallback
{
	private int direction;
	private float speed;
	private MainGameScene mainGame;

	/**
	 * Creates a bullet with the specified information and it will continue accelerating in that direction.
	 * 
	 * @param name The name of the bullet
	 * @param scene The parent scene
	 * @param colour The Colour of the bullet
	 * @param posX The position x
	 * @param posY The position Y
	 * @param sizeX The size X
	 * @param sizeY The size Y
	 * @param direction The direction in which it will continue traveling (Responding to cardinal directions where it starts at 0 for north and increases by one clock-wise)
	 * @param speed The speed of the bullet
	 */
	public Bullet(String name, Scene scene, Color colour, float posX,
			float posY, float sizeX, float sizeY, int direction, float speed)
	{
		super(name, scene, colour, posX, posY, sizeX, sizeY);
		this.direction=direction;
		this.speed = speed;
		this.mainGame=(MainGameScene) getScene();
		getTimer().newTimer(new Time(50), this);
	}

	@Override
	public void callback(String eventName)
	{

		// Make sure the right event is calling
		if(eventName.equalsIgnoreCase("TIMER_50"))
		{
			// Check if it's out of the screen
			if(getPosX()>800||getPosY()>600||getPosX()<0||getPosY()<0)
			{
				try {
					
					// Remove references from memory
					finalize();
					
					// Remove hooks to stop any issues
					getEventManager().removeHooks(this);
					
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
				// Update the position based on the direction it's heading
				setPosX(LogicUtils.calculateDirectionOffset(getPosX(), getPosY(), getSpeed(), direction).x);
				setPosY(LogicUtils.calculateDirectionOffset(getPosX(), getPosY(), getSpeed(), direction).y);
		}
		
		// Iterate through all the walls
		for(int i = 0; i<=mainGame.walls.size()-1;i++)
		{
				// Check if there's a collision
				if(getPosY()==mainGame.walls.get(i).getPosY()&&getPosX()==mainGame.walls.get(i).getPosX()) try
				{
					// Remove this bullet from memory and it's hooks
					finalize();
					getEventManager().removeHooks(this);
					
					// Remove the wall from memory
					mainGame.walls.get(i).finalize();
					
					// Remove it from the walls array
					mainGame.walls.remove(mainGame.walls.get(i));
				}
				catch (Throwable e)
				{
					e.printStackTrace();
				}
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
