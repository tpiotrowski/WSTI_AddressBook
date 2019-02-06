package tp.services;

import java.util.ArrayList;

import tp.interfaces.ContactBookSerializer;
import tp.interfaces.ContactBookService;
import tp.model.Person;

public class ContactBookFileService implements ContactBookService {

	protected ContactBookSerializer serializer;
	protected ArrayList<Person> persons;
	
	public ContactBookFileService(ContactBookSerializer serializer) {
		super();
		this.serializer = serializer;
		this.persons = new ArrayList<Person>();
	}	
	
	@Override
	public ArrayList<Person> GetPersons() {
		return persons;
	}

	@Override
	public void UpdatePerson(Person person) {
		
	}

	@Override
	public void DeletePerson(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AddPerson(Person person) {
		// TODO Auto-generated method stub

	}

}
