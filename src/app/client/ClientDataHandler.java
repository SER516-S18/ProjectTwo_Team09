package app.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ClientDataHandler extends Thread {
	private ArrayList<String> dataSetFromServer;
	private int numberOfChannels;

	public int getNumberOfChannels() {
		return numberOfChannels;
	}

	public void setNumberOfChannels(int numberOfChannels) {
		this.numberOfChannels = numberOfChannels;
	}

	public int getClientFrequency() {
		return clientFrequency;
	}

	public void setClientFrequency(int clientFrequency) {
		this.clientFrequency = clientFrequency;
	}

	private int clientFrequency;

	public ArrayList<String> getDataSetFromServer() {
		return dataSetFromServer;
	}

	public void setDataSetFromServer(ArrayList<String> dataSetFromServer) {
		this.dataSetFromServer = dataSetFromServer;
	}

	List<ArrayList<CoordinatesModel>> accumulateDataFromServer;
	ClientCommonData clientCommonData = ClientCommonData.getInstance();

	public void run() {
		try {
			
			
			Timer timer = new Timer();
			TimerTask myTask = new TimerTask() {
			    @Override
			    public void run() {
			    	
			    	for(String eachString:dataSetFromServer) {
			    		ArrayList<CoordinatesModel> coordinatesArray=new ArrayList<CoordinatesModel>();
			    		String[] arrayOfValues= eachString.split(",");
			    		int frequencyOffset=1000/clientFrequency;
			    		int currentYCoordinate=0;
			    		for(String eachArrayValue:arrayOfValues) {
			    			coordinatesArray.add(new CoordinatesModel(currentYCoordinate, Integer.parseInt(eachArrayValue)));
			    			currentYCoordinate=currentYCoordinate+frequencyOffset;
			    		}
			    		
			    		accumulateDataFromServer.add(coordinatesArray);
					}
			    	clientCommonData.setDataFromServer(accumulateDataFromServer);
			    		
			    }
			   
			    
				
			};
			timer.schedule(myTask, 1000, 1000);
			
			//System.out.println(dataSetFromServer[]);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
