package app.server;

import java.net.ServerSocket;

import app.server.ServerView;

public class ServerController {

    private ServerOptions options;
    private ServerView view;
    private ServerSocket listener;
    private boolean status;
    private int port;

    public ServerController(int port, ServerView view) {
        this.status = false;
        this.port = port;
        this.view = view;
    }

    public void startServer() {
        view.log("The server has been started...");
    }

    public void stopServer() {
        view.log("The server has been stopped...");
    }

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
        }
        else {
            this.status = false;
            stopServer();
        }
    }

}