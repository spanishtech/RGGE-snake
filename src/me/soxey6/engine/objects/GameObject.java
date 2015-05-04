package me.soxey6.engine.objects;

import me.soxey6.engine.main.Wrapper;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
	 * The basic game object, used to create any object rendered in the game
*/
public class GameObject extends Wrapper
{
	private String name;
	private Color colour;
	private float posX;
	private float posY;
	private float sizeX;
	private float sizeY;
	private Scene scene;
	public int direction = 0;
	
	/**
	 * The basic game object, used to create any object rendered in the game
	 * @param name
	 * @param scene
	 * @param colour
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 */
	public GameObject(String name, Scene scene,Color colour, float posX, float posY, float sizeX, float sizeY)
	{
		this.name=name;
		this.colour=colour;
		this.posX=posX;
		this.posY=posY;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.scene=scene;
		this.scene.getGameObjects().add(this);
		this.getLogger().log(this.getLogger().DEBUG, "Created game object: "+name+"\nIn scene: "+this.getScene().getName()+"\n At coords: "+this.getPosX()+", "+this.getPosY() );
	}
	
	/**
	 * This function renders the object, only supports squares currently. This is snake, what where you expecting.
	 */
	public void render()
	{
    	GL11.glDisable(GL11.GL_BLEND);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor4f(colour.getRed(), colour.getGreen(), colour.getBlue(),colour.getAlpha());
		    GL11.glVertex2f(posX,posY);
		    GL11.glVertex2f(posX+sizeX,posY);
		    GL11.glVertex2f(posX+sizeX,posY+sizeY);
		    GL11.glVertex2f(posX,posY+sizeY);
		GL11.glEnd();
    	GL11.glEnable(GL11.GL_BLEND);
	}
	
	/**
	 * The logic functions of the object (For overriding)
	 */
	public void logic()
	{
		
	}
	
	/**
	 * The input processes for the  object (For overriding)
	 */
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
	 * What's my, what's my, what's my scene. Oh, it's this one.<br>
	 * https://www.youtube.com/watch?v=ugA5bLqivkY <- Context
	 * @return the game
	 */
	public Scene getScene()
	{
		return scene;
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
	public void setScene(Scene scene)
	{
		this.scene = scene;
	}
	
}
