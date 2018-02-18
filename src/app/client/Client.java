package app.client;

import java.io.*;
import java.net.*; 


public class Client {
	public static void main(String[] args) {

		String hostname = "localhost";
		int port = 6789;

		Socket clientSocket = null;  
		DataOutputStream os = null;
		BufferedReader is = null;

		try {
			clientSocket = new Socket(hostname, port);
			os = new DataOutputStream(clientSocket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Unknown host: " + hostname);
		} catch (IOException e) {
			System.err.println("Connection Exception " + hostname);
		}

		if (clientSocket == null || os == null || is == null) {
			System.err.println( "Unknown Error." );
			return;
		}

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.print( "Enter 5 to start sending values: " );
			String userInp = br.readLine();

			os.writeBytes( userInp + "\n" );
			int n = Integer.parseInt( userInp );

			while ( true ) {


				if ( n == 0 || n == -1 ) {
					break;
				}

				String responseLine = is.readLine();
				System.out.println(responseLine);
			}

			os.close();
			is.close();
			clientSocket.close();   
		} catch (UnknownHostException e) {
			System.err.println("Trying to connect to unknown host: " + e);
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		}
	}           
}

