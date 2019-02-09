package tp.interfaces;

import java.util.ArrayList;
import java.util.List;

import tp.model.Person;

public interface ContactBookLoaderAndPersister {
	List<Person> load() throws Exception;
	void persist(ArrayList<Person> persons) throws Exception;
}
