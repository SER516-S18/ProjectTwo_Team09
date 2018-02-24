
package app.client.model;

/**
 *	Model for storing the calculated minimum, 
 *maximum and average values sent from the server for 
 *displaying on the UI
 */
public class DataStat {

	private int highestValueFromServer;
	private int lowestValueFromServer;
	private float averageOfValuesFromServer;
	/**
	 * @return the highestValueFromServer
	 */
	public int getHighestValueFromServer() {
		return highestValueFromServer;
	}
	/**
	 * @param highestValueFromServer the highestValueFromServer to set
	 */
	public void setHighestValueFromServer(int highestValueFromServer) {
		this.highestValueFromServer = highestValueFromServer;
	}
	/**
	 * @return the lowestValueFromServer
	 */
	public int getLowestValueFromServer() {
		return lowestValueFromServer;
	}
	/**
	 * @param lowestValueFromServer the lowestValueFromServer to set
	 */
	public void setLowestValueFromServer(int lowestValueFromServer) {
		this.lowestValueFromServer = lowestValueFromServer;
	}
	/**
	 * @return the averageOfValuesFromServer
	 */
	public float getAverageOfValuesFromServer() {
		return averageOfValuesFromServer;
	}
	/**
	 * @param averageOfValuesFromServer the averageOfValuesFromServer to set
	 */
	public void setAverageOfValuesFromServer(float averageOfValuesFromServer) {
		this.averageOfValuesFromServer = averageOfValuesFromServer;
	}
	
	

}
