package me.soxey6.utils;

import me.soxey6.engine.main.Game;
import me.soxey6.engine.managers.file.FileManager;

/**
 * This class will allow logging of any type of information into both 
 */
public class Logger
{
	private final boolean DEBUG_MODE = true;
	private final boolean SAVE_LOGS = false;
	
	public final int DEBUG 		= 0;
	public final int INFO		= 1;
	public final int WARNING	= 2;
	public final int ERROR		= 3;
	
	private static Logger logger;
	
	private int loggingLevel = 0;
	
	public Logger()
	{
		logger=this;
	}
	
	/**
	 * This function logs errors with varying levels
	 * @param int level - Used for sorting the logs
	 * @param Object object - The item to be printed to the logs.
	 */
	public void log(int level, Object object)
	{
		if(level>=this.loggingLevel)
		{
			this.printLog(level, object);
			if(SAVE_LOGS)
			this.appendLog(level, object);
		}
	}
	
	/**
	 * This function appends the log to the end of the log file.
	 * @param int level - Used for sorting the logs
	 * @param Object object - The item to be printed to the logs.
	 */
	private void appendLog(int level, Object object)
	{
		String stringToPrint = "";
		switch(level){
			case 0:
				stringToPrint+="[DEBUG] ";
				break;
			case 1:
				stringToPrint+="[INFO] ";
				break;
			case 2:
				stringToPrint+="[WARNING] ";
				break;
			case 3:
				stringToPrint+="[ERROR] ";
				break;
		}
		stringToPrint+=object.toString();
		FileManager.getFileManager().appendFile(Game.getGame().getGameName()+".log", stringToPrint+"\n");
	}
	
	/**
	 * This function prints the log to console
	 * @param int level - Used for sorting the logs
	 * @param Object object - The item to be printed to the logs.
	 */
	private void printLog(int level, Object object)
	{
		String stringToPrint = "";
		switch(level){
			case 0:
				stringToPrint+="[DEBUG] ";
				break;
			case 1:
				stringToPrint+="[INFO] ";
				break;
			case 2:
				stringToPrint+="[WARNING] ";
				break;
			case 3:
				stringToPrint+="[ERROR] ";
				break;
		}
		stringToPrint+=object.toString();
		System.out.println(stringToPrint);
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Logger.logger = logger;
	}

	public int getLoggingLevel() {
		return loggingLevel;
	}
	public boolean isDebugMode()
	{
		return DEBUG_MODE;
	}

}
