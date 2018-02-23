package app.client;

import app.client.gui.ClientView;
import app.client.model.ClientCommonData;

public class ClientMain {

	public static int PORT = 1516;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientView client = new ClientView();
		ClientCommonData.getInstance().setParentFrame(client);
	}
}