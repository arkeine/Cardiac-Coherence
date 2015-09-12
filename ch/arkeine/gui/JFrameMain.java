package ch.arkeine.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ch.arkeine.function.Sinus;

@SuppressWarnings("serial")
public class JFrameMain extends JFrame {

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JFrameMain()  {
		geometry();
		control();
		appearance();
	}
	
	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry() {
		displayFrequency = new JPanelDisplayFrequency(new Sinus(), 30);
		
		setLayout(new BorderLayout());
		
		add(displayFrequency, BorderLayout.CENTER);
	}

	private void control() {
		// nothing
	}

	private void appearance() {
		setSize(200, 600);
		setVisible(true);
	}

	/* ============================================ */
	// FIELD
	/* ============================================ */
	
	private JPanelDisplayFrequency displayFrequency;
}
