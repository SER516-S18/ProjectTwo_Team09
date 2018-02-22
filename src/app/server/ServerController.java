package app.server;

import java.util.HashMap;

import app.server.ServerView;

public class ServerController {

    private ServerOptions options;
    private ServerView view;
    private boolean status;
    private int port;
    HashMap<String, Thread> threadMap = new HashMap<String, Thread>();
    static  ServerSocketImpl socketImpl = null;
    static Thread t = null;

    public ServerController(int port, ServerView view) {
        this.status = false;
        this.port = port;
        this.view = view;
    }

    public void startServer() {
        view.log("Info: The server has been started.");
        view.setStatus(true);
        socketImpl = new ServerSocketImpl(port);
        t = new Thread(socketImpl);
		t.start();
    }

    public void stopServer() {
        view.log("Info: The server has been stopped.");
        view.setStatus(false);
        t.interrupt();
        socketImpl.stopServer();
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

    /**
     * Generate a random integer between min and max, inclusive.
     */
    private int generateValue() {
        return options.minimumValue + (int)(Math.random() * (options.maximumValue - options.minimumValue));
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
