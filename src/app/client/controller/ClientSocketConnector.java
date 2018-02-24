package app.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import app.client.DataStatObserver;
import app.client.model.ClientCommonData;
import app.client.model.CoordinatesModel;
import app.client.model.LogConstants;

/**
 * This class extends Runnable and connects the client side to the socket with
 * the given PortNo and hostname. It also reads and writes to the socket.
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
	 * Class constructor
	 * 
	 * @param hostname: Pass the hostname to which you wish to connect to the
	 * server.
	 * @param port: Pass the port number to establish socket connection.
	 * @param channelNumber: The number of channels for which the data is needed
	 * from the socket server
	 * 
	 */

	public ClientSocketConnector(String hostName, int port, Integer channelNumber) {
		this.port = port;
		this.hostName = hostName;
		this.channelNumber = channelNumber.toString();

	}

	
	/**
	 * @return the clientStatus
	 */
	public boolean isClientStatus() {
		return clientStatus;
	}


	/**
	 * @param clientStatus the clientStatus to set
	 */
	public void setClientStatus(boolean clientStatus) {
		this.clientStatus = clientStatus;
	}

	/**
	 * @return the serverData
	 */
	public ArrayList<String> getServerData() {
		return serverData;
	}

	/**
	 * @param serverData the serverData to set
	 */
	public void setServerData(ArrayList<String> serverData) {
		this.serverData = serverData;
	}

	/**
	 * @see 
	 */

	@Override
	public void run() {
		DataStatObserver dataStatObserver = new DataStatObserver();
		ClientCommonData.getInstance().addObserver(dataStatObserver);
		try {
			clientSocket = new Socket(hostName, port);
			inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outputStream = new PrintWriter(this.clientSocket.getOutputStream(), true);
			outputStream.println(channelNumber);
		} catch (IOException e) {
			ClientCommonData.getInstance().logError(LogConstants.CONNECTIONERROR);
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
					int clientFrequency = ClientCommonData.getInstance().getFrequency();
					int frequencyOffset = 1000 / clientFrequency;
					ArrayList<Integer> listOfAllValues = new ArrayList<Integer>();
					for (String eachArrayValue : arrayOfValues) {
						int eachValueFromServer = Integer.parseInt(eachArrayValue);
						listOfAllValues.add(eachValueFromServer);
						ClientCommonData.getInstance().addToListValues(eachValueFromServer);
						CoordinatesModel coordinatesModel = new CoordinatesModel(currentXCoordinate,
								Integer.parseInt(eachArrayValue));
						coordinatesArray.add(coordinatesModel);
						ClientCommonData.getInstance().logInfo("Received: " + coordinatesModel);
					}
					currentXCoordinate = currentXCoordinate + frequencyOffset;
					ClientCommonData.getInstance().getDataFromServer().add(coordinatesArray);
					if (!clientStatus) {
						break;
					}
				}
				ClientCommonData.getInstance().logError(LogConstants.SERVER_CONN_FAIL);
				ClientCommonData.getInstance().setStarted(false);

			} catch (IOException e) {
				ClientCommonData.getInstance().logError(LogConstants.IOERROR);
			}

		}
		try {
			clientSocket.close();
			ClientCommonData.getInstance().logInfo(LogConstants.SERVERDISCONNECT);
		} catch (IOException e) {
			ClientCommonData.getInstance().logError(LogConstants.IOERROR);
		}

	}

}
