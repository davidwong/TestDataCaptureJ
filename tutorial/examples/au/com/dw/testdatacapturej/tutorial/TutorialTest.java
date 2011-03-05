package au.com.dw.testdatacapturej.tutorial;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.samples.jpetstore.domain.Account;
import org.springframework.samples.jpetstore.domain.Cart;


public class TutorialTest {

	@Test
	public void testInitOrderCart()
	{
		// Create the object that contains the pasted generated code
		CreateTestObject createTestObject = new CreateTestObject();
		
		// invoke the generated method
		Cart cart = createTestObject.createParam2Cart_org_springframework_samples_jpetstore_domain_Order_initOrder();
		
		// test the generated data
		assertNotNull("cart exists", cart);
		
		assertTrue("cart has corrent number of items" + cart.getNumberOfItems(), cart.getNumberOfItems() == 3);
		
		assertTrue("cart contains specific item id", cart.containsItemId("FL-DSH-01"));
		
	}
	
	@Test
	public void testInitOrderAccount()
	{
		// Create the object that contains the pasted generated code
		CreateTestObject createTestObject = new CreateTestObject();
		
		// invoke the generated method
		Account account = createTestObject.createParam1Account_org_springframework_samples_jpetstore_domain_Order_initOrder();
		
		// test the generated data
		assertNotNull("account exists", account);
		
		assertEquals("account for specific user", "j2ee", account.getUsername());
		
	}
}
