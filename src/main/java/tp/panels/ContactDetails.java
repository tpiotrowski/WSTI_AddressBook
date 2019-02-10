package tp.panels;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import tp.Tools.SwingTools;
import tp.model.Person;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ContactDetails extends JPanel {
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfPhone;
	private JTextField tfIdDocument;
	private JTextField tfTaxNr;
	private Person person;

	/**
	 * Create the panel.
	 */
	public ContactDetails() {
		setLayout(new MigLayout("", "[grow]", "[112.00,grow][grow]"));

		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][149.00,grow][][]", "[][][][]"));

		JLabel lblNewLabel = new JLabel("Name:");
		panel.add(lblNewLabel, "cell 0 0,alignx trailing");

		tfName = new JTextField();
		SwingTools.addChangeListener(tfName, e -> {
			person.setName(tfName.getText());
		});

		panel.add(tfName, "flowx,cell 1 0,growx");
		tfName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Surname:");
		panel.add(lblNewLabel_1, "cell 2 0,alignx trailing");

		tfSurname = new JTextField();
		panel.add(tfSurname, "cell 3 0");
		tfSurname.setColumns(10);

		SwingTools.addChangeListener(tfSurname, e -> {
			person.setSurname(tfSurname.getText());
		});

		JLabel lblNewLabel_2 = new JLabel("Phone:");
		panel.add(lblNewLabel_2, "cell 0 1,alignx trailing");

		tfPhone = new JTextField();
		panel.add(tfPhone, "cell 1 1,growx");
		tfPhone.setColumns(10);

		SwingTools.addChangeListener(tfPhone, e -> {
			person.setPhone(tfPhone.getText());
		});

		JLabel lblNewLabel_3 = new JLabel("Document Id:");
		panel.add(lblNewLabel_3, "cell 0 2,alignx trailing");

		tfIdDocument = new JTextField();
		panel.add(tfIdDocument, "cell 1 2,growx,aligny top");
		tfIdDocument.setColumns(10);

		SwingTools.addChangeListener(tfIdDocument, e -> {
			person.setIdDocument(tfIdDocument.getText());
		});

		JLabel lblTaxNumber = new JLabel("Tax number:");
		panel.add(lblTaxNumber, "cell 0 3,alignx trailing");

		tfTaxNr = new JTextField();
		panel.add(tfTaxNr, "cell 1 3,growx");
		tfTaxNr.setColumns(10);

		SwingTools.addChangeListener(tfTaxNr, e -> {
			person.setTaxId(tfTaxNr.getText());
		});

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, "cell 0 1,grow");

	}

	public void setData(Person person) {
		this.person = person;
		bind(person);
	}

	public void bind(Person person) {
		tfName.setText(person.getName());
		tfSurname.setText(person.getSurname());
		tfPhone.setText(person.getPhone());
		tfIdDocument.setText(person.getIdDocument());
		tfTaxNr.setText(person.getTaxId());
	}

	public Person getDtata() {
		return person;
	}

}