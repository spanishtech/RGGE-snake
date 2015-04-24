package me.soxey6.utils;

import me.soxey6.engine.main.Game;

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
	
	/**
	 * This class is for logging things.
	 */
	public Logger()
	{
		logger=this;
	}
	
	public void log(int level, Object object)
	{
		if(level>=this.loggingLevel)
		{
			this.printLog(level, object);
			if(SAVE_LOGS)
			this.appendLog(level, object);
		}
	}
	
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
		FileHandler.getFileHandler().appendFile(Game.getGame().getGameName()+".log", stringToPrint+"\n");
	}
	
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
	//TODO: Create logging class that will log stuff

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
