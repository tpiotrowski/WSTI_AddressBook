import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tp.interfaces.ContactBookSerializer;
import tp.model.Person;
import tp.services.ContactBookSerializerImpl;

class ContactBookSerializerImplTest {

	public Person GetPerson() {
		var person = new Person();
		person.setName("dasdasdsad");
		person.setSurname("asdsa");
		person.setPhone("555254125");

		return person;
	}

	public ArrayList<Person> GetPersonsArray() {
		var person = new Person();
		person.setName("dasdasdsad");
		person.setSurname("asdsa");
		person.setPhone("555254125");
		
		var person2 = new Person();
		person2.setName("dasdasdsad");
		person2.setSurname("asdsa");
		person2.setPhone("555254125");
		
		var array = new ArrayList<Person>();
		
		array.add(person);
		array.add(person2);
		
		return array;
	}

	@Test
	void testDeserialize() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	void testSerialize() throws Exception {

		
		
		ContactBookSerializer serializer = new ContactBookSerializerImpl();

		var array = GetPersonsArray();
		
		var result = serializer.serialize(array);

		Assertions.assertAll("", () -> Assertions.assertNotNull(result),
				() -> Assertions.assertTrue(result.length > 0));

	}
	
	@Test
	void testSerializeClean() throws Exception {


		
		ByteArrayOutputStream bis = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(bis);
			
			var person = new Person();
			person.setName("dasdasdsad");
			person.setSurname("asdsa");
			person.setPhone("555254125");
			
			
			var array = new ArrayList<Person>();
			
			array.add(person);
			
			outputStream.writeObject(array);

			var res = bis.toByteArray();
			
			
			Assertions.assertAll("", () -> Assertions.assertNotNull(res),
					() -> Assertions.assertTrue(res.length > 0));
			

		} finally {
			outputStream.close();
			bis.close();
		}

		

	}

}
