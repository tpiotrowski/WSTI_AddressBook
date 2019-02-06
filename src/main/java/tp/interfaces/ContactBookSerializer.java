package tp.interfaces;

import java.util.List;

import tp.model.Person;

public interface ContactBookSerializer {
	List<Person> Deserialize();
	void Serialize(List<Person> persnons);
}
