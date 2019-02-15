package tp.panels;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddressDetailsPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public AddressDetailsPanel() {
		setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		JLabel lblNewLabel = new JLabel("Street:");
		add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Home number:");
		add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		add(textField_1, "flowx,cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblFaltNr = new JLabel("Flat number:");
		add(lblFaltNr, "cell 1 1,alignx right");
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 1,growx");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("City:");
		add(lblNewLabel_2, "cell 0 2,alignx trailing");
		
		textField_3 = new JTextField();
		add(textField_3, "flowx,cell 1 2,growx");
		textField_3.setColumns(10);
		
		JLabel lblZip = new JLabel("Zip:");
		add(lblZip, "cell 1 2,alignx right");
		
		textField_4 = new JTextField();
		add(textField_4, "cell 1 2,growx");
		textField_4.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country:");
		add(lblCountry, "cell 0 3,alignx trailing");
		
		textField_5 = new JTextField();
		add(textField_5, "cell 1 3,growx");
		textField_5.setColumns(20);

	}

}
