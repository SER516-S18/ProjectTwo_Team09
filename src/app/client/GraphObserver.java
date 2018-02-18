package app.client;

import java.util.Observable;
import java.util.Observer;

public class GraphObserver implements Observer{

	@Override
	public void update(Observable arg0, Object arg1) {
		// update the graph
		
		//use this data to populate the graph
		//data is provided in the form of list of list, see client common data
		ClientCommonData data=(ClientCommonData) arg0;
		System.out.println("incoming data");
		
	}

}
