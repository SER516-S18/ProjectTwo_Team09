package app.client.controller;

import app.client.gui.Graph;

public class ClientSocketController {
	
	public static final int PORT_NUM=1516;
	public static final String HOSTNAME="localhost";
	private ClientSocketConnector clientSocketConnector ;
	public void startGraph(Graph graphObj) {
		new Thread(new GraphUpdateThread(graphObj)).start();
	}
	
	public ClientSocketConnector getClientSocketConnector() {
		return clientSocketConnector;
	}
	public void startServer(int channelNumber) {
		clientSocketConnector= new ClientSocketConnector(HOSTNAME, PORT_NUM, channelNumber);
		new Thread(clientSocketConnector).start();
	}
	public void stopServer() {
		this.getClientSocketConnector().setClientStatus(false);
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ClientSocketController csc=new ClientSocketController();
		csc.startServer(2);
		Thread.sleep(10000);
		csc.stopServer();
	}

}
