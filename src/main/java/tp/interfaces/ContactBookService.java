package tp.interfaces;

import java.util.ArrayList;

import tp.model.Person;

public interface ContactBookService {

	ArrayList<Person> GetPersons();
	void UpdatePerson(Person person);
	void DeletePerson(Person person);
	void AddPerson(Person person);
	
}
