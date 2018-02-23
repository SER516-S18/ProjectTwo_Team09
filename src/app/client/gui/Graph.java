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

@SuppressWarnings("serial")
public class Graph extends JPanel {

	XYDataset dataset;
	JFreeChart chart;
	ChartPanel chartPanel;

	public Graph() {
		initializeGraph();
		add(chartPanel);
		setVisible(true);
	}

	public void initializeGraph() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
	}

	public void updateGraph() {
		remove(chartPanel);
		dataset = createDataset();
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
		add(chartPanel);
		setVisible(true);
	}

	private XYDataset createDataset() {

		XYSeries series[] = new XYSeries[ClientCommonData.getInstance().getChannels()];
		XYSeriesCollection dataset = new XYSeriesCollection();

		for (int i = 0; i < ClientCommonData.getInstance().getChannels(); i++) {
			series[i] = new XYSeries("Channel " + (i + 1));
		}

		for (int i = 0; i < ClientCommonData.getInstance().getDataFromServer().size(); i++) {
			for (int j = 0; j < ClientCommonData.getInstance().getChannels(); j++) {
				series[j].add(ClientCommonData.getInstance().getDataFromServer().get(i).get(j).xCoordinate,
						ClientCommonData.getInstance().getDataFromServer().get(i).get(j).yCoordinate);
			}
		}

		for (int i = 0; i < ClientCommonData.getInstance().getChannels(); i++) {
			dataset.addSeries(series[i]);
		}

		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {

		Color colorList[] = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE };

		JFreeChart chart = ChartFactory.createXYLineChart("", "Time (s)", "Numbers", dataset, PlotOrientation.VERTICAL,
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
