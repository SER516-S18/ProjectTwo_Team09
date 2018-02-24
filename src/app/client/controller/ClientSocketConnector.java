package app.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import app.client.model.ClientCommonData;
import app.client.model.CoordinatesModel;
import app.client.model.LogConstants;

/**
 * This class extends Runnable and connects the client side to the socket with
 * the given PortNo and hostname. It also reads and writes to the socket.
 * 
 * @author Manish Tandon
 * @version 1.0
 * @since February, 2018
 * 
 */
public class ClientSocketConnector implements Runnable {

	private boolean clientStatus = false;
	private int port;
	private String hostName;
	private Socket clientSocket;
	private String channelNumber;
	private BufferedReader inputReader = null;
	private PrintWriter outputStream = null;
	private ArrayList<String> serverData = new ArrayList<String>();

	/**
	 * This constructor initializes the port, host name and number of channels
	 * to connect to the server.
	 * 
	 * @param hostname
	 *            - Host name to which you wish to connect to the server.
	 * @param port
	 *            - Port number to establish socket connection.
	 * @param channelNumber
	 *            - The number of channels for which the data is needed from the
	 *            socket server
	 * 
	 */
	public ClientSocketConnector(String hostName, int port,
			Integer channelNumber) {
		this.port = port;
		this.hostName = hostName;
		this.channelNumber = channelNumber.toString();

	}

	/**
	 * Returns the client status.
	 * 
	 * @return client status - True if running, False if not running
	 */
	public boolean isClientStatus() {
		return clientStatus;
	}

	/**
	 * Sets the client status
	 * 
	 * @param clientStatus
	 *            - True if running, False if not running
	 */
	public void setClientStatus(boolean clientStatus) {
		this.clientStatus = clientStatus;
	}

	/**
	 * Returns the data from the server in an list consisting of Strings.
	 * 
	 * @return serverData - ArrayList of Strings
	 */
	public ArrayList<String> getServerData() {
		return serverData;
	}

	/**
	 * Sets the data from server.
	 * 
	 * @param serverData
	 *            - ArrayList of Strings
	 */
	public void setServerData(ArrayList<String> serverData) {
		this.serverData = serverData;
	}

	/**
	 * Overriding the run method of Thread.java to create a connection with
	 * server.
	 * 
	 */
	@Override
	public void run() {
		DataStatObserver dataStatObserver = new DataStatObserver();
		ClientCommonData.getInstance().addObserver(dataStatObserver);
		try {
			clientSocket = new Socket(hostName, port);
			inputReader = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			outputStream = new PrintWriter(this.clientSocket.getOutputStream(),
					true);
			outputStream.println(channelNumber);
		} catch (IOException e) {
			ClientCommonData.getInstance()
					.logError(LogConstants.CONNECTIONERROR);
		} catch (Exception e) {
			ClientCommonData.getInstance().logError(LogConstants.GENRICERROR);
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
					int clientFrequency = ClientCommonData.getInstance()
							.getFrequency();
					int frequencyOffset = 1000 / clientFrequency;
					ArrayList<Integer> listOfAllValues = new ArrayList<Integer>();
					for (String eachArrayValue : arrayOfValues) {
						int eachValueFromServer = Integer
								.parseInt(eachArrayValue);
						listOfAllValues.add(eachValueFromServer);
						ClientCommonData.getInstance()
								.addToListValues(eachValueFromServer);
						CoordinatesModel coordinatesModel = new CoordinatesModel(
								currentXCoordinate,
								Integer.parseInt(eachArrayValue));
						coordinatesArray.add(coordinatesModel);
					}
					currentXCoordinate = currentXCoordinate + frequencyOffset;
					ClientCommonData.getInstance().getDataFromServer()
							.add(coordinatesArray);
					if (!clientStatus) {
						break;
					}
				}
				ClientCommonData.getInstance().setStarted(false);

			} catch (IOException e) {
				ClientCommonData.getInstance().logError(LogConstants.IOERROR);
			}
		}
		try {
			clientSocket.close();
			ClientCommonData.getInstance()
					.logInfo(LogConstants.SERVERDISCONNECT);
		} catch (IOException e) {
			ClientCommonData.getInstance().logError(LogConstants.IOERROR);
		}

	}

}
