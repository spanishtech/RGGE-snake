package me.soxey6.engine.objects;

import me.soxey6.engine.main.Game;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;


public class GameObject
{
	private String name;
	private Color colour;
	private float posX;
	private float posY;
	private float sizeX;
	private float sizeY;
	private Game game;
	public int direction = 0;
	
	public GameObject(String name, Game game,Color colour, float posX, float posY, float sizeX, float sizeY)
	{
		this.name=name;
		this.colour=colour;
		this.posX=posX;
		this.posY=posY;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.game=game;
		game.getGameObjects().add(this);
	}
	
	/**
	 * This function renders the object, only supports squares, this is snake, what where you expecting.
	 */
	public void render()
	{
		GL11.glColor3f(colour.getRed(), colour.getGreen(), colour.getBlue());
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(posX,posY);
		    GL11.glVertex2f(posX+sizeX,posY);
		    GL11.glVertex2f(posX+sizeX,posY+sizeY);
		    GL11.glVertex2f(posX,posY+sizeY);
		GL11.glEnd();
	}
	
	/**
	 * The logic functions of the object (For overriding)
	 */
	public void logic()
	{
		
	}
	
	public void input()
	{
		
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the colour
	 */
	public Color getColour()
	{
		return colour;
	}

	/**
	 * @return the posX
	 */
	public float getPosX()
	{
		return posX;
	}

	/**
	 * @return the posY
	 */
	public float getPosY()
	{
		return posY;
	}

	/**
	 * @return the sizeX
	 */
	public float getSizeX()
	{
		return sizeX;
	}

	/**
	 * @return the sizeY
	 */
	public float getSizeY()
	{
		return sizeY;
	}

	/**
	 * @return the game
	 */
	public Game getGame()
	{
		return game;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(Color colour)
	{
		this.colour = colour;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(float posX)
	{
		this.posX = posX;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(float posY)
	{
		this.posY = posY;
	}

	/**
	 * @param sizeX the sizeX to set
	 */
	public void setSizeX(float sizeX)
	{
		this.sizeX = sizeX;
	}

	/**
	 * @param sizeY the sizeY to set
	 */
	public void setSizeY(float sizeY)
	{
		this.sizeY = sizeY;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game)
	{
		this.game = game;
	}
	
}
