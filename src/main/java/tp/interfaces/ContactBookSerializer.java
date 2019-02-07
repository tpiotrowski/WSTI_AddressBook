package tp.interfaces;

import java.util.List;

import tp.model.Person;

public interface ContactBookSerializer {
	List<Person> deserialize(byte[] data) throws Exception;
	byte[] serialize(List<Person> persnons) throws Exception;
}
