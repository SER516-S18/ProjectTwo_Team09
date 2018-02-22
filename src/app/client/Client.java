package app.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{
	
	private static Socket clientSocket;
	private static final String hostname = "localhost";
	private static final int port = 1516;
	
	// This method is for testing only
	public static void main(String[] args) {
		
//		startClient();
	}
	
	/**
	 *  Static method to be invoked on call of start button
	 * @param hostname
	 * @param port
	 */
	public void startClient() {
//		Socket clientSocket = null;
		DataOutputStream os = null;
		BufferedReader is = null;
		try {
			clientSocket = new Socket(hostname, port);
			os = new DataOutputStream(clientSocket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Unknown host: " + hostname);
			ClientCommonData.getInstance().getLogs().add("Unknown host: " + hostname);
			
		} catch (IOException e) {
			System.err.println("Connection Exception " + hostname);
			ClientCommonData.getInstance().getLogs().add("Connection Exception " + hostname);
		}

		if (clientSocket == null || os == null || is == null) {
			System.err.println("Unknown Error.");
			ClientCommonData.getInstance().getLogs().add("Unknown Error.");
			return;
		}

		try {
			
			int freq = ClientCommonData.getInstance().getFrequency();
			@SuppressWarnings("unused")
			int channel = ClientCommonData.getInstance().getChannels();
			os.writeBytes(freq + "\n");

			while (true) {
				if (freq == 0 || freq == -1) {
					break;
				}

				String responseLine = is.readLine();
				ClientCommonData.getInstance().getLogs().add("Data Received: "+responseLine);
				
				System.out.println("Data Received: "+responseLine);
			}

			os.close();
			is.close();
			clientSocket.close();
		} catch (UnknownHostException e) {
			System.err.println("Trying to connect to unknown host: " + e);
			ClientCommonData.getInstance().getLogs().add("Trying to connect to unknown host: " + e);
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
			ClientCommonData.getInstance().getLogs().add("Trying to connect to unknown host: " + e);
		}
	}
	
	/**
	 * method which closes the connection
	 * @throws IOException
	 */
	public void stopClient() {
		try {
			if(clientSocket != null) {
				clientSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ClientCommonData.getInstance().getLogs().add("Exception while stopping client::::" + e);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.startClient();
	}
}
