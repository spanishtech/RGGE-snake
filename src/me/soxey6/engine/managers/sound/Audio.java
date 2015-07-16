package me.soxey6.engine.managers.sound;

import java.io.IOException;

import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * This class is a light wrapper for SlickUtil's Audio interface. Nothing more.
 */
public class Audio
{
	private AudioType audioType;
	private org.newdawn.slick.openal.Audio audio;
	
	public Audio(String fileName, AudioType audioType)
	{
		try
		{
			switch(audioType)
			{
				case SOUND_EFFECT:
					// Asuming it's a .wav file
					audio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("data/sound/"+fileName));
					break;
				case MUSIC:
					// Assuming it's a .aif file
					audio = AudioLoader.getAudio("AIF", ResourceLoader.getResourceAsStream("data/music/"+fileName));
					break;
				default:
					
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		this.setAudioType(audioType);
	}
	
	
	
	public int getBufferID()
	{
		return audio.getBufferID();
	}

	
	public float getPosition()
	{
		return audio.getPosition();
	}

	
	public boolean isPlaying()
	{
		return audio.isPlaying();
	}

	
	public int playAsMusic(float arg0, float arg1, boolean arg2)
	{
		return audio.playAsMusic(arg0, arg1, arg2);
	}

	
	public int playAsSoundEffect(float arg0, float arg1, boolean arg2)
	{
		return audio.playAsSoundEffect(arg0, arg1, arg2);
	}

	
	public int playAsSoundEffect(float arg0, float arg1, boolean arg2,
			float arg3, float arg4, float arg5)
	{
		return audio.playAsSoundEffect(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	
	public boolean setPosition(float arg0)
	{
		return audio.setPosition(arg0);
	}

	
	public void stop()
	{
		audio.stop();
		
	}



	/**
	 * @return the audioType
	 */
	public AudioType getAudioType()
	{
		return audioType;
	}



	/**
	 * @param audioType the audioType to set
	 */
	public void setAudioType(AudioType audioType)
	{
		this.audioType = audioType;
	}

}
