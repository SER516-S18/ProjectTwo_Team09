package app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientController {

	public ArrayList<String> dataSetFromServer;
	
	Socket clientSocket;
	private int portNo;
	private String hostName;
	private int channelCount;
	private PrintWriter outputStream;
	private BufferedReader inputReader;

	public ClientController(int portNo) {
		this.portNo = portNo;
		this.channelCount = 0;
		this.hostName = "localhost";
		this.clientSocket = null;
		outputStream = null;
		inputReader = null;
	}


	// x1,y1;x2,y2;...number of channels
	private void readFromServer() {
		String inputLine = null;
		try {
			while ((inputLine = this.inputReader.readLine()) != null) {
				
				//TODO: Now have to convert it to DataModel. will do after confirmed data style from server
				
				System.out.println(inputLine);
				if (inputLine.indexOf(";") >= 0 && this.channelCount > 0) {
					String[] parts = inputLine.split(";");
					
				}
			}
		} catch (IOException e) {
			// Print on console
			System.err.println("IO Exception: " + hostName);
		}
		
	}

	public void startClient(int channelNo) {
		try {
			this.clientSocket = new Socket(hostName, portNo);
			this.outputStream = new PrintWriter(this.clientSocket.getOutputStream(), true);
			this.inputReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			this.readFromServer();
		} catch (UnknownHostException e) {
			// Print on console
			System.err.println("Unknown host: " + hostName);
		} catch (IOException e) {
			// Print on console
			e.printStackTrace();
			System.err.println("IO Exception: " + hostName);
		}

		if (this.clientSocket == null || this.outputStream == null || this.inputReader == null) {
			System.err.println("Unknown Error.");
			return;
		}
	}

	public void stopClient() {
		try {
			this.inputReader.close();
			this.outputStream.close();
			this.clientSocket.close();
		} catch (Exception e) {
			// Print on console

		}
	}

	public static void main(String... args) {
		ClientController cont = new ClientController(1516);
		cont.startClient(2);
		while(true) {
			cont.readFromServer();
		}
		
	}

}
