package me.soxey6.game.scenes;

import me.soxey6.engine.objects.Scene;
import me.soxey6.engine.objects.gui.Button;
import me.soxey6.engine.objects.gui.Label;

import org.newdawn.slick.Color;

/**
 * A scene that shows you a game over with a top score and your current score.
 * @author pchilds
 *
 */
public class GameOverScene extends Scene
{
	public GameOverScene(MainGameScene mainGame, int score)
	{
		super("GAME_OVER");
		try
		{
			mainGame.finalize(mainGame);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
		new Label("label_title_Game Over", this.getGui(), 400, 100, "Game Over", 42, Color.white);
		new Label("label_title_score", this.getGui(), 400, 300, "Your final score: "+score, 30, Color.white);
		new Button("button_highScores", "HighScores", this.getGui(),Color.black, new Color(0, 255, 0), 350, 415, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				new About();
				getSceneManager().switchScene("About");
			}
		};
		new Button("button_back", "Back", this.getGui(),Color.black, new Color(0, 255, 0), 350, 470, 100, 50){
			@Override
			public void onClick(int posX, int posY)
			{
				getGame().getSceneManager().switchScene("Main Menu");
			}
		};
		getSoundManager().newMusic("death.aiff");
		getSoundManager().playMusicLooped("death.aiff");
	}

}
