package app.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import app.client.DataStatObserver;
import app.client.gui.CoordinatesModel;
import app.client.model.ClientCommonData;
import app.client.model.LogConstants;


public class ClientSocketConnector implements Runnable {

	private boolean clientStatus = false;

	public boolean isClientStatus() {
		return clientStatus;
	}

	public void setClientStatus(boolean clientStatus) {
		this.clientStatus = clientStatus;
	}

	private int port;
	private String hostName;
	private Socket clientSocket;
	private String channelNumber;
	private BufferedReader inputReader = null;
	private PrintWriter outputStream = null;
	private ArrayList<String> serverData = new ArrayList<String>();

	public ArrayList<String> getServerData() {
		return serverData;
	}

	public void setServerData(ArrayList<String> serverData) {
		this.serverData = serverData;
	}

	public ClientSocketConnector(String hostName, int port, Integer channelNumber) {
		this.port = port;
		this.hostName = hostName;
		this.channelNumber = channelNumber.toString();

	}

	@Override
	public void run() {
		DataStatObserver dataStatObserver=new DataStatObserver();
		ClientCommonData.getInstance().addObserver(dataStatObserver);
		try {
			clientSocket = new Socket(hostName, port);
			inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outputStream = new PrintWriter(this.clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			ClientCommonData.getInstance().logError(LogConstants.CONNECTIONERROR);
			//e.printStackTrace();
		}
		try {
			outputStream.println(channelNumber);
		} catch (Exception e) {

			// TODO Auto-generated catch block
			ClientCommonData.getInstance().logError(LogConstants.GENRICERROR);

			e.printStackTrace();
		}
		clientStatus = true;
		while (clientStatus) {
			String inputLine = null;
			int currentXCoordinate = 0;
			try {
				while ((inputLine = inputReader.readLine()) != null) {
					this.serverData.add(inputLine);
					ArrayList<CoordinatesModel> coordinatesArray = new ArrayList<CoordinatesModel>();
					String[] arrayOfValues = inputLine.split(",");
					int clientFrequency = ClientCommonData.getInstance().getFrequency();
					int frequencyOffset = 1000 / clientFrequency;
					ArrayList<Integer> listOfAllValues= new ArrayList<Integer>();
					for (String eachArrayValue : arrayOfValues) {
						int eachValueFromServer=Integer.parseInt(eachArrayValue);
						listOfAllValues.add(eachValueFromServer);
						ClientCommonData.getInstance().addToListValues(eachValueFromServer);
						CoordinatesModel coordinatesModel = new CoordinatesModel(currentXCoordinate,
								Integer.parseInt(eachArrayValue));
						coordinatesArray.add(coordinatesModel);
						ClientCommonData.getInstance().logInfo("Received: "+coordinatesModel);
					}
					//setListOfAllValues(listOfAllValues);
					currentXCoordinate = currentXCoordinate + frequencyOffset;
					ClientCommonData.getInstance().getDataFromServer().add(coordinatesArray);
					if (!clientStatus) {
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ClientCommonData.getInstance().logError(LogConstants.IOERROR);
				//e.printStackTrace();
			}

		}
		try {
			clientSocket.close();
			ClientCommonData.getInstance().logInfo(LogConstants.SERVERDISCONNECT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ClientCommonData.getInstance().logError(LogConstants.IOERROR);
		}

	}

	public static void main(String[] args) {
		ClientSocketConnector cc = new ClientSocketConnector("localhost", 1516, 3);
		new Thread(cc).start();

	}

	/*
	 * public void startServer() { view.log("Info: The server has been started.");
	 * view.setStatus(true); socketImpl = new ServerSocketImpl(port); t = new
	 * Thread(socketImpl); t.start(); }
	 * 
	 * public void stopServer() { view.log("Info: The server has been stopped.");
	 * view.setStatus(false); t.interrupt(); socketImpl.stopServer(); }
	 * 
	 *//**
		 * Event handler for the Start/Stop button. If the server has not been started,
		 * request for the server options (lowest value, highest value, and frequency)
		 * and start the server. Otherwise, stop the server.
		 *//*
			 * public void toggleButtonClickHandler() { if (!this.status) { try {
			 * this.options = view.getOptions(); this.status = true; startClient(); } catch
			 * (NumberFormatException e) { // This exception is thrown if the text fields do
			 * not // contain valid integer values.
			 * view.log("Error: Invalid server options (must have integer values).");
			 * this.status = false; } catch (Exception e) { view.log("Error: " +
			 * e.getMessage()); this.status = false; } } else { this.status = false;
			 * stopClient(); } }
			 */

	/*
	 * public ArrayList<String> dataSetFromServer; //this needs to be integrated
	 * with frequency from UI private int clientFrequency=5; private int
	 * numberOfChannels;
	 * 
	 * 
	 * Socket clientSocket; private int portNo; private String hostName; private
	 * PrintWriter outputStream; private BufferedReader inputReader; private
	 * ClientDataHandler clientDataHandlerObj;
	 * 
	 * public ClientController(int portNo) { this.portNo = portNo; this.hostName =
	 * "localhost"; this.clientSocket = null; outputStream = null; inputReader =
	 * null; this.dataSetFromServer = new ArrayList<String>();
	 * clientDataHandlerObj=new ClientDataHandler(); } public int
	 * getClientFrequency() { return clientFrequency; }
	 * 
	 * public void setClientFrequency(int clientFrequency) { this.clientFrequency =
	 * clientFrequency; }
	 * 
	 * public int getNumberOfChannels() { return numberOfChannels; }
	 * 
	 * public void setNumberOfChannels(int numberOfChannels) { this.numberOfChannels
	 * = numberOfChannels; } public ArrayList<String> getDataSetFromServer() {
	 * return dataSetFromServer; } public void clearDataInArrayList() {
	 * synchronized(this) { this.dataSetFromServer.clear(); } }
	 * 
	 * private boolean readFromServer() { String inputLine = null; try { while
	 * ((inputLine = this.inputReader.readLine()) != null) {
	 * this.dataSetFromServer.add(inputLine); } return true; } catch (IOException e)
	 * { // Print on console System.err.println("IO Exception: " + hostName); return
	 * false; }
	 * 
	 * }
	 * 
	 * public void startClient(int channelNo) { try { this.clientSocket = new
	 * Socket(hostName, portNo); this.outputStream = new
	 * PrintWriter(this.clientSocket.getOutputStream(), true); this.inputReader =
	 * new BufferedReader(new
	 * InputStreamReader(this.clientSocket.getInputStream()));
	 * this.outputStream.println(channelNo); boolean didReadSuccess = true;
	 * System.out.println("starting clien");
	 * clientDataHandlerObj.setDataSetFromServer(this.dataSetFromServer);
	 * clientDataHandlerObj.setClientControllerObj(this);
	 * 
	 * clientDataHandlerObj.start(); Timer timer = new Timer(); TimerTask myTask =
	 * new TimerTask() {
	 * 
	 * @Override public void run() {
	 * 
	 * } };
	 * 
	 * //timer.schedule(myTask, 1000, 1000); while(didReadSuccess) { didReadSuccess
	 * = this.readFromServer(); }
	 * 
	 * } catch (UnknownHostException e) { // Print on console
	 * System.err.println("Unknown host: " + hostName); } catch (IOException e) { //
	 * Print on console e.printStackTrace(); System.err.println("IO Exception: " +
	 * hostName); }
	 * 
	 * if (this.clientSocket == null || this.outputStream == null ||
	 * this.inputReader == null) { System.err.println("Unknown Error."); return; } }
	 * 
	 * public void stopClient() { try { this.inputReader.close();
	 * this.outputStream.close(); this.clientSocket.close(); } catch (Exception e) {
	 * // Print on console
	 * 
	 * } }
	 * 
	 * public static void main(String... args) { ClientController cont = new
	 * ClientController(1516); cont.startClient(2); while(true) {
	 * cont.readFromServer(); }
	 * 
	 * }
	 */
}
