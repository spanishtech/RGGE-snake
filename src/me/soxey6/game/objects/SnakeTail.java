package me.soxey6.game.objects;

import me.soxey6.engine.objects.GameObject;
import me.soxey6.game.scenes.MainGameScene;

import org.newdawn.slick.Color;

public class SnakeTail extends GameObject{
	private MainGameScene mainGame;
	
	/**
	 * This Class had to be jerry-rigged to work with the new scene engine. Keep that in mind.
	 */
	public SnakeTail(String name, MainGameScene scene, Color colour, float posX, float posY, float sizeX, float sizeY) {
			super(name, scene, colour, posX, posY, sizeX, sizeY);
			this.mainGame=scene;
			this.mainGame.getSnake().add(this);
		}
	
	@Override
	public void logic()
	{
		// This moves the tail to the object below it in the array (Next segment.)
		this.setPosX(this.mainGame.getSnake().get(this.getScene().getGameObjects().indexOf(this)-1).getPosX());
		this.setPosY(this.mainGame.getSnake().get(this.getScene().getGameObjects().indexOf(this)-1).getPosY());
	}

}
