package me.soxey6.engine.objects.gui;

import me.soxey6.engine.main.Wrapper;
import me.soxey6.engine.managers.event.EventCallback;
import me.soxey6.engine.managers.input.Click;

/**
 * The basic GuiElement class used for creating any UI element
*/
public class GuiElement extends Wrapper implements EventCallback
{
	private String name;
	private Gui gui;
	private float posX;
	private float posY;
	private float sizeX;
	private float sizeY;
	private boolean hovered;
	private boolean clicked;
	
	private Click clickLocation;
	/**
	 * The basic GuiElement class used for creating any UI element
	 * @param name the name of the GuiElement
	 * @param gui the parent Gui of the element
	 * @param posX The position x of the game object
	 * @param posY The position y of the game object
	 * @param sizeX The size x of the game object
	 * @param sizeY The size y of the game object
	 */
	public GuiElement(String name, Gui gui, float posX, float posY, float sizeX, float sizeY)
	{
		this.name=name;
		this.posX=posX;
		this.posY=posY;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.gui=gui;
		this.hovered=false;
		this.clicked=false;
		
		getGui().getGuiElements().add(this);
		getEventManager().trigger("GUIELEMENT_CREATED");
		getEventManager().trigger(getName().toUpperCase()+"_CREATED");
		
		getEventManager().registerHook("MOUSE_MOVE", this);
		getEventManager().registerHook("MOUSE_CLICK", this);
		getEventManager().registerHook("MOUSE_RELEASE", this);

	}
	
	/**
	 * Performs the following logic:
	 * 	Checks to see if the mouse is hovering
	 * 	Checks to see if someone has clicked the GuiElement
	 * 	Checks to see if someone has released a click that started on the button
	 */
	@Override
	public void callback(String eventName)
	{
		if(getGui().getScene().isFocused()){
			if(eventName=="MOUSE_MOVE")
			{
				if((getInputManager().getMouseCurPos().x>=posX&&getInputManager().getMouseCurPos().x<=posX+sizeX&&getInputManager().getMouseCurPos().y>=posY&&getInputManager().getMouseCurPos().y<=posY+sizeY))
				{
					onHover((int)getInputManager().getMouseCurPos().x,(int)getInputManager().getMouseCurPos().y);
					getEventManager().trigger(name+"_HOVER");
					clickLocation = new Click((int)getInputManager().getMouseCurPos().x, (int)getInputManager().getMouseCurPos().y);
					setHovered(true);
				}else{
					setHovered(false);
				}
			}else if(eventName=="MOUSE_CLICK")
			{
				if(isHovered()&&(clickLocation.getX()==getInputManager().getMouseCurPos().x&&clickLocation.getY()==getInputManager().getMouseCurPos().y))
				{
					onClick((int)getInputManager().getMouseCurPos().x,(int)getInputManager().getMouseCurPos().y);
					getLogger().log(0,name+"_CLICK");
					getEventManager().trigger(name+"_CLICK");
					setClicked(true);
				}else
					setHovered(false);
			}else if(eventName=="MOUSE_RELEASE")
			{
				if(isClicked())
					setClicked(false);
			}
		}
		
	}

	/**
	 * The input processes for the  object (For overriding)
	 */
	public void input()
	{
		
	}
	
	/**
	 * The function that is triggered when something needs to be rendered
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
	 * Triggered when the mouse clicks within the button
	 * @param mousePosX the x position of the mouse at the time of the click
	 * @param mousePosY the y position of the mouse at the time of the click
	 * To be overridden only
	 */
	public void onClick(int mousePosX, int mousePosY)
	{
		
	}
	
	/**
	 * Triggered when the mouse hovers within the button
	 * @param mousePosX the x position of the mouse at the time of the hover
	 * @param mousePosY the y position of the mouse at the time of the hover
	 * To be overridden only
	 */
	public void onHover(int x, int y)
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

	/**
	 * @return the hovered
	 */
	public boolean isHovered()
	{
		return hovered;
	}

	/**
	 * @return the clicked
	 */
	public boolean isClicked()
	{
		return clicked;
	}

	/**
	 * @param hovered the hovered to set
	 */
	public void setHovered(boolean hovered)
	{
		this.hovered = hovered;
	}

	/**
	 * @param clicked the clicked to set
	 */
	public void setClicked(boolean clicked)
	{
		this.clicked = clicked;
	}

	
}
