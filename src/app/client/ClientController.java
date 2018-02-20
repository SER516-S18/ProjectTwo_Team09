package app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientController {
	
	Socket clientSocket;
	private int portNo;
	private String hostName;
	private int channelCount;
	private boolean statusOfClientSocket;
	private PrintWriter outputStream;
	private BufferedReader inputReader;
	
	public ClientController(int portNo) {
		this.portNo = portNo;
		this.channelCount = 0;
		this.hostName = "localhost";
		statusOfClientSocket = false;
		this.clientSocket = null;
		outputStream = null;
		inputReader = null;
	}
	
	
	public void toggleStartOrStopServer() {
		if(this.statusOfClientSocket) {
			this.startClient();
		} else {
			this.stopClient();
		}
	}
	
	public void updateChannelCount(int channelCount) {
		this.outputStream.println(channelCount);
		this.channelCount = channelCount;
        //String resp = in.readLine();
	}
	
	//x1,y1;x2,y2;...number of channels
	public String[] readFromServer() {
		String inputLine = null;
		try {
			while ((inputLine = this.inputReader.readLine()) != null) {
				if (inputLine.indexOf(";") >=0 && this.channelCount > 0) {
					String[] parts = inputLine.split(";");
					return parts;
				}
			}
		} catch (IOException e) {
			//Print on console
			System.err.println("IO Exception: " + hostName);
		}
		return null;
	}
	
	
	private void startClient() {
		try {
			this.clientSocket = new Socket(hostName, portNo);
			this.outputStream = new PrintWriter(this.clientSocket.getOutputStream(), true);
	        this.inputReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			this.statusOfClientSocket = true;
		} catch (UnknownHostException e) {
			//Print on console
			System.err.println("Unknown host: " + hostName);
		} catch (IOException e) {
			//Print on console
			e.printStackTrace();
			System.err.println("IO Exception: " + hostName);
		}
		
		if (this.clientSocket == null || this.outputStream == null || this.inputReader == null) {
			System.err.println( "Unknown Error." );
			return;
		}
	}
	
	private void stopClient() {
		try {
			this.inputReader.close();
			this.outputStream.close();
			this.clientSocket.close();
			this.statusOfClientSocket = false;
		} catch (Exception e) {
			//Print on console
			
		}
	}
	
	public static void main(String... args) {
		ClientController cont = new ClientController(1516);
		cont.startClient();
		cont.updateChannelCount(2);
	}
	
	
}
