package me.soxey6.engine.main;

/**
 * @author Pat Childs || Soxey6
 * @version Dev-0.0.1
 */
public class MainComponent
{
	@SuppressWarnings("unused")
	private static Game game;
	private final static String gameName = "Snake, SNAKE, SNAAAKEEE";
	public static void main(String[] args)
	{
		game = new Game(gameName);
	}
	
}
