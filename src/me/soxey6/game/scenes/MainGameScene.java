package me.soxey6.game.scenes;

import java.util.ArrayList;
import java.util.Random;

import me.soxey6.engine.managers.event.EventCallback;
import me.soxey6.engine.managers.sound.SoundManager;
import me.soxey6.engine.managers.time.Time;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
import me.soxey6.engine.objects.gui.Label;
import me.soxey6.game.objects.Block;
import me.soxey6.game.objects.Pellet;
import me.soxey6.game.objects.SnakeHead;
import me.soxey6.game.objects.SnakeTail;

import org.newdawn.slick.Color;

/**
 * Is the main game and contains most of the elements of the actual game
 * @author pchilds
 *
 */
public class MainGameScene extends Scene implements EventCallback{
	private final int STARTING_TAIL_LENGTH = 0;
	public SnakeHead snake;
	public ArrayList<Block> walls = new ArrayList<Block>();
	private Random randomGenerator = new Random();
	private Label scoreCounter;
	
	
	public MainGameScene(String name) {
		super(name);
		scoreCounter = new  Label("label_score", this.getGui(), 400, 15, "Score: 0", 42, Color.white);
		
		// Register timers (Also automatically hooks them for ease of use)
		getTimer().newTimer(new Time(70), this);
		getTimer().newTimer(new Time(1000), this);
		getTimer().newTimer(new Time(2000), this);
		this.snake = new SnakeHead("Head", this, new Color(0, 255, 0), 21, 21, 18, 18);
		
		for(int i=0; i<=STARTING_TAIL_LENGTH-1; i++)
		{
			new SnakeTail(Integer.toString(i),  this, new Color(0, 255, 0), 21, 21, 18, 18, snake);
		}
		
		SoundManager.getSoundManager().newMusic("main_theme.aiff");
		SoundManager.getSoundManager().playMusicLooped("main_theme.aiff");
	}
	
	@Override
	public void render()
	{
		for(GameObject gameObject:this.getGameObjects())
		{
			gameObject.render();
		}
		this.getGui().render();
	}
	
	@Override
	public void logic()
	{
		getCheatManager().processCheat(this);
		for(int i=this.getGameObjects().size()-1;i>=0;i--)
		{
			getGameObjects().get(i).logic();
		}
		getGui().logic();
	}
	
	@Override
	public void callback(String eventName)
	{
		// Check that's it's the right event
		if(eventName.equalsIgnoreCase("TIMER_70"))
			// Trigger the logic function
			logic();
		else if(eventName.equalsIgnoreCase("TIMER_1000"))
			// Spawn a new wall
			spawnNewWall();
		else if(eventName.equalsIgnoreCase("TIMER_2000"))
			// Spawn a new pellet
			spawnNewPellet();
	}
	
	/**
	 * Spawns a pellet where there are no other game objects around
	 */
	private void spawnNewPellet()
	{
		int randomX = randomGenerator.nextInt(41);
		int randomY = randomGenerator.nextInt(31);
		while(true)
		{
			boolean exit = true;
			for(GameObject go : getGameObjects())
			{
				if(randomX*20+1==go.getPosX()&&randomY*20+1==go.getPosY())
				{
					exit=false;
				}
			}
			if(exit)
			{
				break;
			}else{
				randomX = randomGenerator.nextInt(41);
				randomY = randomGenerator.nextInt(31);
			}
		}
		new Pellet("Pellet", this, randomX*20+2, randomY*20+2);
		
	}
	
	/**
	 * Spawns a wall where there are no other game objects around
	 */
	private void spawnNewWall()
	{
		int randomX = randomGenerator.nextInt(41);
		int randomY = randomGenerator.nextInt(31);
		while(true)
		{
			boolean exit = true;
			for(GameObject go : getGameObjects())
			{
				if(randomX*20+1==go.getPosX()&&randomY*20+1==go.getPosY())
				{
					exit=false;
				}
			}
			if(exit)
			{
				break;
			}else{
				randomX = randomGenerator.nextInt(41);
				randomY = randomGenerator.nextInt(31);
			}
		}
		
		walls.add(new Block("Wall: "+String.valueOf(walls.size()), this, Color.magenta, randomX*20+1, randomY*20+1));
		
	}
	
	/**
	 * Increases the score
	 * To be changed to score() later
	 */
	public void score()
	{
		scoreCounter.setText("Score: "+(snake.snake.size()-1));
	}
	
	/**
	 * The function to be called when the game is over.
	 */
	public void gameOver()
	{
		getSoundManager().getMusic("main_theme.aiff").stop();;
		new GameOverScene(this, snake.snake.size()-1);
		getSceneManager().switchScene("GAME_OVER");
	}
}
