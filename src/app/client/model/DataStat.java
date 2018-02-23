/**
 * 
 */
package app.client.model;

/**
 * @author Pratik Suryawanshi
 *
 */
public class DataStat {

	/**
	 * @param args
	 */
	 
	private int highest;
	private int lowest;
	private float average;
	private int frequency;
	
	
	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}



	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}



	/**
	 * @return the highest
	 */
	public int getHighest() {
		return highest;
	}



	/**
	 * @param highest the highest to set
	 */
	public void setHighest(int highest) {
		this.highest = highest;
	}



	/**
	 * @return the lowest
	 */
	public int getLowest() {
		return lowest;
	}



	/**
	 * @param lowest the lowest to set
	 */
	public void setLowest(int lowest) {
		this.lowest = lowest;
	}



	/**
	 * @return the average
	 */
	public float getAverage() {
		return average;
	}



	/**
	 * @param average the average to set
	 */
	public void setAverage(float average) {
		this.average = average;
	}


}
