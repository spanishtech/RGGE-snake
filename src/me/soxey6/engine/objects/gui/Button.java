package me.soxey6.engine.objects.gui;

import me.soxey6.engine.managers.input.Click;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

/**
 * This is a class for creating buttons
 * @author pchilds
 */
public class Button extends GuiElement{
	private String buttonText;
	private Click click;
	private boolean hover;
	private Color textColor;
	private Color buttonColor;
	public Button(String name, String buttonText, Gui gui, Color textColor, Color buttonColor, float posX, float posY, float sizeX, float sizeY) {
		super(name, gui, posX, posY, sizeX, sizeY);
		this.buttonText=buttonText;
		this.textColor=textColor;
		this.buttonColor=buttonColor;
			
	}
	
	public Button(String name, String buttonText, Gui gui, Color textColor, Color buttonColor, float posX, float posY) {
		super(name, gui, posX, posY, 0, 0);
		this.setSizeX(getRenderingUtils().getWidth(buttonText, 24));
		this.setSizeY(getRenderingUtils().getHeight(buttonText, 24));
		this.buttonText=buttonText;
		this.textColor=textColor;
		this.buttonColor=buttonColor;
			
	}
	
	@Override
	public void input()
	{
		if(!Mouse.isButtonDown(0)&&getGui().isOldInput())
			getGui().setOldInput(false);
		// TODO: Replace "getGui().getScene().getGame().getWindow().getSy()" with a constant.
		// Check if they're clicking within the button keeping in mind that Slick renders text upside-down so we need to flip all Y Calculations
		if(Mouse.getX()>=getPosX()&&Mouse.getX()<=getPosX()+getSizeX()&& getGame().getWindow().getSy()-Mouse.getY()>=getPosY()&&getGame().getWindow().getSy()-Mouse.getY()<=getPosY()+getSizeY())
		{
			this.hover=true;
			onHover(Mouse.getX(), Mouse.getY());
			if(Mouse.isButtonDown(0)&&!getGui().isOldInput())
			{
				getGui().setOldInput(true);
				// register the click
				click = new Click(Mouse.getX(), Mouse.getY());
			}
			
		}else
			hover=false;
	}

	/**
	 * Checks if the current mouse down is new
	 * @return
	 */

	@Override
	public void logic()
	{
		if(click!=null)
		{
			onClick(click.getX(), click.getY());
			click=null;
		}
	}
	
	@Override
	public void render()
	{
		
		getRenderingUtils().drawQuad((int)getPosX(), (int)getPosY(), (int)getPosX()+(int)getSizeX(), (int)getPosY()+(int)getSizeY(), getButtonColor());
		getRenderingUtils().drawStringCentered((int)getPosX()+(int)(getSizeX()/2), (int)getPosY()+(int)(getSizeY()/2), getButtonText(),24, getTextColor());
	}
	
	/**
	 * Triggered when the mouse clicks within the button
	 * @param mousePosX
	 * @param mousePosY
	 * To be overridden only
	 */
	public void onClick(int mousePosX, int mousePosY)
	{
		
	}
	
	/**
	 * Triggered when the mouse hovers within the button
	 * @param mousePosX
	 * @param mousePosY
	 * To be overridden only
	 */
	public void onHover(int x, int y)
	{		
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	/**
	 * @return the hover
	 */
	public boolean isHover()
	{
		return hover;
	}

	/**
	 * @param hover the hover to set
	 */
	public void setHover(boolean hover)
	{
		this.hover = hover;
	}

	/**
	 * @return the textColor
	 */
	public Color getTextColor()
	{
		return textColor;
	}

	/**
	 * @return the buttonColor
	 */
	public Color getButtonColor()
	{
		return buttonColor;
	}

	/**
	 * @param textColor the textColor to set
	 */
	public void setTextColor(Color textColor)
	{
		this.textColor = textColor;
	}

	/**
	 * @param buttonColor the buttonColor to set
	 */
	public void setButtonColor(Color buttonColor)
	{
		this.buttonColor = buttonColor;
	}
}
