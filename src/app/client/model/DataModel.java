package app.client.model;

import java.util.ArrayList;

/**
 * It stores the information for the frequency specified 
 * on the UI and the list of the coordinates model for the graph.
 *
 */
public class DataModel {
	
	int frequency;
	ArrayList<CoordinatesModel> coordinates;
	
	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	/**
	 * @return the coordinates
	 */
	public ArrayList<CoordinatesModel> getCoordinates() {
		return coordinates;
	}
	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(ArrayList<CoordinatesModel> coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
