package app.client;

import java.util.ArrayList;

import javax.swing.JFrame;

public class GraphTesting {
	public void Test() {
		JFrame testFrame = new JFrame("Graph Test"); 
		testFrame.setTitle("Line chart");
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.add(new Graph(ClientCommonData.getInstance()));
		testFrame.pack();
		testFrame.setVisible(true);
	}
	
	public void createTestData() {
		ArrayList<ArrayList<Integer>> listOLists = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> singleList = new ArrayList<Integer>();
		singleList.add(1);
		singleList.add(2);
		singleList.add(3);
		singleList.add(4);
		singleList.add(5);
		listOLists.add(singleList);
		//ClientCommonData.getInstance().getDataFromServer().addAll(listOLists);
	}
	
	public static void main(String[] args) {
		GraphTesting graphTest = new GraphTesting();
		graphTest.createTestData();
		graphTest.Test();
	}
}
