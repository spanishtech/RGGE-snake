package me.soxey6.utils;
import java.util.ArrayList;
/**
 * The class contains functions for checking errors and a list of error codes.
 * Description of values
 * (+/-)X(Y)YY
 * Where: + is a recoverable error and - is an unrecoverable
 * The X is the catagory
 * Three y is a Warning two are errors
 * Y define the specific error/warning.
 * @author Soxey6
 */
public class ErrorHandler{
	/**
	 * An unknown error with input
	 * Value is 100
	 */
	public final int INPT_UNKWN = 100;
	/**
	 * An null pointer error with input
	 * Value is 101
	 */
	public final int INPT_NULLPTR=101;
	/**
	 * A warning where there is already a copy of the key in the key array (Check performance)
	 * Value is 1001
	 */
	public final int INPT_DUPKEY= 1001;
	/**
	 * A warning where there a key is already down but has not been registered before (Check performance)
	 * Value is 1002
	 */
	public final int INPT_NOKEY = 1002;
	
	/**
	 * An unknown error when rendering
	 * Value is 200
	 */
	public int RNDR_UNKWN = 200;
	
	/**
	 * An unknown error when performing logic
	 * Value is 300
	 */
	public int LGIC_UNKWN = 300;
	
	/**
	 * An unknown error with OpenGL
	 * Value is 400
	 */
	public int OPNGL_UNKWN= 400;
	/**
	 * A critical error when OpenGL has not native libraries assigned supplied.
	 * Value is -401
	 */
	public int OPNGL_NONATIVLIB=-401;
	
	/**
	 * An unknown error when performing math
	 * Value is 500
	 */
	public final int MATH_UNKWN = 500;
	
	/**
	 * An unknown error with libraries
	 * Value is 600
	 */
	public final int LIBS_UNKWN = 600;
	
	/**
	 * An unknown error
	 * Value is 999
	 */
	public final int UNKWN_UNKWN = 999;
	
	public static ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public static void setErrorHandler(ErrorHandler errorHandler) {
		ErrorHandler.errorHandler = errorHandler;
	}

	private static ErrorHandler errorHandler;
	
	// Bellow is a hard coded arrays with errors considered Critical, both exception objects.
	private ArrayList<Exception> criticalErrors = new ArrayList<Exception>();
	
	// This is the last error.
	public int lastError=0;
	
	public ErrorHandler()
	{
		errorHandler = this;
	}
	
	/**
	 *  This function will assure that no errors that have been thrown are recoverable.
	 * @param Exception error
	 * @return if it's unrecoverable as boolean
	 */
	public boolean checkCritError(Exception error)
	{
		if(criticalErrors.contains(error))
			return true;
		return false;
	}
	
	/**
	 *  This function will assure that no errors that have been thrown are recoverable.
	 * @param int errorCode
	 * @return if it's unrecoverable as boolean
	 */
	public boolean checkCritError(int errorCode)
	{
		if(errorCode<0)
			return true;
		return false;
	}

	public ArrayList<Exception> getCriticalErrors() {
		return criticalErrors;
	}

	public void setCriticalErrors(ArrayList<Exception> criticalErrors) {
		this.criticalErrors = criticalErrors;
	}

	public int getLastError() {
		return lastError;
	}

	public void setLastError(int lastError) {
		this.lastError = lastError;
	}
}
