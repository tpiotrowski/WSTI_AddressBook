package tp.services;

import tp.interfaces.ContactBookLoaderAndPersister;
import tp.interfaces.ContactBookService;

public class ContactBookServiceFactory {

	public ContactBookService factory(String path)
	{
		ContactBookLoaderAndPersister persister = new ContactBookFileLoaderAndPersister(path);
		ContactBookService service = new ContactBookServiceImpl(persister);
		
		return service;
		
		
	}
	
	
	
	
}
