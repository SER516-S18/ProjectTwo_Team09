package app.client;

import app.client.gui.ClientView;
import app.client.model.ClientCommonData;

/**
 * It is the main entry point for the client window. 
 * It initializes the client window and displays the UI
 *  for user communication. Also, the port number specified 
 *  is used to connect to the server.
 *
 */
public class ClientMain {

	public static int PORT = 1516;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientView client = new ClientView();
		ClientCommonData.getInstance().setParentFrame(client);
	}
}