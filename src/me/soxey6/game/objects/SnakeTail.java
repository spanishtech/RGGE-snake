package me.soxey6.game.objects;

import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;

import org.newdawn.slick.Color;

/**
 * Snake tail follows the head.
 * 
 * @author pchilds
 *
 */
public class SnakeTail extends GameObject{
	private SnakeHead snakeHead;
	/**
	 * Creates a new tail with the following information
	 * @param name The name of the tail
	 * @param scene the Parent scene
	 * @param colour The colour
	 * @param posX The position X
	 * @param posY The position Y
	 * @param sizeX The size X
	 * @param sizeY The size Y
	 * @param snakeHead The parent head
	 */
	public SnakeTail(String name, Scene scene, Color colour, float posX, float posY, float sizeX, float sizeY, SnakeHead snakeHead) {
			super(name, scene, colour, posX, posY, sizeX, sizeY);
			this.snakeHead=snakeHead;
		}
	
	/**
	 * Moves the tail to the tail above it in the array.
	 */
	@Override
	public void logic()	
	{
		getSoundManager().setMusicVolume(getSoundManager().getMusicVolume()-0.01F);
		// This moves the tail to the object below it in the array (Next segment.)
			setPosX(snakeHead.snake.get(snakeHead.snake.indexOf(this)-1).getPosX());
			setPosY(snakeHead.snake.get(snakeHead.snake.indexOf(this)-1).getPosY());
	}

}
