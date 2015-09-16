package ch.arkeine.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import ch.arkeine.function.Function;

@SuppressWarnings("serial")
public class JDialogSetFunction extends JDialog {

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JDialogSetFunction(Collection<Class> functions) {
		this.functions = functions;

		geometry();
		control();
		appearance();
	}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry() {
		labelMessage = new JLabel("Select the function wanted");
		buttonOk= new JButton("Ok");
		buttonCancel = new JButton("Cancel");
		comboBoxFunctions = new JComboBox<>(functions.toArray(new Class[0]));
		comboBoxFunctions.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				JLabel item = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				System.out.println(value);
				Function function = (Function) value;
				item.setText(function.getName());

				return item;
			}
		});

		setLayout(new BorderLayout());
		
		JPanel panel;
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(labelMessage);
		add(panel, BorderLayout.NORTH);

		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(comboBoxFunctions);
		add(panel, BorderLayout.CENTER);
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(buttonOk);
		panel.add(buttonCancel);
		add(panel, BorderLayout.SOUTH);
	}

	private void control() {
		buttonOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isValidated = true;
				selectedClass = (Class) comboBoxFunctions.getSelectedItem();

				JDialogSetFunction.this.dispose();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialogSetFunction.this.dispose();
			}
		});
	}

	private void appearance() {
		setModal(true);
		setSize(300, 150);
		setVisible(true);
	}

	/* ============================================ */
	// FIELD
	/* ============================================ */

	// input
	private Collection<Class> functions;

	// tool
	private JLabel labelMessage;
	private JButton buttonOk;
	private JButton buttonCancel;
	private JComboBox<Class> comboBoxFunctions;

	// output
	private Class selectedClass;
	private boolean isValidated;
}
