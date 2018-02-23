package app.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import app.server.ServerNetworkThread;
import app.server.ServerView;

public class ServerController {

    private ServerNetworkThread networkThread;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ServerOptions options;
    private ServerView view;
    private boolean status;
    private int port;

    public ServerController(int port, ServerView view) {
        this.status = false;
        this.port = port;
        this.view = view;
    }

    public void startServer() {
        // Update the view.
        view.log("Info: The server has been started.");
        view.setStatus(true);
        
        this.networkThread = new ServerNetworkThread(this.serverSocket, this.clientSocket, this.port, this);
        this.networkThread.start();
    }

    public void stopServer() {
        // Update the view.
        view.log("Info: The server has been stopped.");
        view.setStatus(false);
        
        try {
            if (networkThread != null) {
                networkThread.interrupt();
            }
    
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
        catch (IOException e) {
            // TODO: Log this error in the console.
            e.printStackTrace();
        }
    }

    /**
     * Event handler for the Start/Stop button. If the server has not been started,
     * request for the server options (lowest value, highest value, and frequency)
     * and start the server. Otherwise, stop the server.
     */
    public void toggleButtonClickHandler() {
        if (!this.status) {
            try {
                this.options = view.getOptions();
                this.status = true;
                startServer();
            }
            catch (NumberFormatException e) {
                // This exception is thrown if the text fields do not
                // contain valid integer values.
                view.log("Error: Invalid server options (must have integer values).");
                this.status = false;
            }
            catch (Exception e) {
                view.log("Error: " + e.getMessage());
                this.status = false;
            }
        }
        else {
            this.status = false;
            stopServer();
        }
    }

    public ServerOptions getOptions() {
        return this.options;
    }

}
