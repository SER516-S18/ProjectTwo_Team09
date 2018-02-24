package app.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.client.gui.ClientView;

/**
 * @author Manish Tandon This class is responsible for providing data to various
 *         UI elements of the client application. Eg. Graphs etc. Follows
 *         observer pattern.
 */
@SuppressWarnings("deprecation")
public class ClientCommonData extends Observable {
	// data structure for various channels, Format eg.:
	// [[channel_num_1,channel_num_2,...],[channel2_num1,chnnale2_num2...],...]

	private List<ArrayList<CoordinatesModel>> dataFromServer;
	private ArrayList<Integer> listOfAllValues;

	public ArrayList<Integer> getListOfAllValues() {
		return listOfAllValues;
	}

	public void setListOfAllValues(ArrayList<Integer> listOfAllValues) {
		this.listOfAllValues = listOfAllValues;

	}

	private List<String> logs;
	private int frequency;
	private int channels;
	private JFrame clientFrame;
	private boolean started;

	private JTextArea consoleArea;
	private JTextField minField;

	private JTextField maxField;
	private JTextField averageValue;

	private static ClientCommonData instance;

	private ClientCommonData() {
		// so only one instance of this object exists.
		logs = new ArrayList<String>();
		dataFromServer = new ArrayList<ArrayList<CoordinatesModel>>();
		listOfAllValues = new ArrayList<Integer>();
	}

	public static ClientCommonData getInstance() {
		if (instance == null) {
			instance = new ClientCommonData();
		}
		return instance;
	}

	public void setDataFromServer(List<ArrayList<CoordinatesModel>> dataFromServer) {
		this.dataFromServer = dataFromServer;
	}

	public List<ArrayList<CoordinatesModel>> getDataFromServer() {
		return dataFromServer;
	}

	public List<String> getLogs() {
		return logs;
	}

	public int getFrequency() {
		return frequency;
	}

	public int getChannels() {
		return channels;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void setChannels(int channels) {
		this.channels = channels;
	}

	public void setParentFrame(ClientView client) {
		this.clientFrame = client;

	}

	public JFrame getParentFrame() {
		return clientFrame;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public JTextArea getConsoleArea() {
		return consoleArea;
	}

	public void setConsoleArea(JTextArea consoleArea) {
		this.consoleArea = consoleArea;
	}

	public void logInfo(String logs) {
		consoleArea.append("INFO: " + logs + "\n");
	}

	public void logError(String logs) {
		consoleArea.append("Error: " + logs + "\n");
	}

	public JTextField getMinField() {
		return minField;
	}

	public void setMinField(JTextField minField) {
		this.minField = minField;
	}

	public JTextField getMaxField() {
		return maxField;
	}

	public void setMaxField(JTextField maxField) {
		this.maxField = maxField;
	}

	public JTextField getAverageValue() {
		return averageValue;
	}

	public void setAverageValue(JTextField averageValue) {
		this.averageValue = averageValue;
	}

	public void updateAverage(Float averageVal) {
		this.averageValue.setText(String.format("%.2f", averageVal));
	}

	public void updateMaxValue(Integer maxValue) {
		this.maxField.setText(maxValue.toString());
	}

	public void updateMinvalue(Integer minValue) {
		this.minField.setText(minValue.toString());
	}

	public void addToListValues(Integer valueToBeAdded) {
		this.listOfAllValues.add(valueToBeAdded);
		setChanged();
		this.notifyObservers();
	}

}
