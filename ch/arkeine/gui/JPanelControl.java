package ch.arkeine.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class JPanelControl extends JPanel {

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JPanelControl() {
		geometry();
		control();
		appearance();
	}
	
	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry() {
		// set the time setting bar (in millisecond)
		int initialValue = 1500;
		int minValue = 4; 
		int mmaxValue = 10000;
		
		sliderTimeDivision = new JSlider(JSlider.HORIZONTAL, minValue, mmaxValue, initialValue);
		
		// set the breath setting bar (a period is time / 4)
		breathPerMinute = new JSpinner(new SpinnerNumberModel(initialValue, minValue / 4, mmaxValue / 4, 1));
		
		setLayout(new FlowLayout());

		add(sliderTimeDivision);
		add(breathPerMinute);
	}

	private void control() {
	}

	private void appearance() {

	}
	
	/* ============================================ */
	// FIELD
	/* ============================================ */

	private JSlider sliderTimeDivision;
	private JSpinner breathPerMinute;
}
