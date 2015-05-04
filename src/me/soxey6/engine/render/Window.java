package me.soxey6.engine.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
@SuppressWarnings("static-access")
/**
 * This class is the window and it's controls
 */
public class Window
{
	private String name;
	private int sx;
	private int sy;
	private Display display;
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
	
	
	public void update()
	{
		display.update();
	}
	
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
