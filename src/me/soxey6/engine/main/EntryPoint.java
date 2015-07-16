package me.soxey6.engine.main;


/**
 * The entry point of the game that then sets up the game object making almost everything non-static.
 * @author Pat Childs || Soxey6
 * @version Dev-0.0.2
 */
public class EntryPoint
{
	private static Game game;
	private final static String gameName = "Snake, SNAKE, SNAAAKEEE";
	
	public static void main(String[] args)
	{
		setGame(new Game(gameName));

	}
	public static Game getGame() {
		return game;
	}
	public static void setGame(Game game) {
		EntryPoint.game = game;
	}
	
}