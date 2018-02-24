package app.client.controller;

import app.client.gui.Graph;
import app.client.model.ClientCommonData;
import app.client.model.LogConstants;

/**
 * Creates a thread for updating the graph on UI after 1000 milliseconds.
 *
 * @author Manish Tandon
 * @version 1.0
 * @since February, 2018
 * 
 */
public class GraphUpdateThread implements Runnable {
	private Graph graphInstance;

	private static final int DELAY = 1000;

	/**
	 * This constructor initializes an instance of the graph thread.
	 * 
	 * @param graphInstance
	 *            - graph panel instance to be updated on thread.
	 */
	public GraphUpdateThread(Graph graphInstance) {
		this.graphInstance = graphInstance;
	}

	/**
	 * Overriding the run of Thread.java to update the graph at regular intervals.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(DELAY);
				graphInstance.updateGraph();
				ClientCommonData.getInstance().getClientFrame().revalidate();
				ClientCommonData.getInstance().getClientFrame().repaint();

			} catch (InterruptedException e) {
				ClientCommonData.getInstance()
						.logError(LogConstants.GENRICERROR);
			}

		}
	}

}
