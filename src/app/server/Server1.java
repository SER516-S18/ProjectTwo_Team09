package app.server;

import java.io.*;
import java.net.*;
import java.util.HashMap;


public class Server1 implements Runnable{
//	public static void main(String args[]) {
//		int port = 6789;
//		
////		Server1 obj = new Server1(port);
////		Thread t1 = new Thread(obj);
////		t1.start();
//		
//	}
	static HashMap<String, ServerSocket> serverMap = new HashMap<String, ServerSocket>();
	static HashMap<String, PrintStream> osMap = new HashMap<String, PrintStream>();


	ServerSocket echoServer = null;
	Socket clientSocket = null;
	int port;

	public Server1( int port ) {
		this.port = port;
	}

	public void stopServer() {
		System.out.println( "Server cleaning up." );
		System.exit(0);
	}
	
	public void tempStop() {
		try {
			ServerSocket soc = serverMap.get("os");
			soc.close();
			PrintStream os = osMap.get("os");
			os.close();
			System.out.println( "Server is closing" );

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startServer() {

		try {
			echoServer = new ServerSocket(port);
			Server1.serverMap.put("os", echoServer);

		}
		catch (IOException e) {
			System.out.println(e);
		}   

		System.out.println( "Server is ready..." );


		while ( true ) {
			try {
				clientSocket = echoServer.accept();
				Server1Connection oneconnection = new Server1Connection(clientSocket, this);
				oneconnection.run();
				Thread.sleep(2000);
			}   
			catch (IOException e) {
				//System.out.println(e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Server1 server = new Server1( port );
		server.startServer();
	}
}

class Server1Connection {
	BufferedReader is;
	PrintStream os;
	Socket clientSocket;
	Server1 server;

	public Server1Connection(Socket clientSocket, Server1 server) {
		this.clientSocket = clientSocket;
		this.server = server;
		System.out.println( "Connection established with: " + clientSocket );
		try {
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
			Server1.osMap.put("os", os);
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void stopPrintStream() {
		os.close();
		System.out.println("Printstream is closed");
	}

	public void run() {
		String line;
		try {
			boolean serverStop = false;
			line = is.readLine();
			System.out.println( "Received " + line );
			int n = Integer.parseInt(line);

			int min = 0, max =1024;
			while (true) {

				if ( n == -1 ) {
					serverStop = true;
					break;
				}

				if ( n == 0 ) break;
				StringBuilder builder = new StringBuilder();
				for(int i = 0; i < 3; i++) {
					builder.append(min + (int)(Math.random()*(max - min))).append(",").append((min + (int)(Math.random()*(max - min)))).append(";");        	
					Thread.sleep(10);	
				}
				builder.setLength(builder.length() - 1);
				os.println(builder.toString());
			}

			System.out.println( "Connection closed." );
			is.close();
			os.close();
			clientSocket.close();

			if ( serverStop ) server.stopServer();
		} catch (IOException e) {
			System.out.println(e);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
