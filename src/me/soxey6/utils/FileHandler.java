package me.soxey6.utils;

public class FileHandler {
	/**
	 * This is the file handler, all file functions are put here.
	 * All functions are threaded.
	 */
	private static FileHandler fileHandler;
	

	public FileHandler()
	{
		fileHandler=this;
	}
	
	/**
	 * Creates a file with nothing inside
	 * @param path relative to current position
	 * @param file name
	 * @return A code, if not 0 then something went wrong
	 * @see ErrorHandler
	 */
	public int createFile(String path, String name)
	{
		return 0;
	}
	
	/**
	 * Appends the content to the end of the file
	 * @param path relative to current position
	 * @param content to append
	 * @return A code, if not 0 then something went wrong
	 * @see ErrorHandler
	 */
	public int appendFile(String path, String content)
	{
		return 0;
	}
	
	/**
	 * Writes over a current file with the content specified
	 * @param path relative to current position
	 * @param content to write
	 * @return A code, if not 0 then something went wrong
	 * @see ErrorHandler
	 */
	public int overWriteFile(String path, String content)
	{
		return 0;
	}
	
	/**
	 * Reads a whole file and returns its contents
	 * @param path relative to current position
	 * @return the content of the file
	 */
	public String readFile(String path)
	{
		return null;
		
	}
	
	/**
	 * Detels a file
	 * @param path relative to current position
	 * @return A code, if not 0 then something went wrong
	 * @see ErrorHandler
	 */
	public int deleteFile(String path)
	{
		return 0;
	}

	public static FileHandler getFileHandler() {
		return fileHandler;
	}

	public static void setFileHandler(FileHandler fileHandler) {
		FileHandler.fileHandler = fileHandler;
	}
}
