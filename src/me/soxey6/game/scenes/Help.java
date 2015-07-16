package me.soxey6.game.scenes;

import me.soxey6.engine.objects.GameObject;
import me.soxey6.engine.objects.Scene;
import me.soxey6.engine.objects.gui.Button;
import me.soxey6.engine.objects.gui.Label;
import me.soxey6.game.objects.Block;
import me.soxey6.game.objects.Pellet;

import org.newdawn.slick.Color;

/**
 * This scene provides vital information about playing the game
 * @author pchilds
 *
 */
public class Help extends Scene
{

	public Help()
	{
		super("Help");
		new Label("title_help", this.getGui(), 400, 20, "Help", 42, Color.white);
		// Player description
		new Label("title_help_player_0", this.getGui(), 400, 50, "This is you, or part of you. You play a snake made up of many segments.", 14, Color.white);
		new Label("title_help_player_1", this.getGui(), 400, 65, "Each segment follows the one infront where the very first is the one that moves indipendently.", 14, Color.white);		
		new Label("title_help_player_2", this.getGui(), 400, 80, "Try to avoid any obstacles including segments of yourself and walls.", 14, Color.white);
		new Label("title_help_player_3", this.getGui(), 400, 95, "Press Space to shoot the last segment of your snake (This destroys walls)", 14, Color.white);
		new Label("title_help_player_4", this.getGui(), 400, 110, "Change direction by pressing: W = Up, A = Left, S = Down, D = Right", 14, Color.white);
		new GameObject("Fake_Head", this, new Color(0, 255, 0), 391, 121, 18, 18);
		
		// Wall description
		new Label("title_help_wall", this.getGui(), 400, 160, "This is a wall, they randomly spawn and will kill you if hit.", 14, Color.white);
		new Block("fake_block",this, Color.magenta, 391, 171);
		
		// Pellet description
		new Label("title_help_wall", this.getGui(), 400, 210, "This is a pellet, they randomly spawn and collecting them adds a segment to the end of you.", 14, Color.white);
		new Pellet("fake_pellet",this, 392, 222);
		
		// edge of screen explaination
		new Label("title_help_wall", this.getGui(), 400, 260, "Going off one side of the screen will place you on the opposite side of the screen.", 14, Color.white);
		

		new Button("button_back", "Back", this.getGui(),Color.black, new Color(0, 255, 0), 350, 470, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				getGame().getSceneManager().switchScene("Main Menu");
			}
		};
	}

}
