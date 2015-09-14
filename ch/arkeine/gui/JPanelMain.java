package ch.arkeine.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.arkeine.function.Sinus;

@SuppressWarnings("serial")
public class JPanelMain extends JPanel {

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JPanelMain() {
		geometry();
		control();
		appearance();
	}
	
	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry() {
		control = new JPanelControl();
		displayFrequency = new JPanelDisplayFrequency(new Sinus(), 30);
		setLayout(new BorderLayout());

		add(displayFrequency, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
	}

	private void control() {
		// nothing
	}

	private void appearance() {
		// nothing
	}
	
	/* ============================================ */
	// FIELD
	/* ============================================ */

	private JPanelDisplayFrequency displayFrequency;
	private JPanelControl control;
}
