package tp.model;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import net.bytebuddy.asm.Advice.This;

public class PersonsTableModel extends AbstractTableModel {

	String[] columnNameStrings = { "Name", "Surname" };

	private ArrayList<Person> persons = new ArrayList<Person>();
	JTable table;
	
	public PersonsTableModel(JTable table) {
		super();
		this.table = table;
		this.table.setModel(this);
	}
	
	public PersonsTableModel(ArrayList<Person> persons) {
		super();
		this.persons = persons;
		dataChanged();
	}
	
	public PersonsTableModel(JTable table,ArrayList<Person> persons) {
		this(table);
		this.persons = persons;
		dataChanged();
	}
	
	
	
	public void addPerson(Person person) {
		persons.add(person);
		dataChanged();
	}

	private void dataChanged() {
		this.fireTableDataChanged();
	}
	
	public void updatePerson(Person person) {
		
		var personToUpdate = findPersonIndexById(person);
		
		if(personToUpdate != -1)
		{
			persons.set(personToUpdate, person);
			
			dataChanged();
		}
	}
	
	
	public void deletePerson(Person person) {
		
		Person toDeletePerson = null;
		
		toDeletePerson = findPersonById(person);
		
		if(toDeletePerson != null) {
			persons.remove(person);
			dataChanged();
		}
	}

	private Person findPersonById(Person person) {
		
		Person toDeletePerson = null;
		for (Person per : persons) {
			
			if(person.getId() == per.getId())
			{
				toDeletePerson = per;
			}
			
		}
		return toDeletePerson;
	}
	
	private int findPersonIndexById(Person person) {
		var personTmp = findPersonById(person);
		if(personTmp != null) {
			return persons.indexOf(personTmp);
		}
		return -1;
	}
	
	
	
	
	@Override
	public int getRowCount() {
		return persons.size();
	}

	public Person getRow(int index)
	{
		return persons.get(index);
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