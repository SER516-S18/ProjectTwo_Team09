package app.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.client.controller.ClientSocketController;
import app.client.model.ClientCommonData;

public class ClientView extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final Color PINK = new Color(242, 208, 238);
	public static final Color LIGHTPINK = new Color(235, 218, 239);
	private static final Color LIGHTGREY = new Color(245, 245, 245);
	public static final Color BLUE = new Color(222, 235, 252);
	private static final Font FONT = new Font("Courier New", Font.BOLD, 17);
	private static final Color BLACK = new Color(0, 0, 0);
	JButton buttonToggle;
	ClientSocketController clientSocketController;
	

	/**
	 * GUI constructor for client. Adds all components
	 */
	public ClientView() {
		clientSocketController = new ClientSocketController();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Client");
		setMinimumSize(new Dimension(800, 600));
		setLayout(new BorderLayout(8, 8));
		setBackground(BLUE);
		createToolBar();
		createMainBody();
		createConsole();
		setVisible(true);
	}

	private void createToolBar() {
		JToolBar mainToolbar = new JToolBar();
		mainToolbar.setBackground(LIGHTGREY);
		mainToolbar.setBorder(new EmptyBorder(8, 8, 8, 8));
		mainToolbar.setFloatable(false);
		mainToolbar.add(Box.createHorizontalGlue());
		buttonToggle = new JButton("Start / Stop");
		buttonToggle.setFont(FONT);
		buttonToggle.setBorder(new LineBorder(BLACK));
		mainToolbar.add(buttonToggle);

		buttonToggle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (ClientCommonData.getInstance().isStarted()) {
					System.out.println("Clicked stopped");
					ClientCommonData.getInstance().setStarted(false);
					buttonToggle.setBackground(PINK);
					clientSocketController.stopServer();
				} else {
					System.out.println("Clicked started");
					ClientCommonData.getInstance().setStarted(true);
					clientSocketController.startServer(ClientCommonData.getInstance().getChannels());
					buttonToggle.setBackground(BLUE);
				}
			}
		});
		add(mainToolbar, BorderLayout.PAGE_START);
	}

	private void createMainBody() {
		JPanel mainBody = new JPanel();

		mainBody.setLayout(new BorderLayout());
		mainBody.setOpaque(false);
		mainBody.setBorder(new EmptyBorder(8, 8, 8, 8));

		JPanel panelBuffer = new JPanel(new GridLayout(1, 2, 8, 8));
		panelBuffer.setBackground(LIGHTGREY);
		panelBuffer.setBorder(BorderFactory.createLineBorder(Color.black));

		panelBuffer.add(generateGraphView(), BorderLayout.LINE_START);

		panelBuffer.add(generateSideView(), BorderLayout.LINE_END);

		mainBody.add(panelBuffer, BorderLayout.CENTER);
		add(mainBody, BorderLayout.CENTER);
	}

	private JPanel generateSideView() {
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(5, 2, 8, 8));
		sidePanel.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING, TitledBorder.TOP, FONT, null));
		sidePanel.setOpaque(false);

		JLabel highestValue = new JLabel("<html>Highest<br>value:</html>");
		highestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		highestValue.setBackground(BLUE);
		highestValue.setHorizontalAlignment(SwingConstants.CENTER);
		highestValue.setOpaque(true);
		sidePanel.add(highestValue);

		JTextField highestNumber = new JTextField();
		highestNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		highestNumber.setHorizontalAlignment(SwingConstants.CENTER);
		highestNumber.setBackground(PINK);
		highestNumber.setEditable(false);
		sidePanel.add(highestNumber);

		JLabel lowestValue = new JLabel("<html>Lowest<br>value:</html>");
		lowestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		lowestValue.setBackground(PINK);
		lowestValue.setHorizontalAlignment(SwingConstants.CENTER);
		lowestValue.setOpaque(true);
		sidePanel.add(lowestValue);

		JTextField lowestNumber = new JTextField();
		lowestNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		lowestNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lowestNumber.setBackground(BLUE);
		lowestNumber.setEditable(false);
		sidePanel.add(lowestNumber);

		JLabel averageLabel = new JLabel("<html>Average</html>");
		averageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		averageLabel.setBackground(BLUE);
		averageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		averageLabel.setOpaque(true);
		sidePanel.add(averageLabel);

		JTextField averageNumber = new JTextField();
		averageNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		averageNumber.setHorizontalAlignment(SwingConstants.CENTER);
		averageNumber.setBackground(PINK);
		averageNumber.setEditable(false);
		sidePanel.add(averageNumber);

		JLabel channels = new JLabel("<html>Channels:</html>");
		channels.setBorder(BorderFactory.createLineBorder(Color.black));
		channels.setBackground(PINK);
		channels.setHorizontalAlignment(SwingConstants.CENTER);
		channels.setOpaque(true);
		sidePanel.add(channels);

		String[] channelSize = new String[] { "1", "2", "3", "4", "5" };
		JComboBox<String> channelChoice = new JComboBox<String>(channelSize);
		channelChoice.setVisible(true);
		channelChoice.setBorder(BorderFactory.createLineBorder(Color.black));
		channelChoice.setBackground(BLUE);
		channelChoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String channelValue = (String) channelChoice.getSelectedItem();
				ClientCommonData.getInstance().setChannels(Integer.parseInt(channelValue));
			}
		});
		sidePanel.add(channelChoice);

		JLabel freqLabel = new JLabel("<html>Frequency<br>(Hz):</html>");
		freqLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		freqLabel.setBackground(BLUE);
		freqLabel.setBounds(560, 325, 85, 60);
		freqLabel.setHorizontalAlignment(SwingConstants.CENTER);
		freqLabel.setOpaque(true);
		sidePanel.add(freqLabel);

		JTextField freqNumber = new JTextField();
		freqNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		freqNumber.setHorizontalAlignment(SwingConstants.CENTER);
		freqNumber.setBackground(PINK);
		freqNumber.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateFrequencyValue();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateFrequencyValue();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateFrequencyValue();
			}

			private void updateFrequencyValue() {
				if (freqNumber.getText() != null && freqNumber.getText() != "") {
					if (!freqNumber.getText().matches(".*[a-z].*") && !freqNumber.getText().equals("")) {
						int freq = Integer.parseInt(freqNumber.getText());
						ClientCommonData.getInstance().setFrequency(freq);
					} else {
						ClientCommonData.getInstance().setFrequency(1);
					}
				}
			}
		});
		sidePanel.add(freqNumber);
		return sidePanel;
	}

	private JPanel generateGraphView() {
		Graph graphPanel = new Graph();
		graphPanel.setLayout(new BorderLayout());
		graphPanel.setOpaque(false);
		graphPanel.setBorder(new TitledBorder(null, "Graph", TitledBorder.LEADING, TitledBorder.TOP, FONT, null));
		clientSocketController.startGraph(graphPanel);
		return graphPanel;
	}

	private void createConsole() {
		JTextArea consoleView = new JTextArea(6, 30);
		consoleView.setEditable(false);
		consoleView.setBackground(LIGHTGREY);
		consoleView.setFont(new Font("Courier New", Font.PLAIN, 15));
		JScrollPane consolePane = new JScrollPane(consoleView);
		consolePane.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, FONT, null));
		consolePane.setBackground(LIGHTGREY);
		consolePane.setBounds(10, 445, 758, 50);
		add(consolePane, BorderLayout.PAGE_END);
	}

	public static void main(String args[]) {
		new ClientView();
	}

}
