package ch.arkeine.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import ch.arkeine.SharedConstant;
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

		this.ellipseColor = Color.BLUE;
		this.ellipseRadius = 20;

		startAnimation();
	}

	/* ============================================ */
	// DRAWING
	/* ============================================ */

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		boolean canDraw = calculatePosition();

		if (canDraw) {
			drawEllipse(g);
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
		int yMargin = (int) (ellipseRadius * 1.5);
		int r = ellipseRadius;
		int w = getWidth();
		int h = getHeight();

		// check for error in formule
		Assert.assertTrue(yPercent <= 1 && yPercent >= -1);

		ellipse.setFrame(w / 2 - r, (h - yMargin) / 2 + (h / 2 - 2 * yMargin) * yPercent, 2 * r, 2 * r);

		return 2 * yMargin < h; // true if drawing is possible
	}

	private void drawEllipse(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);	
		g2d.setColor(ellipseColor);
		g2d.draw(ellipse);
		g2d.fill(ellipse);
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
	private Color ellipseColor;
	private int ellipseRadius; // in pixel

}
