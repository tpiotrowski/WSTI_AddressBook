package tp.services;

import java.util.ArrayList;

import tp.interfaces.ContactBookSerializer;
import tp.interfaces.ContactBookService;
import tp.model.Person;

public class ContactBookFileService implements ContactBookService {

	protected ContactBookSerializer serializer;
	
	public ContactBookFileService(ContactBookSerializer serializer) {
		super();
		this.serializer = serializer;
	}	
	
	@Override
	public ArrayList<Person> GetPersons() {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public void UpdatePerson(Person person) {
		// TODO Auto-generated method stub

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
