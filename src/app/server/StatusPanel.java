package app.server;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusPanel extends JPanel {

    // Constants.
    private final Color GREEN = new Color(0, 128, 0);
    private final Color RED = new Color(255, 0, 0);
    private final int BLINK_INTERVAL = 500;

    // Instance variables.
    private Timer blinkAnimation;
    private String statusText;
    private Color circleColor;
    private boolean isVisible;

    public StatusPanel() {
        // Every 500ms, toggle the visibility of the circle.
        this.blinkAnimation = new Timer(BLINK_INTERVAL, event -> {
            this.isVisible = !this.isVisible;
            this.repaint();
        });

        // Initially, the server is not running.
        this.setBlinking(false);
    }

    /**
     * Set the blinking indicator. If `blink` is true, then the server
     * is running. This is indicated by a green blinking circle. If
     * `blink` is false, then the server is not running. This is
     * indicated by a (non-blinking) red circle.
     */
    public void setBlinking(boolean blink) {
		if (blink) {
            this.blinkAnimation.start();
            this.circleColor = GREEN;
            this.statusText = "Server is running.";
            this.isVisible = false;
		}
		else {
            this.blinkAnimation.stop();
            this.circleColor = RED;
            this.statusText = "Server is not running.";
            this.isVisible = true;
        }
        
        // Re-render the panel.
        this.repaint();
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(circleColor);
        g.drawString(statusText, 10, 20);

        if (isVisible) {
            g.fillOval(70, 30, 275, 275);
        }
    }

}