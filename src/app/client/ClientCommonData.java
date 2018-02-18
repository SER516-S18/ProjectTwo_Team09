package app.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Manish Tandon This class is responsible for providing data to various
 *         UI elements of the  client application. Eg. Graphs etc.
 *         Follows observer pattern.
 */
public class ClientCommonData extends Observable {
	// data structure for various channels, Format eg.:
	// [[channel_num_1,channel_num_2,...],[channel2_num1,chnnale2_num2...],...]
	private List<ArrayList<Integer>> dataFromServer;
	

	public List<ArrayList<Integer>> getDataFromServer() {
		return dataFromServer;
	}

	public void setDataFromServer(List<ArrayList<Integer>> dataFromServer) {
		this.dataFromServer = dataFromServer;
		setChanged();
	}

}
