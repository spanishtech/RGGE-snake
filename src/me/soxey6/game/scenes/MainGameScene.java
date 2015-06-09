package me.soxey6.game.scenes;

import java.util.ArrayList;

import me.soxey6.engine.managers.event.EventCallback;
import me.soxey6.engine.managers.time.Time;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
import me.soxey6.game.objects.SnakeHead;
import me.soxey6.game.objects.SnakeTail;

import org.newdawn.slick.Color;

public class MainGameScene extends Scene implements EventCallback{
	private final int STARTING_TAIL_LENGTH = 100;
	private ArrayList<GameObject> snake = new ArrayList<GameObject>();
	
	public MainGameScene(String name) {
		super(name);
		getTimer().newTimer(new Time(100), this);
		new SnakeHead("Head", this, new Color(0, 255, 0), 21, 21, 18, 18);
		for(int i=0; i<=STARTING_TAIL_LENGTH-1; i++)
		{
			new SnakeTail(Integer.toString(i),  this, new Color(0, 255, 0), 21, 21, 18, 18);
		}
	}
	
	@Override
	public void render()
	{
		for(GameObject gameObject:this.getGameObjects())
		{
			gameObject.render();
		}
		this.getGui().render();
	}
	
	@Override
	public void logic()
	{
		getCheatManager().processCheat(this);
		for(int i=this.getGameObjects().size()-1;i>=0;i--)
		{
			getGameObjects().get(i).logic();
		}
		getGui().logic();
	}

	public ArrayList<GameObject> getSnake() {
		return snake;
	}

	public void setSnake(ArrayList<GameObject> snake) {
		this.snake = snake;
	}

	@Override
	public void callback(String eventName)
	{
		if(eventName.equalsIgnoreCase("TIMER_100"))
			logic();
	}

}
