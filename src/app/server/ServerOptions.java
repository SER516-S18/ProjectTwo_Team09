package app.server;

/**
 * Common class to set the data values like 
 * minimum value, maximum value and frequency
 * 
 * @author Nelson Tran
 * @version 1.0
 * @since 2018-02-23
 * 
 */
public class ServerOptions {

	public final int minimumValue;
	public final int maximumValue;
	public final int frequency;

	/**
	 * Sets the server options - max value, min value and frequency *
	 * @param minimumValue - maximum value set at the server
	 * @param maximumValue - minimum value set at the server
	 * @param frequency - frequency at which data is sent
	 *
	 */
	public ServerOptions(int minimumValue, int maximumValue, int frequency) {
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.frequency = frequency;
	}

}
