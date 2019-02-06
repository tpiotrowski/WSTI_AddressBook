package tp.interfaces;

import java.util.ArrayList;

import tp.model.Person;

public interface ContactBookSerializer {
	ArrayList<Person> Deserialize();
	void Serialize(ArrayList<Person> persnons);
}
