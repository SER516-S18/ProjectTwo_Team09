package app.client;

import java.util.ArrayList;

public class CommonDataTesting {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> listOLists = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> singleList = new ArrayList<Integer>();
		singleList.add(1);
		singleList.add(2);
		listOLists.add(singleList);
		
		ClientCommonData dataObject=new ClientCommonData();
		GraphObserver graphObject=new GraphObserver();
		dataObject.addObserver(graphObject);
		dataObject.setDataFromServer(listOLists);
		dataObject.notifyObservers();
		//on calling notify observer GraphObserver update object gets called
	}
}
