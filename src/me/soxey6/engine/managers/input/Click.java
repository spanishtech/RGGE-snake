package me.soxey6.engine.managers.input;

/**
 * A very basic class used for registering clicks and their locations.
 * PLEASE USE vector2f instead.
 * @author spanish
 */
public class Click {
	private int x;
	private int y;
	
	public Click(int x, int y)
	{
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
