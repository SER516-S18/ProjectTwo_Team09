package app.client.controller;

import app.client.gui.Graph;
import app.client.model.ClientCommonData;

public class ClientSocketController {
	
	public static final int PORT_NUM=1516;
	public static final String HOSTNAME="localhost";
	private ClientSocketConnector clientSocketConnector ;
	private Thread graphUpdateThread;
	
	/**
	 * Returns an Image object that can then be painted on the screen. 
	 * The url argument must specify an absolute {@link URL}. The name
	 * argument is a specifier that is relative to the url argument. 
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * image exists. When this applet attempts to draw the image on
	 * the screen, the data will be loaded. The graphics primitives 
	 * that draw the image will incrementally paint on the screen. 
	 *
	 * @param  url  an absolute URL giving the base location of the image
	 * @param  name the location of the image, relative to the url argument
	 * @return      the image at the specified URL
	 * @see         Image
	 */
	public void startGraph(Graph graphObj) {
		graphUpdateThread = new Thread(new GraphUpdateThread(graphObj));
		graphUpdateThread.start();
		
	}
	
	public void stopGraph() {
		ClientCommonData.getInstance().getDataFromServer().clear();
		graphUpdateThread.interrupt();
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
