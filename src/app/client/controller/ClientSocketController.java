package app.client.controller;

import app.client.gui.Graph;
import app.client.model.ClientCommonData;

/**
 * This class performs as an interface between the 
 * ClientSocketConnector class, Graph Thread
 * and the UI classes to connect to the socket.
 * 
 */
public class ClientSocketController {
	
	public static final int PORT_NUM=1516;
	public static final String HOSTNAME="localhost";
	private ClientSocketConnector clientSocketConnector ;
	private Thread graphUpdateThread;
	
	/**
	 * Starts the graph thread when needed to update the graph 
	 * on successful connection to the server.
	 *
	 * @param  graphObj  graph panel object to be updated on thread
	 */
	public void startGraph(Graph graphObj) {
		graphUpdateThread = new Thread(new GraphUpdateThread(graphObj));
		graphUpdateThread.start();
		
	}
	
	/**
	 * Stops and clears the graph thread when stopped by the user.
	 *
	 */
	public void stopGraph() {
		ClientCommonData.getInstance().getDataFromServer().clear();
		graphUpdateThread.interrupt();
	}
	
	public ClientSocketConnector getClientSocketConnector() {
		return clientSocketConnector;
	}
	
	/**
	 * Starts the socket connection thread to connect to the server. 
	 *
	 * @param  channelNumber  number of channels selected
	 */
	public void startServer(int channelNumber) {
		clientSocketConnector= new ClientSocketConnector(HOSTNAME, PORT_NUM, channelNumber);
		new Thread(clientSocketConnector).start();
	}
	
	/**
	 * Stops the connection to the server.
	 *
	 */
	public void stopServer() {
		this.getClientSocketConnector().setClientStatus(false);
	}

}
