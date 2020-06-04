package utn.telefonica.app;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.model.Customer;
import utn.telefonica.app.projections.CustomerExamen;
import utn.telefonica.app.repository.CustomerRepository;
import utn.telefonica.app.service.CustomerService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class TelefonicaApplicationTests {

	@Autowired
	private CustomerService service;

	@MockBean
	private CustomerRepository repository;


	public void getCustomerExamenTest() throws UserNotexistException {
		List<Cust>
		when(repository.findAll()).thenReturn(Stream.of(new Customer()).collect(Collectors.toList()));
		assertEquals(1,service.getCustomerExamen().size());
	}

}

	@Test
	public void getAllPhoneLinesTest(){
		List<PhoneLine> phoneLines = new ArrayList<PhoneLine>();
		Locality locality = new Locality(351, "Carlos Paz", null, null, null);

		PhoneLine line1 = new PhoneLine(1, null, locality, null, "155210762", null);
		PhoneLine line2 = new PhoneLine(2, null, locality, null, "155210901", null);
		PhoneLine line3 = new PhoneLine(3, null, locality, null, "155210798", null);
		phoneLines.add(line1);
		phoneLines.add(line2);
		phoneLines.add(line3);

		when(phoneLineService.getAllPhoneLinesByPrefix(locality.getPrefix())).thenReturn(phoneLines);
		ResponseEntity<List<PhoneLine>> responsePhoneLine = phoneLineController.getAllPhoneLines(locality.getPrefix());

		assertEquals(200, responsePhoneLine.getStatusCodeValue());
	}
}