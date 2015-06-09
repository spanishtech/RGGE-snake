package me.soxey6.game.scenes;

import org.newdawn.slick.Color;

import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
import me.soxey6.engine.objects.gui.Button;
import me.soxey6.engine.objects.gui.Label;

public class About extends Scene
{

	public About()
	{
		super("About");
		// TODO Auto-generated constructor stub
		getGui().getGuiElements().add(new Label("label_title", getGui(), 400, 100, "About", 42, Color.white));
		getGui().getGuiElements().add(new Label("label_author", getGui(), 400, 150, "Author: Soxey6/Spanish/Pat Childs", 32, Color.white));
		getGui().getGuiElements().add(new Label("label_engine", getGui(), 400, 200, "Engine: RGGE created Spanish", 32, Color.white));
		getGui().getGuiElements().add(new Label("label_author", getGui(), 400, 250, "Website: spanishtech.github.io/RGGE-Snake/", 32, Color.white));
		getGui().getGuiElements().add(new Button("button_back", "Back", this.getGui(),Color.black, new Color(0, 255, 0), 350, 440, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				getGame().getSceneManager().switchScene("Main Menu");
			}
		});
	}
	
	@Override
	public void render()
	{
		for(GameObject gameObject:this.getGameObjects())
		{
			gameObject.render();
		}
		getGui().render();
	}
	
	@Override
	public void logic()
	{
		getCheatManager().processCheat(this);
		getGui().logic();
	}
}
