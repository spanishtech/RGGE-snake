package me.soxey6.engine.objects;

import me.soxey6.engine.main.Wrapper;

/**
 * The basic GuiElement class used for creating any UI element
*/
public class GuiElement extends Wrapper
{
	private String name;
	private Gui gui;
	private float posX;
	private float posY;
	private float sizeX;
	private float sizeY;	
	/**
	 * The basic GuiElement class used for creating any UI element
	 * @param name
	 * @param gui 
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 */
	public GuiElement(String name, Gui gui, float posX, float posY, float sizeX, float sizeY)
	{
		this.name=name;
		this.posX=posX;
		this.posY=posY;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.gui=gui;
		
		getGui().getGuiElements().add(this);
		this.getLogger().log(this.getLogger().DEBUG, "Created game object: "+name+"\nIn scene: "+this.getGui().getScene().getName()+"\n At coords: "+this.getPosX()+", "+this.getPosY() );
	}
	

	/**
	 * The input processes for the  object (For overriding)
	 */
	public void input()
	{
		
	}
	
	/**
	 * Override only pls
	 */
	public void render()
	{
   
	}
	
	/**
	 * The logic functions of the object (For overriding)
	 */
	public void logic()
	{
		
	}
	
	
	/**
	 * @return the gui
	 */
	public Gui getGui()
	{
		return gui;
	}

	/**
	 * @param gui the gui to set
	 */
	public void setGui(Gui gui)
	{
		this.gui = gui;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
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
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
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
	
}
