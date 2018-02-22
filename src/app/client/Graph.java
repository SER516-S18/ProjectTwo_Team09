package app.client;

import java.awt.BasicStroke;
import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
//import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

@SuppressWarnings("serial")
public class Graph extends JPanel {
	
	public static int Time = 1;
	
	public Graph(ClientCommonData data) {
		//startTimer();
		CreateGraph(data.getDataFromServer());
    }

    private void CreateGraph(List<ArrayList<Integer>> dataFromServer) {
        XYDataset dataset = createDataset(dataFromServer);
        JFreeChart chart = createChart(dataset, dataFromServer.size());
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
        setVisible(true);
    }

    private XYDataset createDataset(List<ArrayList<Integer>> dataFromServer) {
        
    	XYSeries series[] = new XYSeries[dataFromServer.size()];
    	XYSeriesCollection dataset = new XYSeriesCollection();
    	
    	for(int i = 0; i < dataFromServer.size(); i++)
    	{
    		series[i] = new XYSeries("Channel " + (i+1));
    		int x = Time;
    		for(int j = 0; j < dataFromServer.get(i).size(); j++)
    		{
    			series[i].add(x, dataFromServer.get(i).get(j));
    		}
            dataset.addSeries(series[i]);
    	}

        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset, int NumberOfChannels) {
    	
    	Color colorList[] = new Color[] {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};
    	
        JFreeChart chart = ChartFactory.createXYLineChart(
                "", 
                "Time", 
                "Numbers", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false
        );

        XYPlot plot = chart.getXYPlot();
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        for(int i = 0; i < NumberOfChannels; i++)
        {
        	renderer.setSeriesPaint(i, colorList[i]);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }
        
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.GRAY);

        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }
    
    /**
	* Starts a timer at intervals of 1 second.
	* @param Nothing
	* @return Nothing
	* @exception Exception
	*/
    /*
	private void startTimer()
	{
		try
		{
			Timer timer = new Timer(1000, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Time++;
					List<ArrayList<Integer>> dataFromServer = ClientCommonData.getInstance().getDataFromServer();
					CreateGraph(dataFromServer);
				}
			});
			timer.start();
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
	}
	*/
}

