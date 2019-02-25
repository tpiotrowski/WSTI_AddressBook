package tp.panels;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;

import net.miginfocom.swing.MigLayout;
import tp.interfaces.IContactBookService;
import tp.interfaces.IContactListEditor;
import tp.model.Person;
import tp.model.PersonsTableModel;
import tp.services.ContactBookServiceFactory;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class ContactsListPanel extends JPanel implements IContactListEditor {
	private JTable table;
	JPanel contactDetailsPanel;

	String source;
	IContactBookService service;
	private JTextField textField;
	PersonsTableModel model;
	private IDirtyChangedEventListener dirtyChangedListener = null;
	private JSplitPane splitPane;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public ContactsListPanel() {
		initialize();
		

	}

	private void initialize() {
		setLayout(new GridLayout(1, 0, 0, 0));

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0);
		add(splitPane);

		panel = new JPanel();
		panel.setMinimumSize(new Dimension(500, 500));
		panel.setBorder(new TitledBorder(null, "Contacts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "flowx,cell 0 1,grow");

		table = new JTable();
		table.setAutoCreateRowSorter(true);
		
		table.setUpdateSelectionOnSort(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		var sorter = table.getRowSorter();
		
		sorter.addRowSorterListener(e -> {
			bindPersonDetails();
		});
		
		scrollPane.setViewportView(table);
		

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(e -> newPersonButton(e));
		panel.add(btnAdd, "flowx,cell 0 0");

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(e -> editPersonButton(e));
		panel.add(btnEdit, "cell 0 0");

		JButton btnDelete = new JButton("Delete");
		panel.add(btnDelete, "cell 0 0,alignx left,aligny top");

		JLabel lblSearch = new JLabel("Search:");
		panel.add(lblSearch, "cell 0 0");

		textField = new JTextField();
		panel.add(textField, "cell 0 0");
		textField.setColumns(200);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filterPerson();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filterPerson();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				
				
			}

			private void filterPerson() {
				ArrayList<Person> persons = null;
				
				var text = textField.getText();
				if(text != null && !text.isEmpty()) {
					persons = service.findPersonsByNamoOrSurname(text);
				}else {
					persons = service.getPersons();
				}
				
				model.setPersons(persons);
			}
		});

		contactDetailsPanel = new JPanel();
		contactDetailsPanel.setBorder(
				new TitledBorder(null, "Contact details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(contactDetailsPanel);
		
		   splitPane.setOneTouchExpandable(true);
	        splitPane.setContinuousLayout(true);
		contactDetailsPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
	}

	@Override
	public void setDirtyChangedEventListener(IDirtyChangedEventListener e) {
		this.dirtyChangedListener = e;
	}

	private void fireDrityChanged(Boolean isDirty) {
		if (dirtyChangedListener != null) {
			dirtyChangedListener.onDirtyChanged(isDirty,this.source);
		}
	}

	@Override
	public String getSource()
	{
		return this.source; 
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

			table.getRowSorter().toggleSortOrder(1);
			
			table.getSelectionModel().addListSelectionListener(e -> {
				bindPersonDetails();
			});
			
			if(!list.isEmpty())
			{
				table.changeSelection(0, 0, false, false);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void bindPersonDetails() {
		var person = getSelectedPerson();
		
		ContactDetails cDetails = new ContactDetails();
		
		cDetails.setReadOnly(true);
		cDetails.setData(person);
		contactDetailsPanel.removeAll();
		contactDetailsPanel.add(cDetails,"cell 0 0,growx,aligny top");
		contactDetailsPanel.setSize(cDetails.getMinimumSize());
		validate();
	}

	private Person getSelectedPerson() {
		var selectedRowIndex = table.getSelectedRow();
		var index = table.convertRowIndexToModel(selectedRowIndex);
		var person = model.getRow(index);
		return person;
	}

	void newPersonButton(ActionEvent e) {
		
		CustomerEditDialog dialog = new CustomerEditDialog();
		dialog.setData(new Person());
	    int result = dialog.showDialog();
	    
		if (result == JOptionPane.OK_OPTION) {

			var person = dialog.getData();
			service.addPerson(person);
			model.addPerson(person);
			fireDrityChanged(isDirty());
			bindPersonDetails();
		}
	}
	
	void editPersonButton(ActionEvent e) {	
		var person = getSelectedPerson();
	
		CustomerEditDialog dialog = new CustomerEditDialog();
		dialog.setData(person);
	    int result = dialog.showDialog();
	    
		if (result == JOptionPane.OK_OPTION) {

			person = dialog.getData();
			service.updatePerson(person);
			model.updatePerson(person);
			fireDrityChanged(isDirty());
			bindPersonDetails();
		}
		
	}

	
	@Override
	public void persist() throws Exception {
		service.persist();
		fireDrityChanged(isDirty());
	}

	@Override
	public Boolean isDirty() {
		return service.isDirty();
	}


	
	

}
