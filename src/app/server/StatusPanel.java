package app.server;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Server Status indicator Panel - If the server is running it is indicated by a
 * green blinking circle. If its stopped it is indicated by a red circle.
 * 
 * @author Nelson Tran
 * @version 1.0
 * @since 2018-02-23
 * 
 */
public class StatusPanel extends JPanel {
	private final Color GREEN = new Color(0, 128, 0);
	private final Color RED = new Color(255, 0, 0);
	private final int BLINK_INTERVAL = 500;

	private Timer blinkAnimation;
	private String statusText;
	private Color circleColor;
	private boolean isVisible;

	/**
	 * Toggles the visibility of the circle every 500ms
	 * 
	 * @param none
	 */
	public StatusPanel() {
		this.blinkAnimation = new Timer(BLINK_INTERVAL, event -> {
			this.isVisible = !this.isVisible;
			this.repaint();
		});
		this.setBlinking(false);
	}

	/**
	 * Set the blinking indicator. If `blink` is true, then the server is
	 * running and a green blinking circle. If `blink` is false, then the server
	 * is not running and is indicated by a (non-blinking) red circle.
	 * 
	 * @param blink
	 *            - Server status flag
	 */
	public void setBlinking(boolean blink) {
		if (blink) {
			this.blinkAnimation.start();
			this.circleColor = GREEN;
			this.statusText = "Server is running.";
			this.isVisible = false;
		} else {
			this.blinkAnimation.stop();
			this.circleColor = RED;
			this.statusText = "Server is not running.";
			this.isVisible = true;
		}
		this.repaint();
	}

	/**
	 * Paints the status circle with relevant colors
	 * 
	 * @param graphics
	 *            - Graphics object to change the color the circle
	 */
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);

		graphics.setColor(circleColor);
		graphics.drawString(statusText, 10, 20);

		if (isVisible) {
			graphics.fillOval(70, 30, 275, 275);
		}
	}

}
