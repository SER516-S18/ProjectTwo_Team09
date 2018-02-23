package app.client.model;

import java.util.ArrayList;

import app.client.gui.CoordinatesModel;

public class DataModel {
	
	int frequency;
	ArrayList<CoordinatesModel> coordinates;
	
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public ArrayList<CoordinatesModel> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(ArrayList<CoordinatesModel> coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
