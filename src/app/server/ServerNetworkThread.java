package app.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import app.server.ServerController;

/**
 * Handles sending stream of random values to the client in a separate thread.
 * 
 * @author Nelson Tran
 * @author Ganesh Kumar
 * @version 1.0
 */

public class ServerNetworkThread extends Thread {

	private ServerController controller;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private BufferedReader inputStream;
	private PrintStream outputStream;
	private int numberOfChannels;
	private int port;

	/**
	 * Constructs a ServerNetworkThread.
	 * 
	 * @param serverSocket
	 *            The server socket.
	 * @param clientSocket
	 *            The socket that the client connects to.
	 * @param port
	 *            Port number of the network connection.
	 * @param controller
	 *            The server controller.
	 */
	public ServerNetworkThread(ServerSocket serverSocket, Socket clientSocket,
			int port, ServerController controller) {
		this.serverSocket = serverSocket;
		this.clientSocket = clientSocket;
		this.controller = controller;
		this.port = port;
	}

	/*
	 * 
	 * Thread runner function that listens to the client socket for getting the
	 * number of channels After receiving parsing the number of channels, the
	 * function starts writing the stream of computed values to its output
	 * stream
	 * 
	 * @param None
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			// Accept a connection from the client and begin generating
			// and sending values to the server.
			this.serverSocket = new ServerSocket(port);
			this.clientSocket = serverSocket.accept();
			this.clientSocket.setSoTimeout(2);

			// Create input and output streams.
			InputStream socketInputStream = clientSocket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					socketInputStream);
			OutputStream socketOutputStream = clientSocket.getOutputStream();
			this.inputStream = new BufferedReader(inputStreamReader);
			this.outputStream = new PrintStream(socketOutputStream);

			// Repeatedly send values to the client.
			getNumberOfChannels();
			while (true) {
				if (clientSocket.isConnected()) {
					sendValuesToClient();
					sleep(computeFrequencyTime(
							controller.getOptions().frequency));
				} else {
					controller.stopServer();
					break;
				}
			}
		} catch (IOException e) {
			if (!(e.getMessage().equalsIgnoreCase("socket closed"))) {
				ServerException.printErrorMessage(e.toString());
			}
		} catch (InterruptedException e) {
			ServerException.printErrorMessage(e.toString());
		}
	}

	/**
	 * Close the socket connection between the client and the server and any
	 * associated IO streams.
	 * 
	 * @param None
	 */
	public void closeConnection() {
		try {
			serverSocket.close();
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (IOException e) {
			ServerException.printErrorMessage(e.toString());
		}
	}

	/**
	 * Read an integer value from the client that tells the server the number of
	 * channels the client is expecting.
	 */
	private void getNumberOfChannels() {
		try {
			String line = inputStream.readLine();
			this.numberOfChannels = Integer.parseInt(line);
		} catch (IOException e) {
			ServerException.printErrorMessage(e.toString());
		} catch (Exception e) {
			ServerException.printErrorMessage(e.toString());
		}
	}

	/**
	 * Sends a comma-delimited list of numbers to the client. The length of the
	 * list depends on `numberOfChannels`.
	 */
	private void sendValuesToClient() {
		// Generate a comma-delimited string of random integer values.
		// e.g. "14,64,67,"
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.numberOfChannels; i++) {
			builder.append(generateValue());
			builder.append(',');
		}

		// Remove the last comma character.
		// e.g. "14,64,67," -> "14,64,67"
		builder.setLength(builder.length() - 1);

		// Send the string to the client.
		outputStream.println(builder.toString());
	}

	/**
	 * Generate a random integer between the minimum and maximum values stored
	 * in the server options.
	 */
	private int generateValue() {
		int minimum = controller.getOptions().minimumValue;
		int maximum = controller.getOptions().maximumValue;
		return minimum + (int) (Math.random() * (maximum - minimum));
	}

	/**
	 * Compute the period of time associated with a frequency (in Hz) given by F
	 * = 1 / T.
	 */
	private int computeFrequencyTime(int hertz) {
		final int MILLI_IN_SECONDS = 1000;
		return MILLI_IN_SECONDS / hertz;
	}

}
