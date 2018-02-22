package app.server;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;



/**
 * @author Janani Thiagarajan
 * @version 1.0 Class to create a Server UI as per 
 * the user requirements - User story 2.1.1
 *  
 */
public class ServerUI {

	private JFrame serverFrame;
	private JTextField txtHighestValue;
	private JTextField txtLowestValue;
	private JTextField txtFrequency;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerUI window = new ServerUI();
					window.serverFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		serverFrame = new JFrame();
		serverFrame.setFont(new Font("Courier New", Font.PLAIN, 17));
		serverFrame.getContentPane().setFont(new 
				Font("Times New Roman", Font.PLAIN, 10));
		serverFrame.getContentPane().setBackground(
				new Color(240, 248, 255));
		serverFrame.getContentPane().setLayout(null);
		
		JButton toggleButton = new JButton("Start / Stop");
		toggleButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		toggleButton.setHorizontalTextPosition(SwingConstants.CENTER);
		toggleButton.setFont(new Font("Courier New", Font.BOLD, 17));
		toggleButton.setBounds(578, 6, 187, 40);
		toggleButton.setForeground(new Color(0, 0, 0));
		toggleButton.setBackground(new Color(255, 228, 225));
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		serverFrame.getContentPane().add(toggleButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 56, 758, 379);
		serverFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel pnlSignal = new JPanel();
		pnlSignal.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlSignal.setBackground(new Color(255, 228, 225));
		pnlSignal.setBounds(23, 22, 414, 335);
		panel.add(pnlSignal);
		
		JLabel lblHighestValue = new JLabel("<html>Highest<br>"
				+ "Value:</html>");
		lblHighestValue.setBackground(new Color(240, 248, 255));
		lblHighestValue.setOpaque(true);
		lblHighestValue.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHighestValue.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHighestValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighestValue.setFont(new Font("Courier New", Font.BOLD, 17));
		lblHighestValue.setBounds(462, 34, 131, 68);
		panel.add(lblHighestValue);
		
		JLabel lblLowestValue = new JLabel("<html>Lowest<br>"
				+ "Value:</html>");
		lblLowestValue.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLowestValue.setOpaque(true);
		lblLowestValue.setBackground(new Color(255, 228, 225));
		lblLowestValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblLowestValue.setFont(new Font("Courier New", Font.BOLD, 17));
		lblLowestValue.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblLowestValue.setBounds(462, 119, 131, 68);
		panel.add(lblLowestValue);
		
		JLabel lblfrequencyhz = new JLabel("<html>Frequency<br>"
				+ "(Hz):</html>");
		lblfrequencyhz.setOpaque(true);
		lblfrequencyhz.setBackground(new Color(240, 248, 255));
		lblfrequencyhz.setHorizontalTextPosition(SwingConstants.CENTER);
		lblfrequencyhz.setHorizontalAlignment(SwingConstants.CENTER);
		lblfrequencyhz.setFont(new Font("Courier New", Font.BOLD, 17));
		lblfrequencyhz.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblfrequencyhz.setBounds(462, 202, 131, 68);
		panel.add(lblfrequencyhz);
		
		txtHighestValue = new JTextField();
		txtHighestValue.setBackground(new Color(255, 228, 225));
		txtHighestValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtHighestValue.setFont(new Font("Courier New", Font.BOLD, 17));
		txtHighestValue.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtHighestValue.setBounds(620, 34, 117, 68);
		panel.add(txtHighestValue);
		txtHighestValue.setColumns(10);
		
		txtLowestValue = new JTextField();
		txtLowestValue.setBackground(new Color(240, 248, 255));
		txtLowestValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtLowestValue.setFont(new Font("Courier New", Font.BOLD, 17));
		txtLowestValue.setColumns(10);
		txtLowestValue.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtLowestValue.setBounds(620, 119, 117, 68);
		panel.add(txtLowestValue);
		
		txtFrequency = new JTextField();
		txtFrequency.setBackground(new Color(255, 228, 225));
		txtFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		txtFrequency.setFont(new Font("Courier New", Font.BOLD, 17));
		txtFrequency.setColumns(10);
		txtFrequency.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtFrequency.setBounds(620, 202, 117, 68);
		panel.add(txtFrequency);
		
		JTextArea txtrConsole = new JTextArea();
		txtrConsole.setFont(new Font("Courier New", Font.PLAIN, 15));
		txtrConsole.setBorder(new TitledBorder(null, "Console", 
				TitledBorder.LEADING, TitledBorder.TOP, 
				new Font("Courier New", Font.BOLD, 17), null));
		txtrConsole.setBackground(new Color(245, 245, 245));
		txtrConsole.setBounds(10, 445, 758, 138);
		serverFrame.getContentPane().add(txtrConsole);
		serverFrame.setTitle("Server");
		serverFrame.setBounds(100, 100, 800, 629);
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
