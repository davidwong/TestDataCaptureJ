package au.com.dw.testdatacapturej.tutorial;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.samples.jpetstore.domain.Account;
import org.springframework.samples.jpetstore.domain.Cart;


public class TutorialTest {

	@Test
	public void testInitOrderCart()
	{
		// Create the object that contains the pasted generated code for the Cart
		CreateTestCart createTestCart = new CreateTestCart();
		
		// invoke the generated method
		Cart cart = createTestCart.createParam2Cart_org_springframework_samples_jpetstore_domain_Order_initOrder();
		
		// test the generated data
		assertNotNull("cart exists", cart);
		
		assertTrue("cart has corrent number of items" + cart.getNumberOfItems(), cart.getNumberOfItems() == 3);
		
		assertTrue("cart contains specific item id", cart.containsItemId("FL-DSH-01"));
		
	}
	
	@Test
	public void testInitOrderAccount()
	{
		// Create the object that contains the pasted generated code for the Account
		CreateTestAccount createTestAccount = new CreateTestAccount();
		
		// invoke the generated method
		Account account = createTestAccount.createParam1Account_org_springframework_samples_jpetstore_domain_Order_initOrder();
		
		// test the generated data
		assertNotNull("account exists", account);
		
		assertEquals("account for specific user", "j2ee", account.getUsername());
		
	}
}
