
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import tp.model.Person;

public class ContactBookJsonSerializerTests {

	@Test
	public void persons_serialize_test() {
		Person person = new Person();

		Assertions.assertNotNull(person.getId());

	}
	
	
}
