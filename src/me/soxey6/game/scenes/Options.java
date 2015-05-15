package me.soxey6.game.scenes;

import org.newdawn.slick.Color;

import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
import me.soxey6.engine.objects.gui.Button;
import me.soxey6.engine.objects.gui.Label;

public class Options extends Scene
{

	public Options()
	{
		super("Options");
		getGui().getGuiElements().add(new Label("label_title", getGui(), 400, 100, "Options", 42, Color.white));
		getGui().getGuiElements().add(new Button("button_frame_limit", "Frame Limit: Off", this.getGui(),Color.black, new Color(0, 255, 0), 300, 275, 200, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				switch(getSettings().getSetting("FRAME_LIMIT").getValueInt())
				{
					case 0:
						getSettings().updateSetting("FRAME_LIMIT", 30);
						this.setButtonText("Frame Limit: "+30);
						this.setSizeX(200);
						this.setPosX(300);
						break;
					case 30:
						getSettings().updateSetting("FRAME_LIMIT", 60);
						this.setButtonText("Frame Limit: "+60);
						this.setSizeX(200);
						this.setPosX(300);
						break;
					case 60:
						getSettings().updateSetting("FRAME_LIMIT", 0);
						this.setButtonText("Frame Limit: Off");
						this.setSizeX(200);
						this.setPosX(300);
				}
				
					
			}
		});
		getGui().getGuiElements().add(new Button("button_back", "Back", this.getGui(),Color.black, new Color(0, 255, 0), 350, 440, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				getGame().getSceneManager().switchScene("Main Menu");
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
		getGui().render();
	}
	
	@Override
	public void logic()
	{
		getCheatManager().processCheat(this);
		getGui().logic();
	}
	
}
