package ch.arkeine.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

import ch.arkeine.function.implementation.ToothSaws;

@SuppressWarnings("serial")
public class JFrameMain extends JFrame {

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JFrameMain() {
		geometry();
		control();
		appearance();
	}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry() {
		panelMain = new JPanelMain();
		focusBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		unfocusBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

		setLayout(new BorderLayout());

		add(panelMain, BorderLayout.CENTER);
	}

	private void control() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				xOffset = e.getX();
				yOffset = e.getY();
			}
		});

		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				JFrameMain.this.setLocation(e.getXOnScreen() - xOffset, e.getYOnScreen() - yOffset);
			}
		});

		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				panelMain.setBorder(unfocusBorder);
			}

			@Override
			public void focusGained(FocusEvent e) {
				panelMain.setBorder(focusBorder);
			}
		});
	}

	private void appearance() {
		setSize(100, 600);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setVisible(true);
	}

	/* ============================================ */
	// FIELD
	/* ============================================ */

	private JPanelMain panelMain;
	private Border focusBorder;
	private Border unfocusBorder;

	// moving borderless frame
	private int xOffset;
	private int yOffset;
}
