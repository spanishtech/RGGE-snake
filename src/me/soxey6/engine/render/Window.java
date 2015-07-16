package me.soxey6.engine.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
@SuppressWarnings("static-access")
/**
 * Wrapper for OpenGL display window
 * Used for creating, managing and getting information about windows
 */
public class Window
{
	private String name;
	private int sx;
	private int sy;
	private Display display;
	
	/**
	 * Takes in a name, and the size in x and y and creates a window based on that information
	 * @param s The name of the window
	 * @param sx The size x
	 * @param sy The size y
	 */
	public Window(String s ,int sx, int sy)
	{
		this.name = s;
		this.sx = sx;
		this.sy = sy;
		try {
			display.setDisplayMode(new DisplayMode(sx, sy));
			display.create();
			display.setTitle(name);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the display to a new frame
	 */
	public void update()
	{
		display.update();
	}
	
	/**
	 * Removes the window
	 */
	public void destroy()
	{
		display.destroy();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSx() {
		return sx;
	}

	public void setSx(int sx) {
		this.sx = sx;
	}

	public int getSy() {
		return sy;
	}

	public void setSy(int sy) {
		this.sy = sy;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}
}
