package tp.interfaces;

import java.util.ArrayList;
import java.util.List;

import tp.model.Person;

public interface IContactBookService {

	ArrayList<Person> getPersons();
	void updatePerson(Person person);
	void deletePerson(Person person);
	void addPerson(Person person);
	ArrayList<Person> findPersonsByNamoOrSurname(String nameOrSurname);
	void Initialize() throws Exception;
	void persist() throws Exception;
	Boolean isDirty();
	
}
