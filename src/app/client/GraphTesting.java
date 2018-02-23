package app.client;


import java.util.ArrayList;

import javax.swing.JFrame;

import app.client.gui.CoordinatesModel;
import app.client.gui.Graph;
import app.client.model.ClientCommonData;

public class GraphTesting {
	public JFrame testFrame;
	public void Test(Graph graph) {
		testFrame = new JFrame("Graph Test"); 
		testFrame.setTitle("Line chart");
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.add(graph);
		graph.updateGraph();
		testFrame.pack();
		testFrame.setVisible(true);
	}
	
	public void createTestData() {
		ArrayList<ArrayList<CoordinatesModel>> listOLists = new ArrayList<ArrayList<CoordinatesModel>>();
		int temp = 0;
		int time = 0;
		
		for(int i = 0; i < 5; i++)
		{
			ArrayList<CoordinatesModel> singleList = new ArrayList<CoordinatesModel>();
			for(int j = 0; j < 3; j++)
			{
				CoordinatesModel model = new CoordinatesModel(time, j + temp);
				singleList.add(model);
			}
			listOLists.add(singleList);
			time = time + 200;
			temp = temp + 1;
		}
		
		ClientCommonData.getInstance().getDataFromServer().addAll(listOLists);
		ClientCommonData.getInstance().setChannels(3);
	}
	
	public static void main(String[] args) {
		GraphTesting graphTest = new GraphTesting();
		graphTest.createTestData();
		Graph graph = new Graph();
		graphTest.Test(graph);
	}
}
