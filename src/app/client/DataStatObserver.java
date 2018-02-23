/**
 * 
 */
package app.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Pratik Suryawanshi
 *
 */
public class DataStatObserver implements Observer{

	private DataStat stat;
	
	/**
	 * @return the stat
	 */
	public DataStat getStat() {
		return stat;
	}

	/**
	 * @param stat the stat to set
	 */
	public void setStat(DataStat stat) {
		this.stat = stat;
	}

	public static DataStat findStats(List<Integer> list) {
		
		int maxValue = Integer.MIN_VALUE;
		int minValue = Integer.MAX_VALUE;
		float sum = 0;
		
		for(int x : list) {
			if(x > maxValue)
				maxValue = x;
			if(x < minValue)
				minValue = x;
			sum += x;
		}
		
		DataStat stat = new DataStat();
		stat.setHighest(maxValue);
		stat.setLowest(minValue);
		stat.setAverage(sum/list.size());
		stat.setFrequency(0);
		return stat;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		ClientCommonData data=(ClientCommonData) arg;
		// list = data.getMyData();
		List<Integer> list = new ArrayList<Integer>();
		setStat(findStats(list));
		
		System.out.println("stats done.");
		
	}

}
