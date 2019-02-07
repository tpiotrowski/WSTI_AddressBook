package tp.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import tp.interfaces.ContactBookSerializer;
import tp.model.Person;

public class ContactBookSerializerImpl implements ContactBookSerializer {

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
	public byte[] serialize(List<Person> persons) throws Exception {

		ByteArrayOutputStream bis = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = new ObjectOutputStream(bis);

		outputStream.writeObject(persons);
		
		return bis.toByteArray();
	}

}
