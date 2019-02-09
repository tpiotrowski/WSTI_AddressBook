package tp.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
	public List<Person> load() throws Exception{
	
		try(FileInputStream fileInputStream = new FileInputStream(filePath)){
			byte[] result = fileInputStream.readAllBytes();
			
			var data = serializer.deserialize(result);
			
			return data;
		} 
	}

	@Override
	public void persist(List<Person> persons) throws Exception {
		try(FileOutputStream stream = new FileOutputStream(filePath)){
			
		}
		

	}
	
	public ContactBookSerializer FactoryContactBookSerializer() {
		return new ContactBookSerializerImpl();
	}

}
