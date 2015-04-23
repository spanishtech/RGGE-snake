package me.soxey6.engine.main;

/**
 * @author Pat Childs || Soxey6
 * @version Dev-0.0.2
 */
public class MainComponent
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
		MainComponent.game = game;
	}
	
}
