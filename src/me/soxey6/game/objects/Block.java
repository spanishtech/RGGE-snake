package me.soxey6.game.objects;

import org.newdawn.slick.Color;

import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
/**
 * A block that is used to help detect a player from hitting it
 * @author spanish
 *
 */
public class Block extends GameObject
{

	/**
	 * Creates a block with the information provided
	 * 
	 * @param name The name
	 * @param scene The parent scene
	 * @param colour The color of the block
	 * @param posX The position X
	 * @param posY The position Y
	 */
	public Block(String name, Scene scene, Color colour, float posX,
			float posY)
	{
		super(name, scene, colour, posX, posY, 18, 18);
	}

}
