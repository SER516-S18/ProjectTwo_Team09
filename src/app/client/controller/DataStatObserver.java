package app.client.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import app.client.model.ClientCommonData;
import app.client.model.DataStat;

/**
 * This class observes data changes for statistics like highest value, lowest
 * value and the average. When any data changes are observed, it informes the
 * dataStat class to perform the changes on the User Interface.
 *
 * @author Pratik Suryavanshi
 * @version 1.0
 * @since February, 2018
 *
 */
public class DataStatObserver implements Observer {

	private DataStat stat;

	/**
	 * @return the stat
	 */
	public DataStat getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(DataStat stat) {
		this.stat = stat;
	}

	/**
	 * This method calculates the highest, lowest values received from the
	 * server and calculates the average from them. After this it returns the
	 * DataStat Model containing all the information.
	 * 
	 * @param: list:
	 *             Pass the list of values for which the calculation has to be
	 *             done.
	 * 
	 * @return: returns the DataStat model containing the prescribed values.
	 * 
	 */
	public static DataStat findStats(List<Integer> list) {

		int maxValue = Integer.MIN_VALUE;
		int minValue = Integer.MAX_VALUE;
		float sum = 0;

		for (int x : list) {
			if (x > maxValue)
				maxValue = x;
			if (x < minValue)
				minValue = x;
			sum += x;
		}

		DataStat stat = new DataStat();
		stat.setHighestValueFromServer(maxValue);
		stat.setLowestValueFromServer(minValue);
		stat.setAverageOfValuesFromServer(sum / list.size());

		return stat;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {

		ClientCommonData data = (ClientCommonData) o;
		// list = data.getMyData();
		List<Integer> listOfValuesFromServer = data.getListOfAllValues();
		// List<Integer> list = new ArrayList<Integer>();
		// setStat(findStats(list));
		DataStat finalStats = findStats(listOfValuesFromServer);
		ClientCommonData.getInstance()
				.updateAverage(finalStats.getAverageOfValuesFromServer());
		ClientCommonData.getInstance()
				.updateMaxValue(finalStats.getHighestValueFromServer());
		ClientCommonData.getInstance()
				.updateMinvalue(finalStats.getLowestValueFromServer());

	}

}
