package tp.interfaces;

import java.util.List;

import tp.model.Person;

public interface ContactBookService {

	List<Person> GetPersons();
	void UpdatePerson(Person person);
	void DeletePerson(Person person);
	void AddPerson(Person person);
	
}
