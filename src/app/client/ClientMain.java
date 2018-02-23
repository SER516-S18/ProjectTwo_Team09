package app.client;

public class ClientMain {
	
	public static int PORT = 1516;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientGui client = new ClientGui();
		client.setPortNo(PORT);
	}

}
