package tp.panels;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import tp.Tools.SwingTools;
import tp.model.Person;

import javax.swing.JTabbedPane;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import java.awt.Component;
import java.awt.Dimension;

public class ContactDetails extends JPanel {
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfPhone;
	private JTextField tfIdDocument;
	private JTextField tfTaxNr;
	private Person person;

	ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	private AddressDetailsPanel addressPanel2;
	private AddressDetailsPanel addressPanel1;
	
	
	/**
	 * Create the panel.
	 */
	public ContactDetails() {
		setMinimumSize(new Dimension(400, 300));
		initialize();
		
		
		
		
		
		
		tfTaxNr.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				tfTaxNr.setToolTipText("dasdasdasdasdasdsad");
				
		
				
				return false;
			}
			
		});
		

		
		

	}

	private void initialize() {
		setLayout(new MigLayout("", "[grow,fill]", "[112.00,grow,top][grow]"));

		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][226.00,grow]", "[][][][][]"));

		JLabel lblNewLabel = new JLabel("Name:");
		panel.add(lblNewLabel, "cell 0 0,alignx trailing");

		tfName = new JTextField();
		SwingTools.addChangeListener(tfName, e -> {
			person.setName(tfName.getText());
		});
		
		textFields.add(tfName);

		panel.add(tfName, "flowx,cell 1 0,growx");
		tfName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Surname:");
		panel.add(lblNewLabel_1, "cell 0 1,alignx trailing");

		tfSurname = new JTextField();
		panel.add(tfSurname, "cell 1 1,growx");
		tfSurname.setColumns(10);
		
		textFields.add(tfSurname);
		
		SwingTools.addChangeListener(tfSurname, e -> {
			person.setSurname(tfSurname.getText());
		});

		JLabel lblNewLabel_2 = new JLabel("Phone:");
		panel.add(lblNewLabel_2, "cell 0 2,alignx trailing");

		tfPhone = new JTextField();
		panel.add(tfPhone, "cell 1 2,growx");
		tfPhone.setColumns(10);

		textFields.add(tfPhone);
		
		SwingTools.addChangeListener(tfPhone, e -> {
			person.setPhone(tfPhone.getText());
		});

		JLabel lblNewLabel_3 = new JLabel("Document Id:");
		panel.add(lblNewLabel_3, "cell 0 3,alignx trailing");

		tfIdDocument = new JTextField();
		panel.add(tfIdDocument, "cell 1 3,growx,aligny top");
		tfIdDocument.setColumns(10);

		textFields.add(tfIdDocument);
		
		SwingTools.addChangeListener(tfIdDocument, e -> {
			person.setIdDocument(tfIdDocument.getText());
		});

		JLabel lblTaxNumber = new JLabel("Tax number:");
		panel.add(lblTaxNumber, "cell 0 4,alignx trailing");

		tfTaxNr = new JTextField();
		panel.add(tfTaxNr, "cell 1 4,growx");
		tfTaxNr.setColumns(10);

		textFields.add(tfTaxNr);
		
		SwingTools.addChangeListener(tfTaxNr, e -> {
			person.setTaxId(tfTaxNr.getText());
		});

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, "cell 0 1,growx,aligny top");
		
		addressPanel1 = new AddressDetailsPanel();
		tabbedPane.addTab("Address 1", null, addressPanel1, null);
		

		addressPanel2 = new AddressDetailsPanel();
		tabbedPane.addTab("Address 2", null, addressPanel2, null);
	}

	public void setData(Person person) {
		this.person = person;
		bind(person);
		addressPanel1.bind(person.getAddresses1());
		addressPanel2.bind(person.getAddresses2());
	
	}

	public void bind(Person person) {
		tfName.setText(person.getName());
		tfSurname.setText(person.getSurname());
		tfPhone.setText(person.getPhone());
		tfIdDocument.setText(person.getIdDocument());
		tfTaxNr.setText(person.getTaxId());
		
		
		
		
	}

	public Person getData() {
		person.setAddresses1(addressPanel1.getData());
		person.setAddresses2(addressPanel2.getData());
		return person;
	}

	public void setReadOnly(Boolean isReadOnly) {
		for (JTextField jTextField : textFields) {
			jTextField.setEditable(!isReadOnly);
		}
		addressPanel1.setReadOnly(isReadOnly);
		addressPanel2.setReadOnly(isReadOnly);
		
	}
	

}
