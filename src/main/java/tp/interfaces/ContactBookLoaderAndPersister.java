package tp.interfaces;

import java.util.List;

import tp.model.Person;

public interface ContactBookLoaderAndPersister {
	List<Person> load() throws Exception;
	void persist(List<Person> persons) throws Exception;
}
