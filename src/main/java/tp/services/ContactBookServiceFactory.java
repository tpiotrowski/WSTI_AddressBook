package tp.services;

import tp.interfaces.IContactBookLoaderAndPersister;
import tp.interfaces.IContactBookService;

public class ContactBookServiceFactory {

	public static IContactBookService factory(String path) {
		IContactBookLoaderAndPersister persister = new ContactBookFileLoaderAndPersister(path);
		IContactBookService service = new ContactBookServiceImpl(persister);

		return service;

	}

}
