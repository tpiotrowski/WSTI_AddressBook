package tp.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tp.interfaces.IContactBookSerializer;
import tp.model.Person;

public class ContactBookSerializerImpl implements IContactBookSerializer {

	@Override
	public List<Person> deserialize(byte[] data) throws Exception {

		var returnArray = new ArrayList<Person>();

		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream objStream = new ObjectInputStream(bis);
		returnArray = (ArrayList<Person>) objStream.readObject();
		bis.close();
		objStream.close();

		return returnArray;
	}

	@Override
	public byte[] serialize(ArrayList<Person> persons) throws Exception {

		ByteArrayOutputStream bis = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(bis);
			
			outputStream.writeObject(persons);

			return bis.toByteArray();

		} finally {
			outputStream.close();
			bis.close();
		}

	}

}
