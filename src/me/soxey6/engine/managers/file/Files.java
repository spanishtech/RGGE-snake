package me.soxey6.engine.managers.file;

public enum Files {
	/*
	 * These are here to sort the type of files that will be loaded and from what folder
	 * Sound	- Sounds: Short sound effects (data/sounds/*.mp3)
	 * Music	- Music: Long pieces of music (data/sounds/*.mp3)
	 * Texture 	- Textures: An image that will be drawn (data/textures/*.png)
	 * Model 	- Unused
	 * Save		- Saves: Information stored to save information to be loaded later (data/saves/*.json)
	 */
	SOUND, MUSIC, TEXTURE, MODEL, SAVE
}
