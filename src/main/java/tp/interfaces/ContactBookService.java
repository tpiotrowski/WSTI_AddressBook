package tp.interfaces;

import java.util.List;

import tp.model.Person;

public interface ContactBookService {

	List<Person> getPersons();
	void updatePerson(Person person);
	void deletePerson(Person person);
	void addPerson(Person person);
	List<Person> findPersonsByNamoOrSurname(String nameOrSurname);
	
}
