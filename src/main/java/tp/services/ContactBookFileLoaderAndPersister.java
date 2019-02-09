package tp.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import tp.interfaces.ContactBookLoaderAndPersister;
import tp.interfaces.ContactBookSerializer;
import tp.model.Person;

public class ContactBookFileLoaderAndPersister implements ContactBookLoaderAndPersister {

	private ContactBookSerializer serializer;
	private String filePath = "";

	public ContactBookFileLoaderAndPersister(String path) {
		super();
		this.serializer = FactoryContactBookSerializer();
	}

	@Override
	public List<Person> load() throws Exception {

		try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
			byte[] result = fileInputStream.readAllBytes();

			var data = serializer.deserialize(result);

			return data;
		}
	}

	@Override
	public void persist(ArrayList<Person> persons) throws Exception {
		try (FileOutputStream stream = new FileOutputStream(filePath)) {
			byte[] data = serializer.serialize(persons);

			stream.write(data);

		}

	}

	public ContactBookSerializer FactoryContactBookSerializer() {
		return new ContactBookSerializerImpl();
	}

}
