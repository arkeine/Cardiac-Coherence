package ch.arkeine.gui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JPanel;

import ch.arkeine.SharedConstant;
import ch.arkeine.ToolClassList;
import ch.arkeine.function.implementation.Sinus;

@SuppressWarnings("serial")
public class JPanelMain extends JPanel implements JPanelWindowControlListener {

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JPanelMain() {
		geometry();
		control();
		appearance();
	}
	
	/* ============================================ */
	// OVERRIDE
	/* ============================================ */
	
	@Override
	public void onCloseClicked() {
		// nothing to do on close: quick method
		System.exit(0);
	}

	@Override
	public void onSettingClicked() {		
		try {
			Collection<Class> functions = ToolClassList.getClasses(SharedConstant.PACKAGE_FUNCTION_NAME);

			new JDialogSetFunction(functions);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry() {
		control = new JPanelWindowControl();
		displayFrequency = new JPanelDisplayFrequency(new Sinus(), 30);
		setLayout(new BorderLayout());

		add(displayFrequency, BorderLayout.CENTER);
		add(control, BorderLayout.NORTH);
	}

	private void control() {
		control.addJPanelWindowControlListener(this);
	}

	private void appearance() {
		// nothing
	}
	
	/* ============================================ */
	// FIELD
	/* ============================================ */

	private JPanelDisplayFrequency displayFrequency;
	private JPanelWindowControl control;
	
}
