package app.server;

public class ServerOptions {

	public final int minimumValue;
	public final int maximumValue;
	public final int frequency;

	public ServerOptions(int minimumValue, int maximumValue, int frequency) {
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.frequency = frequency;
	}

}