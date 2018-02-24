package app.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * This class is responsible for providing data to various UI elements of the
 * client application. Eg. Graphs etc. Follows observer pattern.
 * 
 * @author Manish Tandon, Varun Srivastava
 * 
 * @version 1.0
 * @since February, 2018
 */
public class ClientCommonData extends Observable {

	private List<ArrayList<CoordinatesModel>> dataFromServer;
	private ArrayList<Integer> listOfAllValues;

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
		logs = new ArrayList<String>();
		dataFromServer = new ArrayList<ArrayList<CoordinatesModel>>();
		listOfAllValues = new ArrayList<Integer>();
	}

	/**
	 * Creates a singleton instance of the clientCommonData file If exists,
	 * returns it, else creates it.
	 * 
	 * @return instance of the ClientCommonData
	 */
	public static ClientCommonData getInstance() {
		if (instance == null) {
			instance = new ClientCommonData();
		}
		return instance;
	}

	/**
	 * Returns the data from the server in the form of list of a list containing
	 * coordinate information for the graph.
	 * 
	 * @return data from server
	 */
	public List<ArrayList<CoordinatesModel>> getDataFromServer() {
		return dataFromServer;
	}

	/**
	 * Sets the coordinate data.
	 * 
	 * @param dataFromServer
	 *            - list of a list containing coordinate information for the
	 *            graph.
	 */
	public void setDataFromServer(
			List<ArrayList<CoordinatesModel>> dataFromServer) {
		this.dataFromServer = dataFromServer;
	}

	/**
	 * Returns a list of numbers from server.
	 * 
	 * @return list of numbers from server
	 */
	public ArrayList<Integer> getListOfAllValues() {
		return listOfAllValues;
	}

	/**
	 * Sets the list of values received from the server
	 * 
	 * @param listOfAllValues
	 *            - listOfAllValues to set for calculating stats.
	 */
	public void setListOfAllValues(ArrayList<Integer> listOfAllValues) {
		this.listOfAllValues = listOfAllValues;
	}

	/**
	 * Gets the logs to be printed on the console.
	 * 
	 * @return logs - list of String values
	 */
	public List<String> getLogs() {
		return logs;
	}

	/**
	 * Sets the logs to that need to be displayed on the console
	 * 
	 * @param logs
	 *            - list of String values
	 */
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}

	/**
	 * Returns the frequency to be used at client side.
	 * 
	 * @return frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Sets the frequency to be used at client side.
	 * 
	 * @param frequency
	 *            - the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Returns the no of channels set by the user.
	 * 
	 * @return no of channels
	 */
	public int getChannels() {
		return channels;
	}

	/**
	 * Sets the no of channels set by the user.
	 * 
	 * @param channels
	 *            - the channels to set
	 */
	public void setChannels(int channels) {
		this.channels = channels;
	}

	/**
	 * 
	 * @return the clientFrame
	 */
	public JFrame getClientFrame() {
		return clientFrame;
	}

	/**
	 * @param clientFrame
	 *            the clientFrame to set
	 */
	public void setClientFrame(JFrame clientFrame) {
		this.clientFrame = clientFrame;
	}

	/**
	 * @return the started
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * @param started
	 *            the started to set
	 */
	public void setStarted(boolean started) {
		this.started = started;
	}

	/**
	 * @return the consoleArea
	 */
	public JTextArea getConsoleArea() {
		return consoleArea;
	}

	/**
	 * @param consoleArea
	 *            the consoleArea to set
	 */
	public void setConsoleArea(JTextArea consoleArea) {
		this.consoleArea = consoleArea;
	}

	/**
	 * @return the minField
	 */
	public JTextField getMinField() {
		return minField;
	}

	/**
	 * @param minField
	 *            the minField to set
	 */
	public void setMinField(JTextField minField) {
		this.minField = minField;
	}

	/**
	 * @return the maxField
	 */
	public JTextField getMaxField() {
		return maxField;
	}

	/**
	 * @param maxField
	 *            the maxField to set
	 */
	public void setMaxField(JTextField maxField) {
		this.maxField = maxField;
	}

	/**
	 * @return the averageValue
	 */
	public JTextField getAverageValue() {
		return averageValue;
	}

	/**
	 * @param averageValue
	 *            the averageValue to set
	 */
	public void setAverageValue(JTextField averageValue) {
		this.averageValue = averageValue;
	}

	/**
	 * Common method used to log to display in the console.
	 * 
	 * @param logs
	 *            - the log string
	 */
	public void logInfo(String logs) {
		consoleArea.append("INFO: " + logs + "\n");
	}

	/**
	 * Common method used to log ghe errors in the console.
	 * 
	 * @param logs
	 *            - the error string
	 */
	public void logError(String logs) {
		consoleArea.append("Error: " + logs + "\n");
	}

	/**
	 * Common method to update the value of average value when updated.
	 * 
	 * @param averageVal
	 *            - the new average value
	 */
	public void updateAverage(Float averageVal) {
		this.averageValue.setText(String.format("%.2f", averageVal));
	}

	/**
	 * Common method to update the max value when its value is updated
	 * 
	 * @param maxValue
	 *            - the new max value
	 */
	public void updateMaxValue(Integer maxValue) {
		this.maxField.setText(maxValue.toString());
	}

	/**
	 * Common method to update the minimum value received so far
	 * 
	 * @param minValue-
	 *            the new minimum value
	 */
	public void updateMinvalue(Integer minValue) {
		this.minField.setText(minValue.toString());
	}

	/**
	 * On receiving of new data from server, this method inserts them to the
	 * common list and notifies the observers
	 * 
	 * @param valueToBeAdded
	 *            - the new value received from server.
	 */
	public void addToListValues(Integer valueToBeAdded) {
		this.listOfAllValues.add(valueToBeAdded);
		setChanged();
		this.notifyObservers();
	}

}
