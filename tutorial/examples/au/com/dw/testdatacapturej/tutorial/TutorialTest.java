package au.com.dw.testdatacapturej.tutorial;

import static org.junit.Assert.*;
import org.springframework.samples.jpetstore.domain.Cart;


public class TutorialTest {

	public void testInitOrderCart()
	{
		//
		CreateTestObject createTestObject = new CreateTestObject();
		
		// invoke the generated method
		Cart cart = createTestObject.createParam2Cart_org_springframework_samples_jpetstore_domain_Order_initOrder();
		
		// test the generated data
		assertNotNull(cart);
		
		assertTrue(cart.getNumberOfItems() == 3);
		
		assertTrue(cart.containsItemId("FL-DSH-01"));
		
	}
}
