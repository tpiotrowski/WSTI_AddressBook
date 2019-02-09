package tp.interfaces;

import java.util.ArrayList;
import java.util.List;

import tp.model.Person;

public interface ContactBookSerializer {
	List<Person> deserialize(byte[] data) throws Exception;
	byte[] serialize(ArrayList<Person> persnons) throws Exception;
}
