package tp.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PersonsTableModel extends AbstractTableModel {

	String[] columnNameStrings = { "Name", "Surname" };

	private ArrayList<Person> persons = new ArrayList<Person>();

	public PersonsTableModel(ArrayList<Person> persons) {
		super();
		this.persons = persons;
	}

	@Override
	public int getRowCount() {
		return persons.size();
	}

	@Override
	public int getColumnCount() {
		return columnNameStrings.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		var person = persons.get(rowIndex);
		
		if(columnIndex == 0)
			return person.getName();
		if(columnIndex == 1)
			return person.getSurname();
		
		return null;
	}
	
	// needed to show column names in JTable 
	   public String getColumnName(int col) { 
	      return columnNameStrings[col]; 
	   } 
	   public Class getColumnClass(int col) { 
	      return String.class;
	   } 
		

}