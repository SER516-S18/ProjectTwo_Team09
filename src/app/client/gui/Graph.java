/**
 * Provides the classes for controlling the rendering 
 * of the client window.
 *
 * @since 1.0
 */
package app.client.gui;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JPanel;

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

import app.client.model.ClientCommonData;

/**
 * This class Graph consists of methods to help with initializing, updating and creating a graph 
 * based on the data residing in ClientCommonData.
 * @author Adhiraj Tikku
 * @version 1.0
 * @since 2018-02-23
 *
 */
@SuppressWarnings("serial")
public class Graph extends JPanel {

	XYDataset dataset;
	JFreeChart chart;
	ChartPanel chartPanel;
	
	/**
	 * This constructor initializes a graph instance and creates a default empty graph.
	 * @param None
	 */
	public Graph() {
		initializeGraph();
		add(chartPanel);
		setVisible(true);
	}
	
	/**
	 * Updates the graph using the latest data present in ClientCommonData.
	 * @param None
	 * @return Void
	 */
	public void updateGraph() {
		remove(chartPanel);
		dataset = createDataset();
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
		add(chartPanel);
		setVisible(true);
	}
	

	private void initializeGraph() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
	}
	
	private XYDataset createDataset() {

		XYSeries series[] = new XYSeries[ClientCommonData.getInstance().getChannels()];
		XYSeriesCollection dataset = new XYSeriesCollection();

		for (int i = 0; i < ClientCommonData.getInstance().getChannels(); i++) {
			series[i] = new XYSeries("Channel " + (i + 1));
		}

		for (int i = 0; i < ClientCommonData.getInstance().getDataFromServer().size(); i++) {
			for (int j = 0; j < ClientCommonData.getInstance().getChannels(); j++) {
				series[j].add(ClientCommonData.getInstance().getDataFromServer().get(i).get(j).getxCoordinate(),
						ClientCommonData.getInstance().getDataFromServer().get(i).get(j).getyCoordinate());
			}
		}

		for (int i = 0; i < ClientCommonData.getInstance().getChannels(); i++) {
			dataset.addSeries(series[i]);
		}

		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {

		Color colorList[] = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE };

		JFreeChart chart = ChartFactory.createXYLineChart("", "Time (milliseconds)", "Numbers", dataset, PlotOrientation.VERTICAL,
				true, true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		for (int i = 0; i < ClientCommonData.getInstance().getChannels(); i++) {
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
}
