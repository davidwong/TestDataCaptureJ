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
package au.com.dw.testing;


import static au.com.dw.testing.AssertUtil.assertEqualsAny;
import static au.com.dw.testing.AssertUtil.assertNotEqualsAny;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for custom JUnit asserts which check an object against a collection.
 * i.e. does the collection contain an object which is equal to the test object.
 * 
 * @author David Wong
 *
 */
public class AssertUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void StringTest()
	{
		String actual = "actual";
		
		Collection<String> expectedValues = new ArrayList<String>();
		expectedValues.add("one");
		expectedValues.add("two");
		expectedValues.add("actual");
		
		assertEqualsAny(expectedValues, actual);
		
		expectedValues = new ArrayList<String>();
		expectedValues.add("one");
		expectedValues.add("two");
		expectedValues.add("three");
		
		assertNotEqualsAny(expectedValues, actual);
	}
}
