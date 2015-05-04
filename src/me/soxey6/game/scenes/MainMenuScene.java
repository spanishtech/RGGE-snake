package me.soxey6.game.scenes;

import org.newdawn.slick.Color;

import me.soxey6.engine.objects.Button;
import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;

public class MainMenuScene extends Scene
{

	public MainMenuScene()
	{
		super("Main Menu");
		this.getGui().getGuiElements().add(new Button("Button", "Play", this.getGui(),Color.black, new Color(0, 255, 0), 300, 200, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				new MainGameScene("Game");
				this.getGame().getSceneManager().switchScene("Game");
			}
		});
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
