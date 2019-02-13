package tp.panels;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import tp.interfaces.IContactBookService;
import tp.interfaces.IContactListEditor;
import tp.model.Person;
import tp.model.PersonsTableModel;
import tp.services.ContactBookServiceFactory;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContactsListPanel extends JPanel implements IContactListEditor {
	private JTable table;

	String source;
	IContactBookService service;
	private JTextField textField;
	PersonsTableModel model;

	/**
	 * Create the panel.
	 */
	public ContactsListPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.65);
		add(splitPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Contacts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[][grow]"));

		table = new JTable();
		panel.add(table, "cell 0 1,grow");

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(e -> newPersonButton(e));
		panel.add(btnAdd, "flowx,cell 0 0");

		JButton btnEdit = new JButton("Edit");
		panel.add(btnEdit, "cell 0 0");

		JButton btnDelete = new JButton("Delete");
		panel.add(btnDelete, "cell 0 0,alignx left,aligny top");

		JLabel lblSearch = new JLabel("Search:");
		panel.add(lblSearch, "cell 0 0");

		textField = new JTextField();
		panel.add(textField, "cell 0 0");
		textField.setColumns(200);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Contact details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panel_1);
	}

	public void setSource(String source, Boolean isNew) {
		this.source = source;

		service = ContactBookServiceFactory.factory(source);

		ArrayList<Person> list = null;

		try {
			if (isNew) {
				list = service.getPersons();
			} else {
				service.Initialize();
				list = service.getPersons();
			}

			model = new PersonsTableModel(table, list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void newPersonButton(ActionEvent e) {
		ContactDetails contactDetailsPanel = new ContactDetails();
		contactDetailsPanel.setData(new Person());
		int result = JOptionPane.showConfirmDialog(null, contactDetailsPanel, "My custom dialog",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {

			var person = contactDetailsPanel.getData();

			service.addPerson(person);
			model.addPerson(person);

		}
	}

	@Override
	public void persist() throws Exception {
		service.persist();

	}

	@Override
	public Boolean isDirty() {
		// TODO Auto-generated method stub
		return service.isDirty();
	}

}
