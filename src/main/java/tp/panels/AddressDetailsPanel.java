package tp.panels;

import javax.swing.JPanel;

import net.bytebuddy.implementation.bind.annotation.AllArguments.Binder;
import net.miginfocom.swing.MigLayout;
import tp.Tools.SwingTools;
import tp.model.Address;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddressDetailsPanel extends JPanel {
	private JTextField tfStreet;
	private JTextField tfHomeNuber;
	private JTextField tfFlatNumber;
	private JTextField tfCity;
	private JTextField tfZip;
	private JTextField tfCountry;

	private Address address;

	ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	/**
	 * Create the panel.
	 */
	public AddressDetailsPanel() {		
		initialize();
		
		for (Component component : this.getComponents()) {
			if(component instanceof JTextField)
			{
				textFields.add((JTextField)component);
			}
		}
	}

	public void bind(Address address) {
		this.address = address;
		
		tfStreet.setText(address.getStreet());
		SwingTools.addChangeListener(tfStreet, e -> {
			address.setStreet(tfStreet.getText());
		});
		tfHomeNuber.setText(address.getHomeNumberString());
		SwingTools.addChangeListener(tfHomeNuber, e -> {
			address.setHomeNumberString(tfHomeNuber.getText());
		});
		
		tfFlatNumber.setText(address.getFlatNumber());
		SwingTools.addChangeListener(tfFlatNumber, e -> {
			address.setFlatNumber(tfFlatNumber.getText());
		});
		
		tfCity.setText(address.getCity());
		SwingTools.addChangeListener(tfCity, e -> {
			address.setCity(tfCity.getText());
		});
		
		
		tfZip.setText(address.getZip());
		SwingTools.addChangeListener(tfZip, e -> {
			address.setZip(tfZip.getText());
		});
		
		
		tfCountry.setText(address.getCountry());
		SwingTools.addChangeListener(tfCountry, e -> {
			address.setCountry(tfCountry.getText());
		});
		
	}
	
	public Address getData()
	{
		return address;
	}
	
	
	private void initialize() {
		setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		JLabel lblNewLabel = new JLabel("Street:");
		add(lblNewLabel, "cell 0 0,alignx trailing");
		
		tfStreet = new JTextField();
		add(tfStreet, "cell 1 0,growx");
		tfStreet.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Home number:");
		add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		tfHomeNuber = new JTextField();
		add(tfHomeNuber, "flowx,cell 1 1,growx,aligny top");
		tfHomeNuber.setColumns(10);
		
		JLabel lblFaltNr = new JLabel("Flat number:");
		add(lblFaltNr, "cell 1 1,alignx right");
		
		tfFlatNumber = new JTextField();
		add(tfFlatNumber, "cell 1 1,growx");
		tfFlatNumber.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("City:");
		add(lblNewLabel_2, "cell 0 2,alignx trailing");
		
		tfCity = new JTextField();
		add(tfCity, "flowx,cell 1 2,growx");
		tfCity.setColumns(10);
		
		JLabel lblZip = new JLabel("Zip:");
		add(lblZip, "cell 1 2,alignx right");
		
		tfZip = new JTextField();
		add(tfZip, "cell 1 2,growx");
		tfZip.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country:");
		add(lblCountry, "cell 0 3,alignx trailing");
		
		tfCountry = new JTextField();
		add(tfCountry, "cell 1 3,growx");
		tfCountry.setColumns(20);
	}
	
	public void setReadOnly(Boolean isReadOnly) {
		for (JTextField jTextField : textFields) {
			jTextField.setEditable(!isReadOnly);
		}
		
	}

}
