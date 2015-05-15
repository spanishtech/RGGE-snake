package me.soxey6.utils;

import java.awt.Font;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 * A bunch of tools used for rendering in OpenGL, a basic wrapper for OpenGL and SlickUtils fonts.
 * @author Spanish
 *
 */
public class RenderingUtils{
	private Font awtFont;
	private HashMap<Integer ,TrueTypeFont> fonts = new HashMap<Integer ,TrueTypeFont>();
	private static RenderingUtils renderingUtils;
	
	public RenderingUtils()
	{
		 this.awtFont = new Font("Times New Roman", Font.BOLD, 65);
		 this.fonts.put(65, new TrueTypeFont(awtFont, true));
		 renderingUtils=this;
	}
	
	/**
	 * Getting the height of a string with the current font and size.
	 * @param String text 	- The string to be measured.
	 * @param int size 		- The size of the font to be measured.
	 * @return int height 	- The height of the string.
	 */
	public int getHeight(String text, int size)
	{
		if(!fonts.containsKey(size))
		{
			this.awtFont = new Font("Times New Roman", Font.BOLD, size);
			this.fonts.put(size, new TrueTypeFont(awtFont, true));
		}
		if(text!=null)
			return this.fonts.get(size).getHeight(text);
		else
			Logger.getLogger().log(Logger.getLogger().WARNING, "Attempted to size a null string");
		return 0;
	}
	
	/**
	 * Getting the width of a string with the current font and size.
	 * @param String text 	- The string to be measured.
	 * @param int size 		- The size of the font to be measured.
	 * @return int width	- The width of the string.
	 */
	public int getWidth(String text, int size)
	{
		if(!fonts.containsKey(size))
		{
			this.awtFont = new Font("Times New Roman", Font.BOLD, size);
			this.fonts.put(size, new TrueTypeFont(awtFont, true));
		}
		if(text!=null)
			return this.fonts.get(size).getWidth(text);
		else
			Logger.getLogger().log(Logger.getLogger().WARNING, "Attempted to size a null string");
		return 0;
	}
	
	/**
	 * This function draws a string at any point in any color with any size
	 * @param int x 		- The position to render in X
	 * @param int y 		- The position to render in Y
	 * @param String text 	- The text to be rendered
	 * @param int size 		- The size to render the text in.
	 * @param Color color 	- The color to render the text in
	 */
	public void drawString(int x, int y, String text, int size, Color color)
	{
		if(!fonts.containsKey(size))
		{
			this.awtFont = new Font("Times New Roman", Font.BOLD, size);
			this.fonts.put(size, new TrueTypeFont(awtFont, true));
		}
		if(text!=null)
			this.fonts.get(size).drawString(x, y, text, color);
		else
			Logger.getLogger().log(Logger.getLogger().WARNING, "Attempted to render a null string");

	}
	
	public void drawStringCentered(int x, int y, String text, int size, Color color)
	{
		if(!fonts.containsKey(size))
		{
			this.awtFont = new Font("Times New Roman", Font.BOLD, size);
			this.fonts.put(size, new TrueTypeFont(awtFont, true));
		}
		if(text!=null)
			this.fonts.get(size).drawString(x-fonts.get(size).getWidth(text)/2, y-fonts.get(size).getHeight(text)/2, text, color);
		else
			Logger.getLogger().log(Logger.getLogger().WARNING, "Attempted to render a null string");
	}
	
	public void drawQuad(int posX1, int posY1, int posX2, int posY2, Color colour)
	{
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor4f(colour.getRed(), colour.getGreen(), colour.getBlue(),colour.getAlpha());
		    GL11.glVertex2f(posX1,posY1);
		    GL11.glVertex2f(posX2,posY1);
		    GL11.glVertex2f(posX2,posY2);
		    GL11.glVertex2f(posX1,posY2);
		GL11.glEnd();
    	GL11.glEnable(GL11.GL_BLEND);
	}

	public static RenderingUtils getRenderingUtils() {
		return renderingUtils;
	}

	public static void setRenderingUtils(RenderingUtils renderingUtils) {
		RenderingUtils.renderingUtils = renderingUtils;
	}
}
