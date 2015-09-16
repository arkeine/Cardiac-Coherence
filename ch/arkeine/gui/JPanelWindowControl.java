package ch.arkeine.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

@SuppressWarnings("serial")
public class JPanelWindowControl extends JPanel {

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JPanelWindowControl() {
		geometry();
		control();
		appearance();
	}

	/* ============================================ */
	// LISTENER
	/* ============================================ */

	public void addJPanelWindowControlListener(JPanelWindowControlListener listener) {
		listeners.add(JPanelWindowControlListener.class, listener);
	}

	public void removeJPanelWindowControlListener(JPanelWindowControlListener listener) {
		listeners.remove(JPanelWindowControlListener.class, listener);
	}

	public JPanelWindowControlListener[] getJPanelWindowControlListener() {
		return listeners.getListeners(JPanelWindowControlListener.class);
	}

	protected void fireCloseClickedEvent()
		{
		for(JPanelWindowControlListener iListener:getJPanelWindowControlListener())
			{
			iListener.onCloseClicked();
			}
		}

	protected void fireSettingClickedEvent()
		{
		for(JPanelWindowControlListener iListener:getJPanelWindowControlListener())
			{
			iListener.onSettingClicked();
			}
		}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry() {
		// use a label for the look and the unfocusable behavior
		labelClose = new JLabel(" X ");
		labelSelectFunction = new JLabel(" O ");

		setLayout(new BorderLayout());

		add(labelClose, BorderLayout.EAST);
		add(labelSelectFunction, BorderLayout.WEST);
	}

	private void control() {
		listeners = new EventListenerList();

		labelClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fireCloseClickedEvent();
			}
		});

		labelSelectFunction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fireSettingClickedEvent();
			}
		});
	}

	private void appearance() {
		// nothing
	}

	/* ============================================ */
	// FIELD
	/* ============================================ */

	private JLabel labelClose;
	private JLabel labelSelectFunction;
	private EventListenerList listeners;
}
