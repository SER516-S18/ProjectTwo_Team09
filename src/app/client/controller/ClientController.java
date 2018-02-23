package app.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import app.client.gui.ClientGui;

public class ClientController {

	public ArrayList<String> dataSetFromServer;
	Socket clientSocket;
	private int portNo;
	private String hostName;
	private PrintWriter outputStream;
	private BufferedReader inputReader;
	private ClientDataHandler clientDataHandlerObj;
	private ClientGui client;

	public ClientController(int portNo) {
		this.portNo = portNo;
		this.hostName = "localhost";
		this.clientSocket = null;
		outputStream = null;
		inputReader = null;
		this.dataSetFromServer = new ArrayList<String>();
		clientDataHandlerObj=new ClientDataHandler();
	}

	public ArrayList<String> getDataSetFromServer() {
		return dataSetFromServer;
	}
	public void clearDataInArrayList() {
		synchronized(this) {
			this.dataSetFromServer.clear();
		}
	}

	private boolean readFromServer() {
		String inputLine = null;
		try {
			while ((inputLine = this.inputReader.readLine()) != null) {
				this.dataSetFromServer.add(inputLine);
				System.out.println(inputLine);
			}
			return true;
		} catch (IOException e) {
			// Print on console
			System.err.println("IO Exception: " + hostName);
			return false;
		}
		
	}

	public void startClient(int channelNo) {
		try {
			this.clientSocket = new Socket(hostName, portNo);
			this.outputStream = new PrintWriter(this.clientSocket.getOutputStream(), true);
			this.inputReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			this.outputStream.println(channelNo);
			boolean didReadSuccess = true;
			System.out.println("starting clien");
			clientDataHandlerObj.setDataSetFromServer(this.dataSetFromServer);
			
			clientDataHandlerObj.start();
			/*Timer timer = new Timer();
			TimerTask myTask = new TimerTask() {
			    @Override
			    public void run() {
			    		
			    }
			};*/

			//timer.schedule(myTask, 1000, 1000);
			while(didReadSuccess) {
				didReadSuccess = this.readFromServer();
			}
			
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
