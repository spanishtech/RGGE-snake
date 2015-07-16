package me.soxey6.game.objects;

import org.newdawn.slick.Color;

import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
/**
 * This is the pellet class, this is the thing you eat to grow. YAY
 * @author spanish
 *
 */
public class Pellet extends GameObject {
	/**
	 * Creates a pellet with the following information
	 * 
	 * @param name The name of the pellet
	 * @param scene The parent scene
	 * @param posX The position x
	 * @param posY The position Y
	 */
	public Pellet(String name, Scene scene,float posX,
			float posY) {
		super(name, scene, Color.cyan, posX, posY, 16, 16);
	}

}
