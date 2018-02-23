package app.server;

import java.util.HashMap;

/* Entire exception from server is handled here */
public class ErrorCodes {
	
	private static HashMap<String, String> errorDescriptionHash = new HashMap<String, String>();
	// Error with error codes

	public static final String N101 = "N101"; 

	static {
		errorDescriptionHash.put(N101, "Please enter a valid number");
	}

	public static final String N102 = "N102"; 

	static {
		errorDescriptionHash.put(N102, "The highest value must be greater than or equal to the lowest value.");
	}

	
	public static String printErrorMessage(String errorCode) {
		if (errorDescriptionHash.get(errorCode) != null) {
			return (String) errorDescriptionHash.get(errorCode);
		} else {
			return "Server Error Occured. Please try again later. "+ errorCode; // No I18N
		}
	}



}
