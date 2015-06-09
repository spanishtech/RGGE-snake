package me.soxey6.engine.objects.gui;

import org.newdawn.slick.Color;

/**
 * This is a class for creating buttons
 * @author pchilds
 */
public class Button extends GuiElement{
	private String buttonText;
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
	}

	/**
	 * Checks if the current mouse down is new
	 * @return
	 */

	@Override
	public void logic()
	{
	}
	
	@Override
	public void render()
	{
		
		getRenderingUtils().drawQuad((int)getPosX(), (int)getPosY(), (int)getPosX()+(int)getSizeX(), (int)getPosY()+(int)getSizeY(), getButtonColor());
		getRenderingUtils().drawStringCentered((int)getPosX()+(int)(getSizeX()/2), (int)getPosY()+(int)(getSizeY()/2), getButtonText(),24, getTextColor());
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
