import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ClientGui extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static final Color PINK = new Color(242, 208, 238);
	public static final Color LIGHTPINK = new Color(235, 218, 239);
	public static final Color BLUE = new Color(222, 235, 252);
	private String[] channelSize = new String[] {"1", "2", "3", "4", "5"};
	private JComboBox<String> channelChoice;
	
	public ClientGui() {
		JFrame client = new JFrame();
		client.setTitle("Client");
		client.setSize(800,760);
		client.setVisible(true);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setLayout(null);
		
		JButton toggle = new JButton("start / stop");
	    toggle.setBackground(PINK);
	    toggle.setBounds(580, 10, 190, 30);
	    toggle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
        client.add(console);
        console.setLayout(null);
        
        JLabel consoleHeading = new JLabel("<html>Console:</html>");
        consoleHeading.setBackground(LIGHTPINK);
        consoleHeading.setBounds(10,5,70,15);
        consoleHeading.setHorizontalAlignment(SwingConstants.CENTER);
        consoleHeading.setOpaque(true);
        console.add(consoleHeading);
        
        JLabel highestValue = new JLabel("<html>Highest<br>value:</html>");
        highestValue.setBorder(BorderFactory.createLineBorder(Color.black));
        highestValue.setBackground(BLUE);
        highestValue.setBounds(560,15,85,60);
        highestValue.setHorizontalAlignment(SwingConstants.CENTER);
        highestValue.setOpaque(true);
        dataPanel.add(highestValue);

        JTextPane highestNumber = new JTextPane();
        highestNumber.setBorder(BorderFactory.createLineBorder(Color.black));
        highestNumber.setBackground(PINK);
        highestNumber.setBounds(655,15,85,60);
        dataPanel.add(highestNumber);
        
        JPanel graphArea = new JPanel();
        graphArea.setBorder(BorderFactory.createLineBorder(Color.black));
        graphArea.setBackground(PINK);
        graphArea.setBounds(15,15,520,450);
        dataPanel.add(graphArea);
        graphArea.setLayout(null);

        JLabel lowestValue = new JLabel("<html>Lowest<br>value:</html>");
        lowestValue.setBorder(BorderFactory.createLineBorder(Color.black));
        lowestValue.setBackground(PINK);
        lowestValue.setBounds(560,90,85,60);
        lowestValue.setHorizontalAlignment(SwingConstants.CENTER);
        lowestValue.setOpaque(true);
        dataPanel.add(lowestValue);
        
        JTextPane lowestNumber = new JTextPane();
        lowestNumber.setBorder(BorderFactory.createLineBorder(Color.black));
        lowestNumber.setBackground(BLUE);
        lowestNumber.setBounds(655,90,85,60);
        dataPanel.add(lowestNumber);
        
        JLabel averageLabel = new JLabel("<html>Average</html>");
        averageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        averageLabel.setBackground(BLUE);
        averageLabel.setBounds(560,165,85,60);
        averageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        averageLabel.setOpaque(true);
        dataPanel.add(averageLabel);
        
        JTextPane averageNumber = new JTextPane();
        averageNumber.setBorder(BorderFactory.createLineBorder(Color.black));
        averageNumber.setBackground(PINK);
        averageNumber.setBounds(655,165,85,60);
        dataPanel.add(averageNumber);
        
        JLabel channels = new JLabel("<html>Channels:</html>");
        channels.setBorder(BorderFactory.createLineBorder(Color.black));
        channels.setBackground(PINK);
        channels.setBounds(560,245,85,60);
        channels.setHorizontalAlignment(SwingConstants.CENTER);
        channels.setOpaque(true);
        dataPanel.add(channels);
        
        channelChoice = new JComboBox<String>(channelSize);
        channelChoice.setVisible(true);
        channelChoice.setBorder(BorderFactory.createLineBorder(Color.black));
        channelChoice.setBackground(BLUE);
        channelChoice.setBounds(655,245,85,60);
        channelChoice.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String selectedValue = (String)channelChoice.getSelectedItem();
			}
		});
        dataPanel.add(channelChoice);
        
        JLabel freqLabel = new JLabel("<html>Frequency<br>(Hz):</html>");
        freqLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        freqLabel.setBackground(BLUE);
        freqLabel.setBounds(560,325,85,60);
        freqLabel.setHorizontalAlignment(SwingConstants.CENTER);
        freqLabel.setOpaque(true);
        dataPanel.add(freqLabel);

        JTextPane freqNumber = new JTextPane();
        freqNumber.setBorder(BorderFactory.createLineBorder(Color.black));
        freqNumber.setBackground(PINK);
        freqNumber.setBounds(655,325,85,60);
        dataPanel.add(freqNumber);
        dataPanel.repaint();
        console.repaint();
	}
	 public static void main(String[] args) 
	    {
	        ClientGui clientInstance = new ClientGui();
	    }
}
