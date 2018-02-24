package app.server;

import java.util.HashMap;

/**
 * Server Side Exception Handler
 * 
 * @author Mohan Vasantrao Yadav
 * @author Sangeetha Swaminathan
 * @author Ganesh Kumar
 * @version 1.0
 * @since 2018-02-23
 * 
 */
public class ServerException {

	private static ServerView view = null;
	private static HashMap<String, String> errorDescriptionHash = new HashMap<String, String>();

	
	static {
		errorDescriptionHash.put("NumberFormatException", "INFO: Please enter a valid number");
		errorDescriptionHash.put("MinMaxException",
				"INFO: The highest value must be greater than or equal to the lowest value.");
	}

	/**
	 * Returns the Server View Object
	 *
	 * @param none
	 *
	 */
	public static void getView() {
		view = ServerController.getView();
	}

	/**
	 * Appends the corresponding message in the server console.
	 * 
	 * @param errorCode
	 *            - Exception Name or Error Code
	 */
	public static void printErrorMessage(String errorCode) {
		if (view == null) {
			getView();
		}
		if (errorDescriptionHash.containsKey(errorCode)) {
			view.log((String) errorDescriptionHash.get(errorCode));
		} else {
			view.log("Error: " + errorCode);
		}

	}

}
