package me.soxey6.engine.objects.gui;

import java.util.ArrayList;

import me.soxey6.engine.objects.Scene;
/**
 * This is a gui class that stores Gui elements and manages them to a small extent, every scene should have one.
 * @author pchilds
 *
 */
public class Gui {
	private boolean oldInput;
	
	public Gui(Scene scene)
	{
		this.scene=scene;
		this.oldInput=true;
	}
	
	private Scene scene;
	private ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();

	public void input()
	{
		for(GuiElement guiElement: guiElements)
		{
			guiElement.input();
		}
	}
	
	public void logic()
	{
		for(GuiElement guiElement: guiElements)
		{
			guiElement.logic();
		}
	}
	
	public void render()
	{
		for(GuiElement guiElement: guiElements)
		{
			guiElement.render();
		}
		
	}
	
	/**
	 * @return the guiElements
	 */
	public ArrayList<GuiElement> getGuiElements()
	{
		return guiElements;
	}

	/**
	 * @param guiElements the guiElements to set
	 */
	public void setGuiElements(ArrayList<GuiElement> guiElements)
	{
		this.guiElements = guiElements;
	}

	/**
	 * @return the scene
	 */
	public Scene getScene()
	{
		return scene;
	}

	/**
	 * @param scene the scene to set
	 */
	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

	/**
	 * @return the oldInput
	 */
	public boolean isOldInput()
	{
		return oldInput;
	}

	/**
	 * @param oldInput the oldInput to set
	 */
	public void setOldInput(boolean oldInput)
	{
		this.oldInput = oldInput;
	}

}
