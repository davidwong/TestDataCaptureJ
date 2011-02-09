/*******************************************************************************
 * Copyright () 2009, 2011 David Wong
 *
 * This file is part of TestDataCaptureJ.
 *
 * TestDataCaptureJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TestDataCaptureJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Afferro General Public License for more details.
 *
 * You should have received a copy of the GNU Afferro General Public License
 * along with TestDataCaptureJ.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package au.com.dw.testdatacapturej.explanation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


import org.junit.Test;

import au.com.dw.testdatacapturej.mock.explanation.Account;
import au.com.dw.testdatacapturej.mock.explanation.Address;
import au.com.dw.testdatacapturej.mock.explanation.Customer;
import au.com.dw.testdatacapturej.mock.explanation.CustomerAddress;
import au.com.dw.testdatacapturej.mock.explanation.CustomerAddressesInArray;
import au.com.dw.testdatacapturej.mock.explanation.CustomerAddressesInCollection;
import au.com.dw.testdatacapturej.mock.explanation.CustomerAddressesInMap;

/**
 * This is not a real test case, it's only to generate logging for the explanation page in the documentation.
 * 
 * Hence these tests will not be included when regression testing with AllTests.
 * Also these tests will never fail as we are only using them to generate example logging.
 * 
 * Note that there are empty methods named 'joinPoint...(..)', these are used as JoinPoints that have been
 * configured for AspectJ to capture the contents of the parameter object.
 * e.g.
 * The method 'testCustomerAddressesInCollection()' creates the data and passes the data object as a
 * parameter to the method 'joinPointParamForCustomerAddressesInCollection(CustomerAddressesInCollection customerAddresses)'.
 * Because this method has a JointPoint configured on it, AspectJ will be able to capture the 'CustomerAddressesInCollection'
 * object to generate logging for it.
 * 
 * @author David Wong
 */
public class ExplanationTest {

	@Test
	public void testCustomer()
	{
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setSurName("Smith");
		
		joinPointParamForCustomer(customer);
	}
	
	private void joinPointParamForCustomer(Customer customer)
	{
		
	}
	
	@Test
	public void testAccount()
	{
		Account account = joinPointReturnForAccount();
	}
	
	private Account joinPointReturnForAccount()
	{
		Account account = new Account();
		account.setActive(true);
		account.setAmount(1000.50d);
		account.setCustomerNumber(1234567890L);
		account.setInterestRate(0.10f);
		account.setName("Savings Account");
		account.setNumber(123);
		account.setType('A');
		
		return account;
	}
	
	private Address createAddress1()
	{
		Address address = new Address();
		address.setHouseNumber(1);
		address.setStreet("Home Street");
		address.setCity("Sydney");
		
		return address;
	}
	
	private Address createAddress2()
	{
		Address address = new Address();
		address.setHouseNumber(2);
		address.setStreet("Work Road");
		address.setCity("London");
		
		return address;
	}

	@Test
	public void testCustomerAddress()
	{
		CustomerAddress customerAddress = joinPointReturnForCustomerAddress();
	}
	
	private CustomerAddress joinPointReturnForCustomerAddress()
	{
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setAddress(createAddress1());
		
		return customerAddress;
	}
	
	@Test
	public void testCustomerAddressesInCollection()
	{
		CustomerAddressesInCollection customerAddresses = new CustomerAddressesInCollection();
		
		Collection<Object> addresses = new ArrayList<Object>();
		addresses.add(createAddress1());
		addresses.add("Test value");
		customerAddresses.setAddresses(addresses);
		
		joinPointParamForCustomerAddressesInCollection(customerAddresses);
	}
	
	private void joinPointParamForCustomerAddressesInCollection(CustomerAddressesInCollection customerAddresses)
	{
		
	}
	
	@Test
	public void testCustomerAddressesInArray()
	{
		CustomerAddressesInArray customerAddresses = new CustomerAddressesInArray();
		
		Object[] addresses = new Object[2];
		addresses[0] = createAddress1();
		addresses[1] = createAddress2();
		customerAddresses.setAddresses(addresses);
		
		joinPointParamForCustomerAddressesInArray(customerAddresses);
	}
	
	private void joinPointParamForCustomerAddressesInArray(CustomerAddressesInArray customerAddresses)
	{
		
	}
	
	@Test
	public void testCustomerAddressesInMap()
	{
		CustomerAddressesInMap customerAddresses = new CustomerAddressesInMap();
		
		HashMap<String, Address> addresses = new HashMap<String, Address>();
		addresses.put("work", createAddress2());
		addresses.put("home", createAddress1());
		customerAddresses.setAddresses(addresses);
		
		joinPointParamForCustomerAddressesInMap(customerAddresses);
	}
	
	private void joinPointParamForCustomerAddressesInMap(CustomerAddressesInMap customerAddresses)
	{
		
	}
}
