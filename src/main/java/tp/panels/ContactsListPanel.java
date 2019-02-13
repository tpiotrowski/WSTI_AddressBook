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

public class ContactsListPanel extends JPanel implements IContactListEditor {
	private JTable table;

	String source;
	IContactBookService service;
	
	
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

		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, "cell 0 0");

		table = new JTable();
		panel.add(table, "cell 0 1,grow");

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Contact details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panel_1);
	}

	public void setSource(String source, Boolean isNew) {
		this.source = source;

		service = ContactBookServiceFactory.factory(source);

		try {
			if (!isNew) {
				service.Initialize();
				var list = service.getPersons();

				FillJTable(table, list);
			} else {
				var list = service.getPersons();

				FillJTable(table, list);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void FillJTable(JTable table, ArrayList<Person> persons) {
		var model = new PersonsTableModel(persons);

		table.setModel(model);

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
