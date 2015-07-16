package me.soxey6.utils;

import org.lwjgl.util.vector.Vector2f;
/**
 * A static class that provides basic functions that help speed up programming
 * @author pchilds
 *
 */
public class LogicUtils {
	/**
	 * Generates the new position based on an offset and the direction to offset by
	 * @param x The original position x
	 * @param y The original position y
	 * @param offset The amount to offset the x and y by
	 * @param direction The direction that will decide what is offset and by how much
	 * @return the new location
	 */
	public static Vector2f calculateDirectionOffset(int x, int y, int offset, int direction)
	{
		Vector2f toReturn=new Vector2f(0,0);
		if(direction==0)
		{
			toReturn = new Vector2f(x, y+offset);
		}else if(direction==2)
		{
			toReturn = new Vector2f(x, y-offset);
		}else if(direction==1)
		{
			toReturn = new Vector2f(x+offset, y);
		}
		else if(direction==3)
		{
			toReturn = new Vector2f(x-offset, y);
		}
		return toReturn;
	}
	
	/**
	 * Generates the new position based on an offset and the direction to offset by
	 * @param x The original position x
	 * @param y The original position y
	 * @param offset The amount to offset the x and y by
	 * @param direction The direction that will decide what is offset and by how much
	 * @return the new location
	 */
	public static Vector2f calculateDirectionOffset(float x, float y,
			float offset, int direction)
	{
		Vector2f toReturn=new Vector2f(0,0);
		if(direction==0)
		{
			toReturn = new Vector2f(x, y+offset);
		}else if(direction==2)
		{
			toReturn = new Vector2f(x, y-offset);
		}else if(direction==1)
		{
			toReturn = new Vector2f(x+offset, y);
		}
		else if(direction==3)
		{
			toReturn = new Vector2f(x-offset, y);
		}
		return toReturn;		
	}
}
