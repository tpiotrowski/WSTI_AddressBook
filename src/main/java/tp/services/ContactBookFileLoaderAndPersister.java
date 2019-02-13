package tp.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import tp.interfaces.IContactBookLoaderAndPersister;
import tp.interfaces.IContactBookSerializer;
import tp.model.Person;

public class ContactBookFileLoaderAndPersister implements IContactBookLoaderAndPersister {

	private IContactBookSerializer serializer;
	private String filePath = "";

	public ContactBookFileLoaderAndPersister(String path) {
		super();
		this.serializer = FactoryContactBookSerializer();
		this.filePath = path;
	}

	@Override
	public List<Person> load() throws Exception {

		var tmpDir = new File(filePath);
		boolean exists = tmpDir.exists();
		if(!exists)
			return new ArrayList<Person>();
		
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

	protected IContactBookSerializer FactoryContactBookSerializer() {
		return new ContactBookSerializerImpl();
	}

}
