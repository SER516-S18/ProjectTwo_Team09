package app.client;

import java.util.ArrayList;

import app.client.model.ClientCommonData;

public class CommonDataTesting {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> listOLists = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> singleList = new ArrayList<Integer>();
		singleList.add(1);
		singleList.add(2);
		listOLists.add(singleList);
	}
}
