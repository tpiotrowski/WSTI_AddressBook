package tp.services;

import java.util.List;

import tp.interfaces.ContactBookLoaderAndPersister;
import tp.interfaces.ContactBookSerializer;
import tp.model.Person;

public class ContactBookFileLoaderAndPersister implements ContactBookLoaderAndPersister {

	private ContactBookSerializer serializer;
	
	public ContactBookFileLoaderAndPersister(ContactBookSerializer serializer) {
		super();
		this.serializer = serializer;
	}

	@Override
	public List<Person> load() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(List<Person> persons) {
		// TODO Auto-generated method stub

	}

}
