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
package au.com.dw.testdatacapturej.builder;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.SystemUtils;
import org.junit.Test;

import au.com.dw.testdatacapturej.builder.MethodBuilder;
import au.com.dw.testdatacapturej.mock.dataholder.AllDataHolder;

public class MethodBuilderTest {

	/**
	 * Test for simple class as param 1
	 */
	@Test
	public void testCreateMethodLineForStringParam1() {
		MethodBuilder builder = new MethodBuilder();
		String result = builder.createMethodLine("org.test.DummyClass.dummyMethod", "test", 1);
		System.out.println(result);
		
		String expected = "public java.lang.String createParam1String_org_test_DummyClass_dummyMethod() {" + SystemUtils.LINE_SEPARATOR;
		assertEquals(expected, result);
	}

	/**
	 * Test for param 2
	 */
	@Test
	public void testCreateMethodLineForStringParam2() {
		MethodBuilder builder = new MethodBuilder();
		String result = builder.createMethodLine("org.test.DummyClass.dummyMethod", "test", 2);
		System.out.println(result);
		
		String expected = "public java.lang.String createParam2String_org_test_DummyClass_dummyMethod() {" + SystemUtils.LINE_SEPARATOR;
		assertEquals(expected, result);
	}
	
	/**
	 * Test for return value instead of param
	 */
	@Test
	public void testCreateMethodLineForStringReturn() {
		MethodBuilder builder = new MethodBuilder();
		String result = builder.createMethodLine("org.test.DummyClass.dummyMethod", "test", 0);
		System.out.println(result);
		
		String expected = "public java.lang.String createReturnString_org_test_DummyClass_dummyMethod() {" + SystemUtils.LINE_SEPARATOR;
		assertEquals(expected, result);
	}

	/**
	 * Test for primitive value instead of object as param
	 */
	@Test
	public void testCreateMethodLineForIntParam1() {
		MethodBuilder builder = new MethodBuilder();
		String result = builder.createMethodLine("org.test.DummyClass.dummyMethod", 100, 1);
		System.out.println(result);
		
		String expected = "public java.lang.Integer createParam1Integer_org_test_DummyClass_dummyMethod() {" + SystemUtils.LINE_SEPARATOR;
		assertEquals(expected, result);
	}
	
	/**
	 * Test for primitive value wrapper
	 */
	@Test
	public void testCreateMethodLineForIntegerParam1() {
		MethodBuilder builder = new MethodBuilder();
		String result = builder.createMethodLine("org.test.DummyClass.dummyMethod", new Integer(100), 1);
		System.out.println(result);
		
		String expected = "public java.lang.Integer createParam1Integer_org_test_DummyClass_dummyMethod() {" + SystemUtils.LINE_SEPARATOR;
		assertEquals(expected, result);
	}
	
	/**
	 * Test for complicated object
	 */
	@Test
	public void testCreateMethodLineForObjectParam1() {
		MethodBuilder builder = new MethodBuilder();
		String result = builder.createMethodLine("org.test.DummyClass.dummyMethod", new AllDataHolder(), 1);
		System.out.println(result);
		
		String expected = "public au.com.dw.testdatacapturej.mock.dataholder.AllDataHolder createParam1AllDataHolder_org_test_DummyClass_dummyMethod() {" + SystemUtils.LINE_SEPARATOR;
		assertEquals(expected, result);
	}
}
