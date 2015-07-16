package me.soxey6.engine.managers.sound;

import java.util.HashMap;

import org.lwjgl.openal.AL;

public class SoundManager {
	private static SoundManager soundManager;
	
	private final float PITCH = 1.0f;
	
	private HashMap<String, Audio> music = new HashMap<String, Audio>();
	private HashMap<String, Audio> soundEffects = new HashMap<String, Audio>();
	private HashMap<String, Audio> currentlyPlayingMusic = new HashMap<String, Audio>();
	private HashMap<String, Audio> currentlyPlayingSoundEffects = new HashMap<String, Audio>();

	
	private float masterVolume=1.0f;
	private float soundEffectsVolume = 1.0f;
	private float musicVolume = 0.5f;
	
	
	public SoundManager()
	{
		setSoundManager(this);
	}
	
	/**
	 * Creates a new music track for later use from a fileName
	 * @param fileName FileName of the track
	 */
	public void newMusic(String fileName)
	{
		getMusic().put(fileName, new Audio(fileName, AudioType.MUSIC));
	}
	
	/**
	 * Creates a new sound effect for later use from a fileName
	 * @param fileName FileName of the track
	 */
	public void newSoundEffect(String fileName)
	{
		getSoundEffects().put(fileName, new Audio(fileName, AudioType.SOUND_EFFECT));
	}
	
	/**
	 * Gets the music based on it's name
	 * @param name The name of the music
	 * @return The music that matches that name
	 */
	public Audio getMusic(String name)
	{
		return getMusic().get(name);
	}
	
	/**
	 * Gets the sound effect based on it's name
	 * @param name The name of the sound effect
	 * @return The sound effect that matches that name
	 */
	public Audio getSoundEffect(String name)
	{
		return getSoundEffects().get(name);
	}
	
	/**
	 * Plays music once
	 * @param audio Plays the audio as music once
	 */
	public void playMusicOnce(Audio audio)
	{
		audio.playAsMusic(PITCH, getMasterVolume()*getMusicVolume(), false);
	}
	
	/**
	 * Plays music looped
	 * @param audio Plays the audio as music looped
	 */
	public void playMusicLooped(Audio audio)
	{
		audio.playAsMusic(PITCH, getMasterVolume()*getMusicVolume(), true);

	}
	
	/**
	 * Plays a sound effect
	 * @param audio Plays the audio as a sound effect
	 */
	public void playSoundEffect(Audio audio)
	{
		audio.playAsSoundEffect(PITCH, getMasterVolume()*getSoundEffectsVolume(), false);
	}
	
	/**
	 * Plays music once
	 * @param name the name of the music to be played
	 */
	public void playMusicOnce(String name)
	{
		getMusic().get(name).playAsMusic(PITCH, getMasterVolume()*getMusicVolume(), false);
	}
	
	/**
	 * Plays music Looped
	 * @param name the name of the music to be played
	 */
	public void playMusicLooped(String name)
	{
		getMusic(name).playAsMusic(PITCH, getMasterVolume()*getMusicVolume(), true);

	}
	
	/**
	 * Plays a sound effect
	 * @param name the name of the sound effect to be played
	 */
	public void playSoundEffect(String name)
	{
		getSoundEffect(name).playAsSoundEffect(PITCH, getMasterVolume()*getSoundEffectsVolume(), false);
	}
	
	/**
	 * @deprecated
	 */
	public void playMusicOnce(Audio audio, float singleVolume)
	{
		audio.playAsMusic(PITCH, getMasterVolume()*getMusicVolume()*singleVolume, false);
	}
	
	/**
	 * @deprecated
	 */
	public void playMusicLooped(Audio audio, float singleVolume)
	{
		audio.playAsMusic(PITCH, getMasterVolume()*getMusicVolume()*singleVolume, true);

	}
	
	/**
	 * @deprecated
	 */
	public void playSoundEffect(Audio audio, float singleVolume)
	{
		audio.playAsSoundEffect(PITCH, getMasterVolume()*getSoundEffectsVolume()*singleVolume, false);
	}
	
	/**
	 * @deprecated
	 */
	public void playMusicOnce(String name, float singleVolume)
	{
		getMusic().get(name).playAsMusic(PITCH, getMasterVolume()*getMusicVolume()*singleVolume, false);
	}
	
	/**
	 * @deprecated
	 */
	public void playMusicLooped(String name, float singleVolume)
	{
		getMusic(name).playAsMusic(PITCH, getMasterVolume()*getMusicVolume()*singleVolume, true);

	}
	
	/**
	 * @deprecated
	 */
	public void playSoundEffect(String name, float singleVolume)
	{
		getSoundEffect(name).playAsSoundEffect(PITCH, getMasterVolume()*getSoundEffectsVolume()*singleVolume, false);
	}
	
	/**
	 * Cleans up openAL
	 */
	public void destroy()
	{
		AL.destroy();
	}

	public static SoundManager getSoundManager() {
		return soundManager;
	}

	public static void setSoundManager(SoundManager soundManager) {
		SoundManager.soundManager = soundManager;
	}

	public float getMasterVolume() {
		return masterVolume;
	}

	public void setMasterVolume(float masterVolume) {
		this.masterVolume = masterVolume;
	}

	/**
	 * @return the music
	 */
	public HashMap<String, Audio> getMusic()
	{
		return music;
	}

	/**
	 * @return the soundEffects
	 */
	public HashMap<String, Audio> getSoundEffects()
	{
		return soundEffects;
	}

	/**
	 * @return the currentlyPlayingMusic
	 */
	public HashMap<String, Audio> getCurrentlyPlayingMusic()
	{
		return currentlyPlayingMusic;
	}

	/**
	 * @return the currentlyPlayingSoundEffects
	 */
	public HashMap<String, Audio> getCurrentlyPlayingSoundEffects()
	{
		return currentlyPlayingSoundEffects;
	}

	/**
	 * @param music the music to set
	 */
	public void setMusic(HashMap<String, Audio> music)
	{
		this.music = music;
	}

	/**
	 * @param soundEffects the soundEffects to set
	 */
	public void setSoundEffects(HashMap<String, Audio> soundEffects)
	{
		this.soundEffects = soundEffects;
	}

	/**
	 * @param currentlyPlayingMusic the currentlyPlayingMusic to set
	 */
	public void setCurrentlyPlayingMusic(
			HashMap<String, Audio> currentlyPlayingMusic)
	{
		this.currentlyPlayingMusic = currentlyPlayingMusic;
	}

	/**
	 * @param currentlyPlayingSoundEffects the currentlyPlayingSoundEffects to set
	 */
	public void setCurrentlyPlayingSoundEffects(
			HashMap<String, Audio> currentlyPlayingSoundEffects)
	{
		this.currentlyPlayingSoundEffects = currentlyPlayingSoundEffects;
	}

	public float getSoundEffectsVolume() {
		return soundEffectsVolume;
	}

	public void setSoundEffectsVolume(float soundEffectsVolume) {
		this.soundEffectsVolume = soundEffectsVolume;
	}

	public float getMusicVolume() {
		return musicVolume;
	}

	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}

	public float getPITCH() {
		return PITCH;
	}
	
	
}
