package me.soxey6.utils;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 * A bunch of tools used for rendering in OpenGL, a basic wrapper for rendering.
 * @author pchilds
 *
 */
public class RenderingUtils {
	private Font awtFont;
	private TrueTypeFont font;
	private static RenderingUtils renderingUtils;
	
	
	public RenderingUtils()
	{
		 this.awtFont = new Font("Times New Roman", Font.BOLD, 65);
		 this.font = new TrueTypeFont(awtFont, true);
		 renderingUtils=this;
	}
	
	public void changeFontSize(int size)
	{
		this.awtFont = new Font("Times New Roman", Font.BOLD, size);
		this.font = new TrueTypeFont(awtFont, true);
	}
	
	public void drawString(int x, int y, String string, int size, Color color)
	{
		this.awtFont = new Font("Times New Roman", Font.BOLD, size);
		this.font = new TrueTypeFont(awtFont, true);
		if(string!=null)
		this.font.drawString(x, y, string, color);

	}
	
	public void drawStringCentered(int x, int y, String string, int size, Color color)
	{
		if(this.awtFont.getSize()!=size)
		{
			this.awtFont = new Font("Times New Roman", Font.BOLD, size);
			this.font = new TrueTypeFont(awtFont, true);
		}
		if(string!=null)
		this.font.drawString(x-font.getWidth(string)/2, y-font.getHeight(string)/2, string, color);
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
