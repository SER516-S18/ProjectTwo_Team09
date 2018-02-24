package app.server;

import app.server.ServerController;
import app.server.ServerView;

public class Server {

    /**
     * Define the network connection port according to the port
     * number defined in the PowerPoint slides.
     * 
     * @author Nelson Tran
     * @version 1.0
     */
    private static int PORT = 1516;

    /**
     * Main entry point for the server.
     */
    public static void main(String... args) {
        ServerView view = new ServerView();
        ServerController controller = new ServerController(PORT, view);
        view.setController(controller);
    }

}
