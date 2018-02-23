package app.server;

import java.util.HashMap;

/* Entire exception from server is handled here */
public class ServerException {
	
	private static ServerView view = null;
	private static HashMap<String, String> errorDescriptionHash = new HashMap<String, String>();
	// Error with error codes

	static {
		errorDescriptionHash.put("NumberFormatException", "INFO: Please enter a valid number");
		errorDescriptionHash.put("MinMaxException", "INFO: The highest value must be greater than or equal to the lowest value.");
	}	

	public static void getView() {
		view = ServerController.getView();
	}
	
	public static void printErrorMessage(String errorCode) {
		if(view == null) {
			getView();
		}
		if(errorDescriptionHash.containsKey(errorCode))
		{
			view.log((String) errorDescriptionHash.get(errorCode));
		}
		else
		{
			view.log("Error: "+errorCode);
		}

		
	}

}
