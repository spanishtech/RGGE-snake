package me.soxey6.game.scenes;

import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
import me.soxey6.engine.objects.gui.Button;
import me.soxey6.engine.objects.gui.Label;

import org.newdawn.slick.Color;

public class MainMenuScene extends Scene
{

	public MainMenuScene()
	{
		super("Main Menu");
		getGui().getGuiElements().add(new Button("button_play", "Play", this.getGui(),Color.black, new Color(0, 255, 0), 350, 275, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				new MainGameScene("Game");
				getGame().getSceneManager().switchScene("Game");
			}
		});
		getGui().getGuiElements().add(new Button("button_options", "Options", this.getGui(),Color.black, new Color(0, 255, 0), 350, 330, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				new Options();
				getGame().getSceneManager().switchScene("Options");
			}
		});
		getGui().getGuiElements().add(new Button("button_about", "About", this.getGui(),Color.black, new Color(0, 255, 0), 350, 385, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				new About();
				getGame().getSceneManager().switchScene("About");
			}
		});
		getGui().getGuiElements().add(new Button("button_quit", "Quit", this.getGui(),Color.black, new Color(0, 255, 0), 350, 440, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				// Fuck it, I quit xD
				System.exit(0);
			}
		});
		getGui().getGuiElements().add(new Label("label_title_snakeSnake", this.getGui(), 400, 100, "Snake, SNAKE,", 42, Color.white));
		getGui().getGuiElements().add(new Label("label_title_snaaakkkeee", this.getGui(), 400, 150, "SNAAAKKKKEEE", 60, Color.white));
	}
	
	@Override
	public void input()
	{
		getCheatManager().cheatInputCheck();
		for(GameObject gameObject:this.getGameObjects())
		{
			gameObject.input();
		}
		getGui().input();

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
		getGui().logic();
	}
}
