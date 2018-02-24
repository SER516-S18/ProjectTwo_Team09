package app.client.controller;

import app.client.gui.Graph;
import app.client.model.ClientCommonData;
import app.client.model.LogConstants;

/**
 * Creates a thread for updating the graph on UI after 1000 milliseconds.
 *
 *	@author Manish Tandon
 *  @version 1.0
 *  @since	 February, 2018
 *  
 */
public class GraphUpdateThread implements Runnable{
	Graph graphObj;
	
	private static final int DELAY = 1000;
	
	/**
	 * Class constructor
	 *  
	 * @param graphObj	graph panel object to be updated on thread
	 */
	public GraphUpdateThread(Graph graphObj) {
		// TODO Auto-generated constructor stub
		this.graphObj=graphObj;
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(DELAY);
				graphObj.updateGraph();
				ClientCommonData.getInstance().getClientFrame().revalidate();
				ClientCommonData.getInstance().getClientFrame().repaint();

			} catch (InterruptedException e) {
				ClientCommonData.getInstance().logError(LogConstants.GENRICERROR);
			}

		}
	}


}
