package tp.interfaces;

import java.util.List;

import tp.model.Person;

public interface ContactBookLoaderAndPersister {
	List<Person> load();
	void persist(List<Person> persons);
}
