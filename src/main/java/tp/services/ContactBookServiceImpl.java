package tp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import tp.interfaces.ContactBookLoaderAndPersister;
import tp.interfaces.ContactBookService;
import tp.model.Person;

public class ContactBookServiceImpl implements ContactBookService {

	protected ContactBookLoaderAndPersister persister;
	protected Map<String, Person> persons;

	public ContactBookServiceImpl(ContactBookLoaderAndPersister persister) {
		super();
		this.persister = persister;
		this.persons = new HashMap<String, Person>();
	}

	public void Initialize() throws Exception {
		var persons = persister.load();

		for (Person person : persons) {
			this.persons.put(person.getId(), person);
		}
	}

	public void Persist() {
		persister.persist(getPersons());
	}

	@Override
	public List<Person> getPersons() {
		return new ArrayList<Person>(persons.values());
	}

	@Override
	public void updatePerson(Person person) {
		if (persons.containsKey(person.getId())) {

			var foundedPerson = persons.get(person.getId());

			foundedPerson.setAddresses1(person.getAddresses1());
			foundedPerson.setAddresses2(person.getAddresses2());
			foundedPerson.setName(person.getName());
			foundedPerson.setPhone(person.getPhone());
			foundedPerson.setSurname(person.getSurname());
			foundedPerson.setTaxId(person.getTaxId());
			foundedPerson.setIdDocument(person.getIdDocument());
		} else {
			addPerson(person);
		}

	}

	@Override
	public void deletePerson(Person person) {
		if (persons.containsKey(person.getId())) {
			persons.remove(person.getId());
		}
	}

	@Override
	public void addPerson(Person person) {
		if (persons.containsKey(person.getId())) {
			updatePerson(person);
		} else {
			persons.put(person.getId(), person);
		}

	}

	@Override
	public List<Person> findPersonsByNamoOrSurname(String nameOrSurname) {

		var personsStream = persons.values().stream();

		try {

			var founded = personsStream
					.filter(p -> (p.getName().toLowerCase().contains(nameOrSurname.toLowerCase())
							|| (p.getSurname().toLowerCase().contains(nameOrSurname.toLowerCase())))
							)
					.collect(Collectors.toList());

			return founded.isEmpty() ? new ArrayList<Person>() : founded;

		} catch (NullPointerException e) {
			return new ArrayList<Person>();
		}

	}

}
