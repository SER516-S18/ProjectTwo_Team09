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

public class ServerNetworkThread extends Thread {

	private ServerController controller;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader inputStream;
    private PrintStream outputStream;
    private int numberOfChannels;
    private int port;

    public ServerNetworkThread(ServerSocket serverSocket, Socket clientSocket, int port, ServerController controller) {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
        this.controller = controller;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            // Accept a connection from the client and begin generating
            // and sending values to the server.
            this.clientSocket = serverSocket.accept();
            this.clientSocket.setSoTimeout(2);
            System.out.println("Info: The client has connected to the server.");
            
            // Create input and output streams.
            InputStream socketInputStream = clientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(socketInputStream);
            OutputStream socketOutputStream = clientSocket.getOutputStream();
            this.inputStream = new BufferedReader(inputStreamReader);
            this.outputStream = new PrintStream(socketOutputStream);

            getNumberOfChannels();
            System.out.println("Number of channels received");
            while (true) {
                if (clientSocket.isConnected()) {
                    sendValuesToClient();
                    sleep(computeFrequencyTime(controller.getOptions().frequency));
                }
                else {
                    controller.stopServer();
                    break;
                }
            }
        }
        catch (IOException e) {
            // TODO: Log this error in the console.
        	if(e.getMessage().equals("socket closed")) {
        		new ServerException("Info: "+e.getMessage());
        	}else {
        		new ServerException(e.toString());
        	}
        }
        catch (InterruptedException e) {
            // TODO: Log this error in the console.
            new ServerException(e.toString());
        }
    }

    private void getNumberOfChannels() {
        try {
            String line = inputStream.readLine();
            this.numberOfChannels = Integer.parseInt(line);
        }
        catch (IOException e) {
            // TODO: Log this error in the console.
            new ServerException(e.toString());
        }
        catch (Exception e) {
            // TODO: Log this error in the console.
            new ServerException(e.toString());
        }
    }

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
        System.out.println(builder.toString());
    }

    /**
     * Generate a random integer between the minimum and maximum values
     * stored in the server options.
     */
    private int generateValue() {
        int minimum = controller.getOptions().minimumValue;
        int maximum = controller.getOptions().maximumValue;
        return minimum + (int)(Math.random() * (maximum - minimum));
    }

    /**
     * Compute the period of time associated with a frequency (in Hz)
     * given by F = 1 / T.
     */
    private int computeFrequencyTime(int hertz) {
        final int MILLI_IN_SECONDS = 1000;
        return MILLI_IN_SECONDS / hertz;
    }

}
