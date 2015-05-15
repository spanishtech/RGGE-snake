package me.soxey6.engine.objects.gui;

import org.newdawn.slick.Color;

/**
 * A class for rendering text, it defaults to centered text
 * @author Spanish
 *
 */
public class Label extends GuiElement
{
	private int size;
	private String text;
	private Color color;
	private boolean centered;
	public Label(String name, Gui gui, float posX, float posY, String text, int size, Color color)
	{
		super(name, gui, posX, posY, 0, 0);
		this.setSizeX(getRenderingUtils().getWidth(text, size));
		this.setSizeY(getRenderingUtils().getHeight(text, size));
		this.size=size;
		this.text=text;
		this.color=color;
		this.centered=true;
	}
	public Label(String name, Gui gui, float posX, float posY, String text, int size, Color color,boolean centered)
	{
		super(name, gui, posX, posY, 0, 0);
		this.setSizeX(getRenderingUtils().getWidth(text, size));
		this.setSizeY(getRenderingUtils().getHeight(text, size));
		this.size=size;
		this.text=text;
		this.color=color;
		this.centered=centered;
	}
	
	@Override
	/**
	 * Rendering the text here
	 */
	public void render()
	{
		if(centered)
			getRenderingUtils().drawStringCentered((int)this.getPosX(), (int)this.getPosY(), text, size, color);
		else
			getRenderingUtils().drawString((int)this.getPosX(), (int)this.getPosY(), text, size, color);
	}
	
	/**
	 * @return the size of the font to be used
	 */
	public int getSize()
	{
		return size;
	}
	/**
	 * @return the text that this label renders
	 */
	public String getText()
	{
		return text;
	}
	/**
	 * @param the size of the font to set.
	 */
	public void setSize(int size)
	{
		this.size = size;
	}
	/**
	 * @param the text to change the text to
	 */
	public void setText(String text)
	{
		this.text = text;
	}
	
	
}
