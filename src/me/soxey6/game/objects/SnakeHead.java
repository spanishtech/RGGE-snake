package me.soxey6.game.objects;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Color;

import me.soxey6.engine.main.Game;
import me.soxey6.engine.objects.GameObject;

public class SnakeHead extends GameObject {
	private int direction;
	private float speed;
	
	public SnakeHead(String name, Game game, Color colour, float posX, float posY, float sizeX, float sizeY) {
		super(name, game, colour, posX, posY, sizeX, sizeY);
		this.direction=0;
		this.speed=20;
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
		for(GameObject gameObject : this.getGame().getGameObjects())
		{
			if((this.getPosX()==gameObject.getPosX()&&this.getPosY()==gameObject.getPosY())&&!(gameObject instanceof SnakeHead))
			{
				this.getGame().gameOver();
			}
		}
	}
	
	@Override
	public void input()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			this.setDirection(1);
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			this.setDirection(3);
		}else if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			this.setDirection(0);
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			this.setDirection(2);
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
