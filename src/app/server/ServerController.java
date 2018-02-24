package app.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import app.server.ServerNetworkThread;
import app.server.ServerView;

/**
 * Centralized server controller to start/stop the server and to send stream of data to the client
 * 
 * @author Nelson Tran
 * @author Ganesh Kumar
 * @version 1.0
 */

public class ServerController {

	private ServerNetworkThread networkThread;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private ServerOptions options;
	private static ServerView view;
	private boolean status;
	private int port;

	public ServerController(int port, ServerView view) {
		this.status = false;
		this.port = port;
		this.view = view;
	}

	public void startServer(){
		// Update the view.
		view.log("Info: The server has been started.");
		view.setStatus(true);
		this.networkThread = new ServerNetworkThread(this.serverSocket, this.clientSocket, this.port, this);
		this.networkThread.start();
	}
	
	public static ServerView getView() {
		return view;
	}

	public void stopServer() {
		// Update the view.
		view.log("Info: The server has been stopped.");
		view.setStatus(false);
		
		if (networkThread != null) {
			networkThread.interrupt();
			networkThread.closeConnection();
		}
	}

	/**
	 * Event handler for the Start/Stop button. If the server has not been started,
	 * request for the server options (lowest value, highest value, and frequency)
	 * and start the server. Otherwise, stop the server.
	 */
	public void toggleButtonClickHandler() {
		if (!this.status) {
			try {
				this.options = view.getOptions();
				if(this.options!=null)
				{
					this.status = true;
					startServer();
				}
			}
			catch (NumberFormatException e) {
				// This exception is thrown if the text fields do not
				// contain valid integer values.
				ServerException.printErrorMessage("NumberFormatException");
				this.status = false;
			}
			catch (Exception e) {
				ServerException.printErrorMessage(e.getMessage());
				this.status = false;
			}
		}
		else {
			this.status = false;
			stopServer();
		}
	}

	public ServerOptions getOptions() {
		return this.options;
	}

}
