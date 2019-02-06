package tp.interfaces;

import java.util.List;

import tp.model.Person;

public interface ContactBookSerializer {
	List<Person> deserialize();
	void serialize(List<Person> persnons);
}
