package app.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ClientGui extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final Color PINK = new Color(242, 208, 238);
	public static final Color LIGHTPINK = new Color(235, 218, 239);
	public static final Color BLUE = new Color(222, 235, 252);
	private String[] channelSize = new String[] { "1", "2", "3", "4", "5" };
	private JComboBox<String> channelChoice;
	JTextArea consoleOutput = new JTextArea(5, 30);
	
	 static Thread clientThread = null;

	/**
	 * GUI constructor for client. Adds all components
	 */
	public ClientGui() {
		Client runner = new Client();
		
		
		JFrame client = new JFrame();
		// Saving the value of this frame object to manipulate in future
		ClientCommonData.getInstance().setClientFrame(client);
		client.setTitle("Client");
		client.setSize(800, 760);
		client.setVisible(true);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setLayout(null);

		JButton toggle = new JButton("start / stop");
		toggle.setBackground(PINK);
		toggle.setBounds(580, 10, 190, 30);
		toggle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		toggle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ClientCommonData.getInstance().isStarted()) {
					ClientCommonData.getInstance().setStarted(false);
					toggle.setBackground(PINK);
					clientThread.interrupt();
					runner.stopClient();
					
				} else {
					ClientCommonData.getInstance().setStarted(true);
					toggle.setBackground(BLUE);
					clientThread = new Thread(runner);
					validateValues();
					clientThread.start();
				}
				
			}
		});
		client.add(toggle);

		JPanel dataPanel = new JPanel();
		dataPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dataPanel.setBackground(LIGHTPINK);
		dataPanel.setBounds(10, 50, 760, 480);
		client.add(dataPanel);
		dataPanel.setLayout(null);

		JPanel console = new JPanel();
		console.setBorder(BorderFactory.createLineBorder(Color.black));
		console.setBackground(LIGHTPINK);
		console.setBounds(10, 550, 760, 150);
		
		console.setLayout(null);

		JLabel consoleHeading = new JLabel("<html>Console:</html>");
		consoleHeading.setBackground(LIGHTPINK);
		consoleHeading.setBounds(10, 5, 70, 15);
		consoleHeading.setHorizontalAlignment(SwingConstants.CENTER);
		consoleHeading.setOpaque(true);
		console.add(consoleHeading);
		// adding the console to GUI
		client.add(console);

		JLabel highestValue = new JLabel("<html>Highest<br>value:</html>");
		highestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		highestValue.setBackground(BLUE);
		highestValue.setBounds(560, 15, 85, 60);
		highestValue.setHorizontalAlignment(SwingConstants.CENTER);
		highestValue.setOpaque(true);
		dataPanel.add(highestValue);

		JTextField highestNumber = new JTextField();
		highestNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		highestNumber.setHorizontalAlignment(SwingConstants.CENTER);
		highestNumber.setBackground(PINK);
		highestNumber.setBounds(655, 15, 85, 60);
		dataPanel.add(highestNumber);

		JPanel graphArea = new JPanel();
		graphArea.setBorder(BorderFactory.createLineBorder(Color.black));
		graphArea.setBackground(PINK);
		graphArea.setBounds(15, 15, 520, 450);
		dataPanel.add(graphArea);
		graphArea.setLayout(null);

		JLabel lowestValue = new JLabel("<html>Lowest<br>value:</html>");
		lowestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		lowestValue.setBackground(PINK);
		lowestValue.setBounds(560, 90, 85, 60);
		lowestValue.setHorizontalAlignment(SwingConstants.CENTER);
		lowestValue.setOpaque(true);
		dataPanel.add(lowestValue);

		JTextField lowestNumber = new JTextField();
		lowestNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		lowestNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lowestNumber.setBackground(BLUE);
		lowestNumber.setBounds(655, 90, 85, 60);
		dataPanel.add(lowestNumber);

		JLabel averageLabel = new JLabel("<html>Average</html>");
		averageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		averageLabel.setBackground(BLUE);
		averageLabel.setBounds(560, 165, 85, 60);
		averageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		averageLabel.setOpaque(true);
		dataPanel.add(averageLabel);

		JTextField averageNumber = new JTextField();
		averageNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		averageNumber.setHorizontalAlignment(SwingConstants.CENTER);
		averageNumber.setBackground(PINK);
		averageNumber.setBounds(655, 165, 85, 60);
		dataPanel.add(averageNumber);

		JLabel channels = new JLabel("<html>Channels:</html>");
		channels.setBorder(BorderFactory.createLineBorder(Color.black));
		channels.setBackground(PINK);
		channels.setBounds(560, 245, 85, 60);
		channels.setHorizontalAlignment(SwingConstants.CENTER);
		channels.setOpaque(true);
		dataPanel.add(channels);

		channelChoice = new JComboBox<String>(channelSize);
		channelChoice.setVisible(true);
		channelChoice.setBorder(BorderFactory.createLineBorder(Color.black));
		channelChoice.setBackground(BLUE);
		channelChoice.setBounds(655, 245, 85, 60);


		channelChoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String channelValue = (String) channelChoice.getSelectedItem();
				ClientCommonData.getInstance().setChannels(Integer.parseInt(channelValue));

			}
		});
		dataPanel.add(channelChoice);

		JLabel freqLabel = new JLabel("<html>Frequency<br>(Hz):</html>");
		freqLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		freqLabel.setBackground(BLUE);
		freqLabel.setBounds(560, 325, 85, 60);
		freqLabel.setHorizontalAlignment(SwingConstants.CENTER);
		freqLabel.setOpaque(true);
		dataPanel.add(freqLabel);

		JTextField freqNumber = new JTextField();
		freqNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		freqNumber.setHorizontalAlignment(SwingConstants.CENTER);
		freqNumber.setBackground(PINK);
		freqNumber.setBounds(655, 325, 85, 60);
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
				if(freqNumber.getText() != null && freqNumber.getText() != "") {
					int freq = Integer.parseInt(freqNumber.getText());
					ClientCommonData.getInstance().setFrequency(freq);
				}
			}
		});
		dataPanel.add(freqNumber);
		dataPanel.repaint();
		console.repaint();
	}

	protected void validateValues() {
		// TODO Auto-generated method stub
		if (ClientCommonData.getInstance().getFrequency() < 1) {
			ClientCommonData.getInstance().getLogs().add("Frequency value invalid");
		}

		if (ClientCommonData.getInstance().getChannels() < 1) {
			ClientCommonData.getInstance().getLogs().add("Channel value invalid");
		}

	}

	public static void main(String[] args) {
		// Starting GUI using constructor
		new ClientGui();
	}
}
