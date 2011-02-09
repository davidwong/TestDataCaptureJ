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


import org.junit.Test;

import au.com.dw.testdatacapturej.mock.explanation.Customer;
import au.com.dw.testdatacapturej.mock.explanation.CustomerFullName;
import au.com.dw.testdatacapturej.mock.explanation.CustomerImmutable;
import au.com.dw.testdatacapturej.mock.explanation.CustomerListing;
import au.com.dw.testdatacapturej.mock.explanation.CustomerMultipleConstructors;

/**
 * This is not a real test case, it's only to generate logging for the customization page in the documentation.
 * 
 * Hence these tests will not be included when regression testing with AllTests.
 * Also these tests will never fail as we are only using them to generate example logging.
 * 
 * @author David Wong
 */
public class CustomizationTest {

	@Test
	public void testCustomerMultipleConstructors()
	{
		CustomerMultipleConstructors customer = new CustomerMultipleConstructors();
		customer.setFirstName("John");
		customer.setSurName("Smith");
		
		joinPointParamForCustomerMultipleConstructors(customer);
	}
	
	private void joinPointParamForCustomerMultipleConstructors(CustomerMultipleConstructors customer)
	{
		
	}
	
	@Test
	public void testCustomerImmutable()
	{
		CustomerImmutable customer = new CustomerImmutable("Mary", "Jones");
		
		joinPointParamForCustomerImmutable(customer);
	}

	private void joinPointParamForCustomerImmutable(CustomerImmutable customer)
	{
		
	}

	@Test
	public void testCustomerFullName()
	{
		CustomerFullName customer = new CustomerFullName();
		customer.setFirstName("Ted");
		customer.setSurName("Jones");
		
		joinPointParamForCustomerFullName(customer);
	}

	private void joinPointParamForCustomerFullName(CustomerFullName customer)
	{
		
	}
	
	@Test
	public void testCustomerListing()
	{
		Customer customer1 = new Customer();
		customer1.setFirstName("John");
		customer1.setSurName("Smith");

		Customer customer2 = new Customer();
		customer2.setFirstName("Mary");
		customer2.setSurName("Jones");
		
		CustomerListing listing = new CustomerListing();
		listing.addCustomer(customer1);
		listing.addCustomer(customer2);
		
		joinPointParamForCustomerListing(listing);
	}

	private void joinPointParamForCustomerListing(CustomerListing listing)
	{
		
	}
}
