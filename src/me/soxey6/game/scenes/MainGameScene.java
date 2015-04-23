package me.soxey6.game.scenes;

import me.soxey6.engine.main.Game;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
import me.soxey6.game.objects.SnakeHead;

import org.newdawn.slick.Color;

public class MainGameScene extends Scene{
	private final int STARTING_TAIL_LENGTH = 5;
	public MainGameScene(String name, Game game) {
		super(name, game);
		new SnakeHead("Head", this, new Color(255,255,255), 21, 21, 18, 18);
		for(int i=0; i<=STARTING_TAIL_LENGTH-1; i++)
		{
			new GameObject(Integer.toString(i),  this, new Color(255,255,255), 21, 21, 18, 18){
				public void logic()
				{
					// This moves the tail to the object below it in the array (Next segment.)
					this.setPosX(this.getScene().getGameObjects().get(this.getScene().getGameObjects().indexOf(this)-1).getPosX());
					this.setPosY(this.getScene().getGameObjects().get(this.getScene().getGameObjects().indexOf(this)-1).getPosY());
				}
			};
		}	}
	
	@Override
	public void input()
	{
		for(GameObject gameObject:this.getGameObjects())
		{
			gameObject.input();
		}
	}
	
	@Override
	public void render()
	{
		for(GameObject gameObject:this.getGameObjects())
		{
			gameObject.render();
		}
	}
	
	@Override
	public void logic()
	{
		for(int i=this.getGameObjects().size()-1;i>=0;i--)
		{
			//System.out.println(i);
			getGameObjects().get(i).logic();
		}
	}

}
