package ch.arkeine.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import ch.arkeine.function.Function;
import ch.arkeine.functionmanager.TimeFunctionManager;

import junit.framework.Assert;

@SuppressWarnings({ "serial", "deprecation" })
public class JPanelDisplayFrequency extends JPanel {

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JPanelDisplayFrequency(Function function, int frameRate) {
		this.functionManager = new TimeFunctionManager(function);
		this.frameRate = frameRate;
		this.ellipse = new Ellipse2D.Double();
		
		startAnimation();
	}

	/* ============================================ */
	// DRAWING
	/* ============================================ */

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		boolean canDraw = calculatePosition();

		if(canDraw)
		{
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(CIRCLE_COLOR);
			g2d.draw(ellipse);			
		}		
	}

	/* ============================================ */
	// ASSESSOR / MUTATOR
	/* ============================================ */

	public int getFrameRate() {
		return this.frameRate;
	}

	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void startAnimation() {
		animationThread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					while (true) {
						Thread.sleep(1000 / frameRate);		
						JPanelDisplayFrequency.this.repaint();
					}
				} catch (InterruptedException e) {
					String message = "Stop when waiting";
					Logger logger = Logger.getLogger(SharedConstant.LOGGER_NAME);
					logger.log(Level.WARNING, message, e);
				}

			}
		});

		animationThread.start();
	}

	
	private boolean calculatePosition() {
		double yPercent = functionManager.getYValue(); // value between -1 and 1
		int yMargin = (int) (CIRCLE_RADIUS * 1.5);
		int r = CIRCLE_RADIUS;
		int w = getWidth();
		int h = getHeight();

		Assert.assertTrue(yPercent <= 1 && yPercent >= -1); // check for error in formule
		
		ellipse.setFrame(w/2 -r, yMargin - r + (h-2*yMargin)*yPercent, 2*r, 2*r);
		
		return 2 * yMargin < h; // true if drawing is possible
	}
	
	/* ============================================ */
	// FIELD
	/* ============================================ */

	// Input
	private int frameRate;

	// Tool
	private TimeFunctionManager functionManager;
	private Thread animationThread;
	private Ellipse2D ellipse;
	
	// Drawing parameter
	public static final int CIRCLE_RADIUS= 20; // in pixel
	public static final Color CIRCLE_COLOR = Color.BLUE;
	
}
