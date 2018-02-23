package app.client.controller;

import app.client.gui.Graph;
import app.client.model.ClientCommonData;
import app.client.model.LogConstants;

public class GraphUpdateThread implements Runnable{
	Graph graphObj;
public GraphUpdateThread(Graph graphObj) {
	// TODO Auto-generated constructor stub
	this.graphObj=graphObj;
	

}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(1000);
				graphObj.updateGraph();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ClientCommonData.getInstance().logError(LogConstants.GENRICERROR);
			}
			
		}
	}

}
